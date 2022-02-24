package com.arya.moviecatalogue.adapter

import androidx.recyclerview.widget.DiffUtil
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity

class ItemDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {

    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

}