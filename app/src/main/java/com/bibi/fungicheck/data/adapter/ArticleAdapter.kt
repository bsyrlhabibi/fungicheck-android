package com.bibi.fungicheck.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bibi.fungicheck.R
import com.bibi.fungicheck.data.model.Article
import com.squareup.picasso.Picasso

class ArticleAdapter(
    private var articles: List<Article>,
    private val clickListener: ArticleClickListener
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    interface ArticleClickListener {
        fun onArticleClick(article: Article)
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val photoImageView: ImageView = itemView.findViewById(R.id.ivPhoto)
        private val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        private val contentTextView: TextView = itemView.findViewById(R.id.tvContent)
        private val authorTextView: TextView = itemView.findViewById(R.id.tvAuthor)
//        private val dateTextView: TextView = itemView.findViewById(R.id.tvDate)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: Article) {
            Picasso.get().load(article.photo).into(photoImageView)
            titleTextView.text = article.title
            contentTextView.text = article.content
            authorTextView.text = article.author
//            dateTextView.text = article.date
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedArticle = articles[position]
                clickListener.onArticleClick(clickedArticle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
