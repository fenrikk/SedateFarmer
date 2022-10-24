package com.nikovodi.sedatefarmer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nikovodi.sedatefarmer.data.model.GiphyData
import com.nikovodi.sedatefarmer.databinding.GifItemBinding
import com.nikovodi.sedatefarmer.other.RENDER_DISTANCE

class GifAdapter(
    private val context: Context,
    private val onItemClicked: (GiphyData) -> Unit,
    private val onEndReached: () -> Unit
) :
    ListAdapter<GiphyData, GifAdapter.GifViewHolder>(GifDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            GifItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        if (itemCount - position == RENDER_DISTANCE) {
            onEndReached()
        }
        Glide.with(context)
            .load(getItem(position).images.fixed_height.url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.binding.itemImage)
        holder.binding.itemTitle.text = getItem(position).title
        holder.binding.root.setOnClickListener {
            onItemClicked(getItem(position))
        }
    }

    class GifViewHolder(val binding: GifItemBinding) : RecyclerView.ViewHolder(binding.root)
}

class GifDiffCallBack : DiffUtil.ItemCallback<GiphyData>() {
    override fun areItemsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}