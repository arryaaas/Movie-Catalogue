package com.arya.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.arya.moviecatalogue.R
import com.arya.moviecatalogue.adapter.SectionsPagerAdapter
import com.arya.moviecatalogue.databinding.FragmentFavoriteBinding
import com.arya.moviecatalogue.ui.home.MainActivity
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)

        binding.viewPager.apply {
            adapter = sectionsPagerAdapter
            getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        }

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        (activity as MainActivity).supportActionBar?.elevation = 0F
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show
        )
    }

}