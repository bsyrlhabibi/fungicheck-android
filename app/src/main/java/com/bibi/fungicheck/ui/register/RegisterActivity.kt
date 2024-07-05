package com.bibi.fungicheck.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bibi.fungicheck.R
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bibi.fungicheck.data.model.Result
import com.bibi.fungicheck.data.response.RegisterResponse
import com.bibi.fungicheck.databinding.ActivityRegisterBinding
import com.bibi.fungicheck.ui.ViewModelFactory
import com.bibi.fungicheck.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnRegister.setOnClickListener {
            register()
        }

        binding.goLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val userViewModelFactory: ViewModelFactory = ViewModelFactory.getInstance(this)
        registerViewModel =
            ViewModelProvider(this, userViewModelFactory)[RegisterViewModel::class.java]
    }

    private fun register() {
        val name = binding.etFullName.text.toString().trim()
        val email = binding.etEmailAddress.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val rePassword = binding.etRePassword.text.toString().trim()

        if (password != rePassword) {
            // Password tidak cocok, tampilkan pesan kesalahan
            binding.etRePassword.error = "Kata sandi tidak sama"
            return
        }

        registerViewModel.register(name, email, password).observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success<*> -> {
                        binding.progressBar.visibility = View.GONE
                        val user = result.data as RegisterResponse
                        if (user.error) {
                            Toast.makeText(
                                this@RegisterActivity,
                                user.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                resources.getString(R.string.register_success),
                                Toast.LENGTH_SHORT
                            ).show()
                            Intent(this@RegisterActivity, LoginActivity::class.java).also {
                                it.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(it)
                            }
                            finish()
                        }
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@RegisterActivity,
                            resources.getString(R.string.register_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
