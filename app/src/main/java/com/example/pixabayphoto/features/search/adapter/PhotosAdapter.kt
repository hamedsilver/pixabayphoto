package com.example.pixabayphoto.features.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabayphoto.databinding.ItemSearchTermsBinding
import com.example.pixabayphoto.domain.Photo
import com.example.pixabayphoto.utils.ImageLoader

class PhotosAdapter(private val onItem: (Photo) -> Unit) :
    ListAdapter<Photo, PhotosAdapter.PhotosViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemSearchTermsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItem) }
    }

    inner class PhotosViewHolder(private val view: ItemSearchTermsBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(item: Photo, onItem: (Photo) -> Unit) {
            ImageLoader.loadImage(view.image, item.thumbnailUrl)
            view.title.text = item.user
            view.root.setOnClickListener { onItem(item) }
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id && oldItem.largeImageURL == newItem.largeImageURL
    }
}
