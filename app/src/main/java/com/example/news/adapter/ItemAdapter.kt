package com.example.news.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsItemBinding
import com.example.news.models.Articles
import com.squareup.picasso.Picasso

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
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

            Picasso.get().load(current.urlToImage).into(it.image)
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