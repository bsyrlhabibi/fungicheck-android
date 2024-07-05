package com.bibi.fungicheck.ui.home

import com.bibi.fungicheck.ui.article.ArticleActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bibi.fungicheck.R
import com.bibi.fungicheck.ui.predict.PredictActivity
import com.bibi.fungicheck.ui.profile.ProfileActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Mendapatkan referensi LinearLayout untuk menuju activity Predict
        val predictLayout = findViewById<LinearLayout>(R.id.activityPrediction)
        predictLayout.setOnClickListener {
            startActivity(Intent(this, PredictActivity::class.java))
            overridePendingTransition(0, 0)
        }

        // Mendapatkan referensi ImageView dan TextView untuk item beranda
        val menu1Icon = findViewById<ImageView>(R.id.menu_item1_icon)
        val menu1Text = findViewById<TextView>(R.id.menu_item1_text)

        // Set item beranda sebagai item yang terpilih saat activity dimuat
        menu1Icon.isSelected = true
        menu1Text.isSelected = true

        // Set listener untuk item artikel pada bottom navigation jika ID itemnya adalah menu_item2
        val menu2 = findViewById<LinearLayout>(R.id.menu_item2)
        menu2.setOnClickListener {
            // Pindah ke halaman artikel
            startActivity(Intent(this, ArticleActivity::class.java))
            overridePendingTransition(0, 0)
        }

        val menu3 = findViewById<LinearLayout>(R.id.menu_item3)
        menu3.setOnClickListener {
            // Pindah ke halaman profil
            startActivity(Intent(this, ProfileActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }
}


