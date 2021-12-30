package com.diaa.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diaa.news.R
import com.diaa.news.databinding.RvSearchLayoutBinding
import com.diaa.news.pojo.Article
import com.squareup.picasso.Picasso

class SearchNewsAdapter(private val searchAdapterListener: SearchAdapterListener) :
    RecyclerView.Adapter<SearchNewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvSearchLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            searchAdapterListener
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
        private val binding: RvSearchLayoutBinding,
        private val newsListener: SearchAdapterListener
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

            binding.root.setOnClickListener { newsListener.onClick(data) }
        }
    }

    interface SearchAdapterListener {
        fun onClick(article: Article)
    }
}
