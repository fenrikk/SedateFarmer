package com.nikovodi.sedatefarmer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nikovodi.sedatefarmer.databinding.FragmentGifListBinding
import com.nikovodi.sedatefarmer.view.adapter.GifAdapter
import com.nikovodi.sedatefarmer.viewmodel.GifListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GifListFragment : Fragment() {

    private lateinit var binding: FragmentGifListBinding
    private val model by viewModel<GifListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGifListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifAdapter = GifAdapter(requireContext(), onItemClicked = {
            val action = GifListFragmentDirections.listToFullView(
                it.id,
                it.title,
                it.username,
                it.rating,
                it.images.fixed_height.url
            )
            findNavController().navigate(action)
        },
            onEndReached = {
                model.fetchGifList()
            })
        binding.fragmentGifListRecycleView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = gifAdapter
        }

        model.getGifData().observe(requireActivity()) {
            gifAdapter.submitList(it)
        }
    }

}