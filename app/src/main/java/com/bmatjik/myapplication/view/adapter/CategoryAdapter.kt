package com.bmatjik.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bmatjik.myapplication.databinding.TextChipItemBinding
import timber.log.Timber

class CategoryAdapter constructor(private val list: ArrayList<String> = arrayListOf()) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var onClickItem:OnClickItem?=null
    class CategoryViewHolder(private val view: TextChipItemBinding,private val onClickItem: OnClickItem?) : ViewHolder(view.root) {

        fun onBind(title:String) {
            view.chipText.text = title
            view.chipText.setOnClickListener {
                onClickItem?.onClickItem(title)
                Timber.d("test $title")
            }
        }
    }
    fun submit(data:List<String>){
        list.clear()
        list.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val textItemChipBinding =
            TextChipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(textItemChipBinding,onClickItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun setListenerItem(onClickItem: OnClickItem){
        this.onClickItem = onClickItem
    }

    interface OnClickItem{
        fun onClickItem(item:String)
    }
}