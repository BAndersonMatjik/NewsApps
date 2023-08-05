package com.bmatjik.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bmatjik.myapplication.common.BaseFragment
import com.bmatjik.myapplication.common.MarginItemDecoration
import com.bmatjik.myapplication.databinding.FragmentArticlesNewsBinding
import com.bmatjik.myapplication.view.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticlesNewsFragment : BaseFragment<FragmentArticlesNewsBinding>() {


    companion object {
        fun newInstance() = ArticlesNewsFragment()
    }

    private val adapter by lazy {
        ArticleAdapter()
    }
    private val viewModel by viewModels<ArticlesNewsViewModel>()
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentArticlesNewsBinding {
        return FragmentArticlesNewsBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNewsSources.adapter = adapter
        binding.rvNewsSources.addItemDecoration(MarginItemDecoration(8))
        viewModel.getArticles()
        lifecycleScope.launch {
            viewModel.articles?.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
