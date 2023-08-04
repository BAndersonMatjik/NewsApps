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
import com.bmatjik.myapplication.databinding.FragmentNewsSourcesBinding
import com.bmatjik.myapplication.view.adapter.NewsSourcesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsSourcesFragment : BaseFragment<FragmentNewsSourcesBinding>() {
    private val viewModel by viewModels<NewsSourcesViewModel>()
    private val adapter by lazy {
        NewsSourcesAdapter()
    }
    val args: NewsSourcesFragmentArgs by navArgs()

    companion object {
        fun newInstance() = NewsSourcesFragment()
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsSourcesBinding {
        return FragmentNewsSourcesBinding.inflate(inflater, container, false)
    }

    override fun initView() {
       viewModel.category = args.category
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNewsSources.addItemDecoration(MarginItemDecoration(8))
        binding.rvNewsSources.adapter = adapter
        viewModel.getNewsSources()

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { uiState ->
                if (uiState.isLoading) {
                    binding.progressCircular.visibility = View.VISIBLE
                } else {
                    binding.progressCircular.visibility = View.GONE
                }

                if (uiState.error.isNotBlank()) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Error 🔥")
                        .setMessage(uiState.error)
                        .setNegativeButton("back") { dialog, which ->
                            findNavController().navigateUp()
                            dialog.dismiss()
                        }
                        .setPositiveButton("retry") { dialog, which ->
                            viewModel.getNewsSources()
                            dialog.dismiss()
                        }
                        .show()
                    uiState.error = ""
                }
                adapter.submitList(uiState.newsSources)
            }
        }
    }

}