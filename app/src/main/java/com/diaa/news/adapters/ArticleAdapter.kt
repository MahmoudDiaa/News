package com.diaa.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diaa.news.R
import com.diaa.news.databinding.RvNewsBinding
import com.diaa.news.pojo.Article
import com.squareup.picasso.Picasso

class ArticleAdapter(private val newsListener: NewsAdapterListener) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            newsListener
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.content == newItem.content
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Article>) = differ.submitList(list)

    class ViewHolder(
        private val binding: RvNewsBinding,
        private val newsListener: NewsAdapterListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.publishedAt
            if (data.urlToImage != null && data.urlToImage.isNotEmpty()) Picasso.get()
                .load(data.urlToImage)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.ivNews)
            else
                binding.ivNews.setImageResource(R.drawable.ic_baseline_broken_image_24)

            if (data.isFav) binding.ivFav.setImageResource(R.drawable.ic_baseline_red_favorite_24)
            else binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)

            binding.ivFav.setOnClickListener {

                if (!data.isFav) binding.ivFav.setImageResource(R.drawable.ic_baseline_red_favorite_24)
                else binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                newsListener.onFavClick(data)
            }
            binding.root.setOnClickListener { newsListener.onClick(data) }
        }
    }

    interface NewsAdapterListener {
        fun onClick(article: Article)
        fun onFavClick(article: Article)
    }
}
