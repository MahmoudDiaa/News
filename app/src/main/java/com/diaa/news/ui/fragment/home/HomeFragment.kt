package com.diaa.news.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diaa.news.R
import com.diaa.news.adapters.ArticleAdapter
import com.diaa.news.databinding.FragmentHomeBinding
import com.diaa.news.pojo.Article
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: ArticleAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        adapter = ArticleAdapter(object : ArticleAdapter.NewsAdapterListener {
            override fun onClick(article: Article) {
                val args = Bundle()
                args.putString("url", article.url)
                findNavController().navigate(R.id.action_navigation_home_to_navigation_details, args)
            }

            override fun onFavClick(article: Article) {
                viewModel.addArticleToFav(article)
            }
        })
        val manager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.rvNews.layoutManager = manager
        binding.rvNews.adapter = adapter

        observer()
    }

    private fun observer() {
        viewModel.articleLiveData.observe(
            viewLifecycleOwner,
            {
                adapter.submitList(it)
            }
        )

        viewModel.errorLiveData.observe(
            viewLifecycleOwner,
            {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
