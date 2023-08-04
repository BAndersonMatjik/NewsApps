package com.bmatjik.myapplication.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T:ViewBinding>:Fragment() {
    private var _binding: T? = null
    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = _binding!!

    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): T

    //call in onCreateView because we cannot override onCreateView but we can extend with initView
    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater,container)
        initView()
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}