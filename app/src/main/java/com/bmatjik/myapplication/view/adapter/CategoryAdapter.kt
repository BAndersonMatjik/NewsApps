package com.bmatjik.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bmatjik.myapplication.databinding.TextItemChipBinding

class CategoryAdapter constructor(private val list: ArrayList<String> = arrayListOf()) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(private val view: TextItemChipBinding) : ViewHolder(view.root) {

        fun onBind(title:String) {
            view.chipText.text = title
        }
    }
    fun submit(data:List<String>){
        list.clear()
        list.addAll(data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val textItemChipBinding =
            TextItemChipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(textItemChipBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}