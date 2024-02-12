package com.example.pixabayphoto.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pixabayphoto.R
import com.example.pixabayphoto.databinding.FragmentDetailBinding
import com.example.pixabayphoto.utils.ImageLoader
import com.hamedsafari.filckrphotos.utils.KEY_USER
import com.hamedsafari.filckrphotos.utils.KEY_LIKES
import com.hamedsafari.filckrphotos.utils.KEY_COMMENTS
import com.hamedsafari.filckrphotos.utils.KEY_DOWNLOADS
import com.hamedsafari.filckrphotos.utils.KEY_LARGE_IMAGE_URL
import java.text.NumberFormat
import java.util.Locale


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = arguments?.getString(KEY_LARGE_IMAGE_URL).orEmpty()
        val user = arguments?.getString(KEY_USER).orEmpty()
        val likes = arguments?.getString(KEY_LIKES).orEmpty()
        val comments = arguments?.getString(KEY_COMMENTS).orEmpty()
        val downloads = arguments?.getString(KEY_DOWNLOADS).orEmpty()


        ImageLoader.loadImage(binding.image, imageUrl)
        binding.user.text = user
        binding.details.text =
            getString(R.string.likes_comments_downloads, likes, comments, downloads)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}