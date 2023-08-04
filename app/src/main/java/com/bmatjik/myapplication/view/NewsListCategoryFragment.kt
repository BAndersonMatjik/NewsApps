package com.bmatjik.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bmatjik.myapplication.common.BaseFragment
import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.common.MarginItemDecoration
import com.bmatjik.myapplication.databinding.FragmentNewsListCategoryBinding
import com.bmatjik.myapplication.view.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListCategoryFragment : BaseFragment<FragmentNewsListCategoryBinding>() {

    private val adapter by lazy {
        CategoryAdapter()
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsListCategoryBinding = FragmentNewsListCategoryBinding.inflate(inflater,container,false)

    override fun initView() {
        binding.rvCategory.adapter = adapter
        binding.rvCategory.addItemDecoration(MarginItemDecoration(6))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setListenerItem(object : CategoryAdapter.OnClickItem{
            override fun onClickItem(item: String) {
                findNavController().navigate(NewsListCategoryFragmentDirections.actionNewsListCategoryFragmentToNewsSourcesFragment(item))
            }
        })
        adapter.submit(Constants.NewsCategory)
    }

}