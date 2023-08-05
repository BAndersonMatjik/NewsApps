package com.bmatjik.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.common.Constants.NEWS_SOURCE_MINIMAL_PAGE_SIZE
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
    private var onItemClickListener:OnItemClickListener? = null
    fun setOnClickItemListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
    class NewsSourceViewHolder(private val sourceItemBinding: SourceItemBinding,private val onItemClickListener: OnItemClickListener?) : ViewHolder(sourceItemBinding.root) {
        fun onBind(newsSource: NewsSource){
            sourceItemBinding.apply {
                tvTitle.text = newsSource.name
                tvDescription.text = newsSource.description

                Glide.with(sourceItemBinding.root.context).load(Constants.FlagUrl.replace("#img",newsSource.country.lowercase().let {
                    if (it == "zh"){
                         "cn"
                    }else{
                        it
                    }
                })).into(imgCountry)
                tvCategory.text = newsSource.category.uppercase()
                root.setOnClickListener {
                    onItemClickListener?.onClickItem(newsSource = newsSource.id)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return if (this.currentList.size==0 || this.currentList.size<NEWS_SOURCE_MINIMAL_PAGE_SIZE){
            super.getItemCount()
        }else{
            Int.MAX_VALUE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        return NewsSourceViewHolder(SourceItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),onItemClickListener)
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        if (currentList.size>0){
            holder.onBind(getItem(position % this.currentList.size))
        }
    }

    interface OnItemClickListener{
        fun onClickItem(newsSource:String)
    }
}
