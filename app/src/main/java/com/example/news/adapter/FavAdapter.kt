package com.example.news.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsItemBinding
import com.example.news.models.Articles
import com.example.news.models.RoomArticle

class FavAdapter: RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<RoomArticle>() {
        override fun areItemsTheSame(oldItem: RoomArticle, newItem: RoomArticle): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RoomArticle, newItem: RoomArticle): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = differ.currentList[position]
        holder.binding.let {
            it.desc.text = current.description
            it.title.text = current.title
            it.link.text = current.url
//            it.image.setImageURI(Uri.parse(current.urlToImage))

            it.main.setOnClickListener {
                onItemClickListener?.let { it(differ.currentList[position].url) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}