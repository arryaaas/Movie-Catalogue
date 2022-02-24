package com.arya.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arya.moviecatalogue.R
import com.arya.moviecatalogue.adapter.ItemAdapter
import com.arya.moviecatalogue.databinding.FragmentFavoriteItemBinding
import com.arya.moviecatalogue.utils.gone
import com.arya.moviecatalogue.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteItemFragment : Fragment() {

    private var _binding: FragmentFavoriteItemBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteItemViewModel by viewModel()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter()

        binding.rvFav.apply {
            setHasFixedSize(true)
            adapter = itemAdapter
        }

        initNotify()

        when (arguments?.getInt(ARG_SECTION_NUMBER, 0)) {
            1 -> {
                viewModel.getFavMovie().observe(viewLifecycleOwner, { movie ->
                    itemAdapter.submitList(movie)
                    if (movie.size == 0) showNotify(true) else showNotify(false)
                })
            }
            2 -> {
                viewModel.getFavTv().observe(viewLifecycleOwner, { tv ->
                    itemAdapter.submitList(tv)
                    if (tv.size == 0) showNotify(true) else showNotify(false)
                })
            }
        }
    }

    private fun initNotify() {
        binding.notifyLayout.apply {
            ivNotify.setImageResource(R.drawable.ic_no_favorite)
            ivNotify.contentDescription = resources.getString(R.string.no_fav_logo)
            tvHeadline.text = resources.getString(R.string.no_fav_headline)
            tvMessage.text = resources.getString(R.string.no_fav_message)
        }
    }

    private fun showNotify(state: Boolean) {
        if (state) {
            binding.rvFav.gone()
            binding.notifyLayout.root.visible()
        } else {
            binding.rvFav.visible()
            binding.notifyLayout.root.gone()
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }

}