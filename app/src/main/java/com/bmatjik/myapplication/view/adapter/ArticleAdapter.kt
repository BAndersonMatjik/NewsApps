package com.bmatjik.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.databinding.ArticleItemBinding
import com.bmatjik.myapplication.feature.model.Article
import com.bumptech.glide.Glide

class ArticleAdapter :
    PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffUtil()) {
    private var realListSize: Int = 0
    override fun getItemCount(): Int {
        realListSize = super.getItemCount()
        if (realListSize==0 || realListSize<Constants.NEWS_SOURCE_MINIMAL_PAGE_SIZE){
            return super.getItemCount()
        }
        return Int.MAX_VALUE
    }
    private var onClickItemListener:OnClickItemListener?=null

    fun setOnClickItemListener(onClickItemListener: OnClickItemListener){
        this.onClickItemListener = onClickItemListener
    }

    class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class ArticleViewHolder(private val sourceItemBinding: ArticleItemBinding,private val onClickItemListener: OnClickItemListener?) :
        ViewHolder(sourceItemBinding.root) {
        fun onBind(newsSource: Article) {
            sourceItemBinding.apply {
                tvTitle.text = newsSource.title
                tvDescription.text = newsSource.description
                Glide.with(sourceItemBinding.root.context).load(newsSource.urlToImage).into(imgNews)
                tvCategory.text = newsSource.source
                root.setOnClickListener {
                    onClickItemListener?.onItemClick(newsSource.url)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ArticleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickItemListener
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if (realListSize>0){
            getItem(position%realListSize)?.apply {
                holder.onBind(this)
            }
        }
    }

    interface OnClickItemListener{
        fun onItemClick(url:String)
    }


}
