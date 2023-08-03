package com.bmatjik.myapplication.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bmatjik.myapplication.R
import com.bmatjik.myapplication.databinding.FragmentMovieListCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

class MovieListCategoryFragment : BaseFragment<FragmentMovieListCategoryBinding>() {

    private lateinit var viewModel: MovieListCategoryViewModel

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListCategoryBinding = FragmentMovieListCategoryBinding.inflate(inflater,container,false)

    override fun initView() {
        binding.tvTest.text = "Test"
    }

}