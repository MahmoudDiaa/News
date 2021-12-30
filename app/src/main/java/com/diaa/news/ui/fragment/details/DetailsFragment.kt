package com.diaa.news.ui.fragment.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.diaa.news.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl(arguments?.getString("url")!!)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.e("Details Frag", "handleOnBackPressed: ",)
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
