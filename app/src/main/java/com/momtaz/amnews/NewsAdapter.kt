package com.momtaz.amnews

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.momtaz.amnews.databinding.ArticleListItemBinding

class NewsAdapter(private val activity: Activity, private val articles: ArrayList<Article>) :
    Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ArticleListItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.articleText.text = articles[position].title
        Glide
            .with(holder.binding.articleImage.context)
            .load(articles[position].urlToImage)
            .error(R.drawable.broken_image_24)
            .transition(DrawableTransitionOptions.withCrossFade(1500))
            .into(holder.binding.articleImage)
        holder.binding.articleContainer.setOnClickListener {
            val url = articles[position].url
            val intent =Intent(Intent.ACTION_VIEW,url.toUri())
            activity.startActivity(intent)
        }
        holder.binding.share.setOnClickListener {
            ShareCompat
                .IntentBuilder(activity)
                .setType("text/plain")
                .setChooserTitle("share article with : ")
                .setText(articles[position].url)
                .startChooser()
        }
    }
}