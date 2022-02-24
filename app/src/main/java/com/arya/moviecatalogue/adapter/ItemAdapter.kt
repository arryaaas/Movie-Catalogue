package com.arya.moviecatalogue.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arya.moviecatalogue.BuildConfig.URL_POSTER
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.databinding.ItemListBinding
import com.arya.moviecatalogue.ui.detail.DetailActivity
import com.arya.moviecatalogue.utils.formatDate
import com.arya.moviecatalogue.utils.loadImage

class ItemAdapter : PagedListAdapter<MovieEntity, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        return ItemViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    inner class ItemViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity) {
            binding.apply {
                itemView.context.loadImage(URL_POSTER + movie.posterPath, binding.ivPoster)

                tvTitle.text = movie.title
                tvRelease.text = movie.releaseDate?.let { formatDate(it, "MMM dd, yyyy") }
                ratingBar.rating = movie.rating?.div(2F) ?: 0F
                tvRating.text = movie.rating.toString()

                itemView.setOnClickListener {
                    Log.d("Movie Adapter", "Item Clicked")
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_TYPE, movie.type)
                        putExtra(DetailActivity.EXTRA_ID, movie.id)
                    }
                    itemView.context.startActivity(intent)
                }

            }
        }

    }

}
