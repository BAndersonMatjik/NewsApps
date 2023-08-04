package com.bmatjik.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bmatjik.myapplication.common.BaseFragment
import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.common.MarginItemDecoration
import com.bmatjik.myapplication.databinding.FragmentMovieListCategoryBinding
import com.bmatjik.myapplication.view.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListCategoryFragment : BaseFragment<FragmentMovieListCategoryBinding>() {

    private val adapter by lazy {
        CategoryAdapter()
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListCategoryBinding = FragmentMovieListCategoryBinding.inflate(inflater,container,false)

    override fun initView() {
        binding.rvCategory.adapter = adapter
        binding.rvCategory.addItemDecoration(MarginItemDecoration(6))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.submit(Constants.NewsCategory)
    }

}