package com.arya.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.arya.moviecatalogue.R
import com.arya.moviecatalogue.adapter.ItemAdapter
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.databinding.FragmentTvBinding
import com.arya.moviecatalogue.ui.home.MainActivity
import com.arya.moviecatalogue.utils.SortUtils.RATING
import com.arya.moviecatalogue.utils.SortUtils.RELEASE_DATE
import com.arya.moviecatalogue.utils.SortUtils.TITLE
import com.arya.moviecatalogue.utils.gone
import com.arya.moviecatalogue.utils.visible
import com.arya.moviecatalogue.vo.Resource
import com.arya.moviecatalogue.vo.Status
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvViewModel by viewModel()
    private lateinit var itemAdapter: ItemAdapter

    private var checkedItem = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.elevation = 4F

        itemAdapter = ItemAdapter()

        binding.rvTv.apply {
            setHasFixedSize(true)
            adapter = itemAdapter
        }

        selectSort(TITLE)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sort) showDialog()
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        val singleItems = arrayOf("Title", "Release Date", "Rating")

        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.sort_by)
            .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                checkedItem = which
                when (which) {
                    0 -> selectSort(TITLE)
                    1 -> selectSort(RELEASE_DATE)
                    2 -> selectSort(RATING)
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun selectSort(sort: String) {
        viewModel.getTv(sort).observe(viewLifecycleOwner, tvObserver)
    }

    private val tvObserver = Observer<Resource<PagedList<MovieEntity>>> { tv ->
        if (tv != null) {
            when (tv.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    showNotify(false)
                    itemAdapter.submitList(tv.data) { binding.rvTv.scrollToPosition(0) }
                }
                Status.ERROR -> {
                    showLoading(false)
                    showNotify(true)
                }
            }
        }
    }

    private fun showNotify(state: Boolean) {
        if (state) {
            binding.notifyLayout.root.visible()
        } else {
            binding.notifyLayout.root.gone()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.rvTv.gone()
            binding.shimmerLayout.visible()
            binding.shimmerLayout.startShimmer()
        } else {
            binding.rvTv.visible()
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.gone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}