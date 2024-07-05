package com.bibi.fungicheck.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bibi.fungicheck.R
import com.bibi.fungicheck.data.model.Article
import com.bibi.fungicheck.databinding.ActivityDetailArticleBinding
import com.squareup.picasso.Picasso

class DetailArticleActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.title_article)

        // Ambil data artikel dari intent
        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE)

        // Gunakan data artikel untuk menampilkan detailnya
        article?.let { displayArticleDetail(it) }
    }

    private fun displayArticleDetail(article: Article) {
        Picasso.get().load(article.photo).into(binding.ivPhoto)
        binding.tvTitle.text = article.title
        binding.tvContent.text = article.content
        binding.tvAuthor.text = article.author
    }
}

