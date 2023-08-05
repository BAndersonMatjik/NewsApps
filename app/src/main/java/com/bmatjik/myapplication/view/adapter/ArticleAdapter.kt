package com.bmatjik.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.databinding.SourceItemBinding
import com.bmatjik.myapplication.feature.model.Article

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

    class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    class ArticleViewHolder(private val sourceItemBinding: SourceItemBinding) :
        ViewHolder(sourceItemBinding.root) {
        fun onBind(newsSource: Article) {
            sourceItemBinding.apply {
                tvTitle.text = newsSource.title
                tvDescription.text = newsSource.description

//                Glide.with(sourceItemBinding.root.context).load(Constants.FlagUrl.replace("#img",newsSource.country.lowercase().let {
//                    if (it == "zh"){
//                         "cn"
//                    }else{
//                        it
//                    }
//                })).into(imgCountry)
                tvCategory.text = newsSource.source
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            SourceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if (realListSize>0){
            getItem(position%realListSize)?.apply {
                holder.onBind(this)
            }
        }
    }


}
