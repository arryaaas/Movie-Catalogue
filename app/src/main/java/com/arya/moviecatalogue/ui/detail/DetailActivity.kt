package com.arya.moviecatalogue.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.arya.moviecatalogue.BuildConfig.URL_BACKDROP
import com.arya.moviecatalogue.BuildConfig.URL_POSTER
import com.arya.moviecatalogue.R
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.databinding.ActivityDetailBinding
import com.arya.moviecatalogue.utils.*
import com.arya.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val type = intent.getStringExtra(EXTRA_TYPE)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        when (type) {
            "movie" -> {
                viewModel.setSelectedMovie(id)
                viewModel.movieDetail.observe(this, { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                showLoading(false)
                                movie.data?.let { populateDetail(it) }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                            }
                        }
                    }
                })
            }
            "tv" -> {
                viewModel.setSelectedTv(id)
                viewModel.tvDetail.observe(this, { tv ->
                    if (tv != null) {
                        when (tv.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                showLoading(false)
                                tv.data?.let { populateDetail(it) }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateDetail(movieEntity: MovieEntity) {
        binding.apply {
            collapsingToolbar.title = movieEntity.type?.let { formatToolbarTitle(it) }

            this@DetailActivity.loadImage(URL_BACKDROP + movieEntity.backdropPath, binding.ivBackdrop)
            this@DetailActivity.loadImage(URL_POSTER + movieEntity.posterPath, binding.ivPoster)

            tvTitle.text = movieEntity.title
            tvRelease.text = movieEntity.releaseDate?.let { formatDate(it, "MMM dd, yyyy") }
            tvGenres.text = movieEntity.genres
            tvRuntime.text = movieEntity.runtime?.let { formatRuntime(it) }
            tvLanguage.text = movieEntity.language
            tvRating.text = movieEntity.rating?.let { formatRating(it) }
            tvOverview.text = movieEntity.overview?.let { formatOverview(it) }

            tvOverview.collapsingTextView()

            btnFavorite.setOnClickListener {
                if (movieEntity.type == "movie") viewModel.setFavoriteMovie()
                else viewModel.setFavoriteTv()
            }

            btnShare.setOnClickListener {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, movieEntity.homepage)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            setFavoriteState(movieEntity.isFavorite)
        }
    }

    private fun setFavoriteState(state: Boolean) {
        binding.btnFavorite.icon = if (state) {
            ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.ivBackdrop.gone()
            binding.ivBackdropShadow.gone()
            binding.content.gone()
            binding.shimmerLayout.visible()
            binding.shimmerLayout.startShimmer()
        } else {
            binding.ivBackdrop.visible()
            binding.ivBackdropShadow.visible()
            binding.content.visible()
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.gone()
        }
    }

    companion object {
        const val EXTRA_TYPE = "type"
        const val EXTRA_ID = "id"
    }

}