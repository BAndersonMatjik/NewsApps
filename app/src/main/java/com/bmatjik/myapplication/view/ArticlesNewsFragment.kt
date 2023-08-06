package com.bmatjik.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bmatjik.myapplication.common.BaseFragment
import com.bmatjik.myapplication.common.MarginItemDecoration
import com.bmatjik.myapplication.common.textChangess
import com.bmatjik.myapplication.databinding.FragmentArticlesNewsBinding
import com.bmatjik.myapplication.view.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticlesNewsFragment : BaseFragment<FragmentArticlesNewsBinding>() {

    private val args: ArticlesNewsFragmentArgs by navArgs()
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
        viewModel.apply {
            category = args.category
            newsSource = args.newsSource
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNewsSources.adapter = adapter
        binding.rvNewsSources.addItemDecoration(MarginItemDecoration(8))
        adapter.setOnClickItemListener(object :ArticleAdapter.OnClickItemListener{
            override fun onItemClick(url: String) {
                findNavController().navigate(ArticlesNewsFragmentDirections.actionArticlesNewsFragmentToDetailArticleFragment(url))
            }

        })
        binding.edtSearch.textChangess().debounce(400)
            .onEach { it ->
                it?.let {
                    if (it.isNotBlank()){
                        viewModel.getFilterArticles(it.toString())
                        lifecycleScope.launch {
                            viewModel.articles?.collectLatest {
                                adapter.submitData(it)
                            }
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)

        viewModel.getArticles(null)
        lifecycleScope.launch {
            viewModel.articles?.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
