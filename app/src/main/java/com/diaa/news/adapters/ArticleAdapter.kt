package com.diaa.news.adapters
class NewsAdapter() :
RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
    RvNews.inflate(
    LayoutInflater.from(parent.context),
    parent,
    false
)
    )
}

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
    holder.bind(differ.currentList[position])
      private val diffCallback = object : DiffUtil.ItemCallback<NewsRespond>() {
        override fun areItemsTheSame(oldItem: NewsRespond, newItem: NewsRespond): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: NewsRespond, newItem: NewsRespond): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<NewsRespond>) = differ.submitList(list)


    class ViewHolder(private val binding: RvNews) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsRespond) {

    }
    }
}