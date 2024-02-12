package com.example.pixabayphoto.features.search

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pixabayphoto.MainApplication
import com.example.pixabayphoto.R
import com.example.pixabayphoto.databinding.FragmentSearchBinding
import com.example.pixabayphoto.domain.Photo
import com.example.pixabayphoto.utils.collectLifecycleFlow
import com.example.pixabayphoto.features.search.adapter.PhotosAdapter
import com.example.pixabayphoto.features.search.adapter.SearchSuggestionAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hamedsafari.filckrphotos.utils.KEY_LARGE_IMAGE_URL
import com.hamedsafari.filckrphotos.utils.KEY_USER
import com.hamedsafari.filckrphotos.utils.KEY_LIKES
import com.hamedsafari.filckrphotos.utils.KEY_COMMENTS
import com.hamedsafari.filckrphotos.utils.KEY_DOWNLOADS

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel> {
        val appContainer = (activity?.application as MainApplication).appContainer
        appContainer.searchViewModelFactory
    }

    private lateinit var photoAdapter: PhotosAdapter
    private lateinit var suggestionAdapter: SearchSuggestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setupRecyclerView()
        setupObservers()
    }

    private fun setUpUi() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchTerms.value = newText
                return true
            }
        })
    }

    private fun setupRecyclerView() {

        suggestionAdapter = SearchSuggestionAdapter {
            binding.search.onActionViewExpanded()
            binding.search.setQuery(it, false)
        }

        photoAdapter = PhotosAdapter {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(getString(R.string.would_you_like_to_see_more_details))
                .setPositiveButton(getString(R.string.yes)) { _, _ ->
                    navigateToDetailPage(it)
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                .show()

        }

        binding.recyclerView.apply {
            adapter = photoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.recyclerViewSuggestions.apply {
            adapter = suggestionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun navigateToDetailPage(it: Photo) {
        findNavController().navigate(
            R.id.action_to_DetailFragment,
            bundleOf(
                KEY_LARGE_IMAGE_URL to it.largeImageURL,
                KEY_USER to it.user,
                KEY_LIKES to it.likes_formated,
                KEY_COMMENTS to it.comments_formated,
                KEY_DOWNLOADS to it.downloads_formated
            )
        )
    }

    private fun setupObservers() {

        collectLifecycleFlow(viewModel.uiState) { state ->
            when (state) {
                is SearchUiState.HasSearchInput -> {
                    photoAdapter.submitList(state.photos)
                }

                is SearchUiState.NoSearchInput -> {
                    suggestionAdapter.submitList(state.suggestions)
                    photoAdapter.submitList(emptyList())
                    binding.recyclerViewSuggestions.scrollToPosition(0)
                }
            }

            binding.recyclerViewSuggestions.isVisible = state is SearchUiState.NoSearchInput &&
                    state.isLoading.not() &&
                    state.errorMessages.isEmpty()
            binding.recyclerView.isVisible = state is SearchUiState.HasSearchInput &&
                    state.errorMessages.isEmpty()
            binding.progress.isVisible = state.isLoading

            binding.errorMessage.text = state.errorMessages
            binding.errorMessage.isVisible =
                state.errorMessages.isNotEmpty() && state.isLoading.not()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
