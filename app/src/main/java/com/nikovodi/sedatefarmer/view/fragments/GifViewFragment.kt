package com.nikovodi.sedatefarmer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nikovodi.sedatefarmer.databinding.FragmentGifViewBinding

class GifViewFragment : Fragment() {

    private lateinit var binding: FragmentGifViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGifViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: GifViewFragmentArgs by navArgs()
        Glide.with(requireActivity()).load(args.url).into(binding.fullViewImage)
        binding.fullViewTitle.text = "Title: ${args.title}"
        binding.fullViewUsername.text = "Author: ${args.username}"
        binding.fullViewRating.text = "Rating: ${args.rating}"

        binding.buttonBack.setOnClickListener {
            findNavController()
                .navigate(GifViewFragmentDirections.actionGifViewFragmentToGifListFragment())
        }
    }
}