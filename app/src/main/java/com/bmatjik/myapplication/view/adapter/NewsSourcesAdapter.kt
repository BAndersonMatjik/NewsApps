package com.bmatjik.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bmatjik.myapplication.databinding.SourceItemBinding
import com.bmatjik.myapplication.feature.model.NewsSource
import com.bumptech.glide.Glide

class NewsSourcesAdapter : ListAdapter<NewsSource, NewsSourcesAdapter.NewsSourceViewHolder>(NewsSourceDiffUtil()) {

    class NewsSourceDiffUtil : DiffUtil.ItemCallback<NewsSource>() {
        override fun areItemsTheSame(oldItem: NewsSource, newItem: NewsSource): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: NewsSource, newItem: NewsSource): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class NewsSourceViewHolder(private val sourceItemBinding: SourceItemBinding) : ViewHolder(sourceItemBinding.root) {
        fun onBind(newsSource: NewsSource){
            sourceItemBinding.apply {
                tvTitle.text = newsSource.name
                tvDescription.text = newsSource.description
                Glide.with(sourceItemBinding.root.context).load("https://flagcdn.com/w80/${newsSource.country}.png").into(imgCountry)
                tvCategory.text = newsSource.category.uppercase()
            }
        }
        fun onBindNothing(){
            sourceItemBinding.root.visibility = View.GONE
        }
    }
    private var _realSize = 0
    val realSize get() = _realSize
    override fun submitList(list: List<NewsSource>?) {
        super.submitList(list)

        _realSize = list?.size?:0

    }

    override fun getItemCount(): Int {
        if (realSize==0){
            return super.getItemCount()
        }else{
            return Int.MAX_VALUE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        return NewsSourceViewHolder(SourceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        if (currentList.size>0){
            holder.onBind(getItem(position % this.currentList.size))
        }
    }
}
