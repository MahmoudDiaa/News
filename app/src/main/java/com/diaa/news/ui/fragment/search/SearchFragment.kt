package com.diaa.news.ui.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diaa.news.R
import com.diaa.news.adapters.SearchNewsAdapter
import com.diaa.news.databinding.FragmentSearchBinding
import com.diaa.news.pojo.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchNewsAdapter

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        initView()
        searchResult()

        observer()
        return binding.root
    }

    private fun observer() {
        viewModel.articleLiveData.observe(viewLifecycleOwner, adapter::submitList)
        viewModel.errorLiveData.observe(
            viewLifecycleOwner,
            {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun initView() {
        adapter = SearchNewsAdapter(object : SearchNewsAdapter.SearchAdapterListener {
            override fun onClick(article: Article) {
                val args = Bundle()
                args.putString("url", article.url)
                findNavController().navigate(
                    R.id.navigation_details,
                    args
                )
            }
        })
        val manager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.rvSearchNews.layoutManager = manager
        binding.rvSearchNews.adapter = adapter

        observer()
    }

    private fun searchResult() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty())
                    viewModel.search(query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
