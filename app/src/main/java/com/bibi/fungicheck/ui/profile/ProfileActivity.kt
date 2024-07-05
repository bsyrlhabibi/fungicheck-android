package com.bibi.fungicheck.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bibi.fungicheck.R
import com.bibi.fungicheck.ui.SharedPreferencesManager
import com.bibi.fungicheck.ui.article.ArticleActivity
import com.bibi.fungicheck.ui.home.HomeActivity
import com.bibi.fungicheck.ui.login.LoginActivity
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private lateinit var userNameTextView: TextView
    private lateinit var userEmailTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        userNameTextView = findViewById(R.id.tvUserName)
        userEmailTextView = findViewById(R.id.tvUserEmail)

        sharedPreferencesManager = SharedPreferencesManager(this)

        val userName = sharedPreferencesManager.getUserName()
        val userEmail = sharedPreferencesManager.getUserEmail()

        userNameTextView.text = userName
        userEmailTextView.text = userEmail

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }

        val menu3Icon = findViewById<ImageView>(R.id.menu_item3_icon)
        val menu3Text = findViewById<TextView>(R.id.menu_item3_text)

        menu3Icon.isSelected = true
        menu3Text.isSelected = true

        val menu1 = findViewById<LinearLayout>(R.id.menu_item1)
        menu1.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(0, 0)
        }

        val menu2 = findViewById<LinearLayout>(R.id.menu_item2)
        menu2.setOnClickListener {
            startActivity(Intent(this, ArticleActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }

    private fun logout() {
        lifecycleScope.launch {
            sharedPreferencesManager.clearSharedPreferences() // Panggil fungsi clearSharedPreferences() di sini
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }
    }
}
