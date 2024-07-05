package com.bibi.fungicheck.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bibi.fungicheck.R
import com.bibi.fungicheck.data.model.Result
import com.bibi.fungicheck.data.response.LoginResponse
import com.bibi.fungicheck.databinding.ActivityLoginBinding
import com.bibi.fungicheck.ui.home.HomeActivity
import com.bibi.fungicheck.ui.register.RegisterActivity
import com.bibi.fungicheck.ui.ViewModelFactory
import com.bibi.fungicheck.ui.SharedPreferencesManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModelFactory = ViewModelFactory.getInstance(this)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        sharedPreferencesManager = SharedPreferencesManager(this)

        val accessToken = sharedPreferencesManager.getAccessToken()
        if (accessToken != null) {
            // Token sudah tersimpan, langsung masuk ke MainActivity
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.goRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        loginViewModel.login(email, password).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success<LoginResponse> -> {
                    binding.progressBar.visibility = View.GONE
                    val loginResponse = result.data
                    if (loginResponse.data != null && !loginResponse.data.user?.name.isNullOrEmpty()) {
                        val userName = loginResponse.data.user?.name ?: ""
                        val userEmail = loginResponse.data.user?.email ?: ""

                        // Logging nama dan email ke Logcat
                        Log.d("LoginActivity", "User Name: $userName, User Email: $userEmail")

                        val accessToken = loginResponse.data.access_token ?: ""
                        sharedPreferencesManager.saveAccessToken(accessToken)
                        sharedPreferencesManager.saveUserName(userName)
                        sharedPreferencesManager.saveUserEmail(userEmail)

                        Toast.makeText(
                            this@LoginActivity,
                            getString(R.string.login_success),
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            loginResponse.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@LoginActivity,
                        resources.getString(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
