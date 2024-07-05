package com.bibi.fungicheck.ui.article

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bibi.fungicheck.data.adapter.ArticleAdapter
import com.bibi.fungicheck.data.api.ApiConfig
import com.bibi.fungicheck.data.model.Article
import com.bibi.fungicheck.databinding.ActivityArticleBinding
import com.bibi.fungicheck.ui.detail.DetailArticleActivity
import com.bibi.fungicheck.ui.home.HomeActivity
import com.bibi.fungicheck.ui.profile.ProfileActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleActivity : AppCompatActivity(), ArticleAdapter.ArticleClickListener {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var articlesAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set item artikel sebagai item yang terpilih saat activity dimuat
        binding.menuItem2Icon.isSelected = true
        binding.menuItem2Text.isSelected = true

        // Set listener untuk item beranda pada bottom navigation jika ID itemnya adalah menu_item1
        binding.menuItem1.setOnClickListener {
            // Pindah ke halaman beranda
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(0, 0)
        }

        // Set listener untuk item profil pada bottom navigation jika ID itemnya adalah menu_item3
        binding.menuItem3.setOnClickListener {
            // Pindah ke halaman profil
            startActivity(Intent(this, ProfileActivity::class.java))
            overridePendingTransition(0, 0)
        }

        // Inisialisasi adapter dan atur RecyclerView
        articlesAdapter = ArticleAdapter(emptyList(), this)
        binding.rvArticleList.layoutManager = LinearLayoutManager(this)
        binding.rvArticleList.adapter = articlesAdapter

        // Panggil API untuk mendapatkan daftar artikel
        val apiService = ApiConfig.getApiService()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getArticles("")
                val articles = response.data ?: emptyList()
                withContext(Dispatchers.Main) {
                    articlesAdapter.updateArticles(articles)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Failed to fetch articles")
            }
        }
    }

    override fun onArticleClick(article: Article) {
        // Ketika artikel diklik, buka DetailArticleActivity dan kirim data artikel
        val intent = Intent(this, DetailArticleActivity::class.java)
        intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, article)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
