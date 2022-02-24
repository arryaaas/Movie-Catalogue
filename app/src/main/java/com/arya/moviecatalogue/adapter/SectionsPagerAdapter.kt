package com.arya.moviecatalogue.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arya.moviecatalogue.ui.favorite.FavoriteItemFragment

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FavoriteItemFragment()
        fragment.arguments = Bundle().apply {
            putInt(FavoriteItemFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

}