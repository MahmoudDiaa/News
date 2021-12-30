package com.diaa.news.ui.fragment.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diaa.news.R
import com.diaa.news.adapters.ArticleAdapter
import com.diaa.news.databinding.FragmentFavouriteBinding
import com.diaa.news.pojo.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private val viewModel: FavouriteViewModel by viewModels()
    private var _binding: FragmentFavouriteBinding? = null
    private lateinit var adapter: ArticleAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        initView()
        observer()
        return binding.root
    }

    private fun initView() {
        adapter = ArticleAdapter(object : ArticleAdapter.NewsAdapterListener {
            override fun onClick(article: Article) {
                val args = Bundle()
                args.putString("url", article.url)
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_details,
                    args
                )
            }

            override fun onFavClick(article: Article) {
                viewModel.removeFromFav(article)
            }
        })
        val manager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.rvFavNews.layoutManager = manager
        binding.rvFavNews.adapter = adapter
    }

    private fun observer() {
        viewModel.favArticleLiveData.observe(
            viewLifecycleOwner,
            {
                adapter.submitList(it)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
