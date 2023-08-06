package com.bmatjik.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.bmatjik.myapplication.common.BaseFragment
import com.bmatjik.myapplication.databinding.FragmentDetailArticleBinding

class DetailArticleFragment : BaseFragment<FragmentDetailArticleBinding>() {

    private val args: DetailArticleFragmentArgs by navArgs()
    private var url:String = ""
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailArticleBinding {
        return FragmentDetailArticleBinding.inflate(inflater,container,false)
    }

    override fun initView() {
        url = args.url
        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl(url)
    }

}