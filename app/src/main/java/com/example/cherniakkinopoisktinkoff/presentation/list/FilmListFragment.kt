package com.example.cherniakkinopoisktinkoff.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cherniakkinopoisktinkoff.R
import com.example.cherniakkinopoisktinkoff.databinding.FragmentListBinding
import com.example.cherniakkinopoisktinkoff.presentation.extensions.checkNetworkConnectAll
import com.example.cherniakkinopoisktinkoff.presentation.factory.FilmViewModelFactory
import com.example.cherniakkinopoisktinkoff.presentation.list.adapter.FilmsAdapter
import com.example.data.network.FilmApiClient
import com.example.data.repository.FilmDetailsRemoteRepository
import com.example.data.repository.FilmListRemoteRepository
import com.example.domain.interactors.FilmDetailsInteractor
import com.example.domain.interactors.FilmListInteractor
import com.example.domain.interactors.UIStateEnum
import com.example.domain.models.FilmItemDto
import kotlinx.coroutines.launch


class FilmListFragment : Fragment() {

    private val binding: FragmentListBinding by viewBinding()
    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter { pos -> openFilmDetails(pos) }
    }
    private var items = listOf<FilmItemDto>()

    private val api by lazy { FilmApiClient.apiClient }
    private val viewModel: FilmListViewModel by viewModels {
        FilmViewModelFactory(
            FilmListInteractor(FilmListRemoteRepository(api)),
            FilmDetailsInteractor(FilmDetailsRemoteRepository(api))
        )
    }
    private var pageCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewSettings()
        pageCount = savedInstanceState?.getInt(PAGE_KEY, 1) ?: 1

        viewModel.filmsLiveData.observe(viewLifecycleOwner) { films ->
            binding.filmsRecyclerView.scrollToPosition(0)
            items = films
            filmsAdapter.data = items
        }

        setNetworkListener()
        setUiStateLiveDataObserver()

        binding.repeatButton.setOnClickListener {
            viewModel.getTopFilms(page = pageCount)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PAGE_KEY, pageCount)
    }


    private fun setRecyclerViewSettings() {
        binding.filmsRecyclerView.apply {
            adapter = filmsAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                    .apply { setPagination(this) }
        }
    }

    private fun openFilmDetails(position: Int) {
        val id = items[position].id.toString()
        val bundle = Bundle().apply { putString(ID_KEY, id) }

        findNavController().navigate(R.id.action_filmListFragment_to_detailsFragment, bundle)

    }

    private fun setPagination(filmLayoutManager: LinearLayoutManager) {
        binding.filmsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (filmLayoutManager.findLastVisibleItemPosition() == items.size - 1) {
                    pageCount += 1
                    viewModel.getTopFilms(page = pageCount)
                }
            }
        })
    }

    private fun setUiStateLiveDataObserver() {
        viewModel.getUiState().observe(viewLifecycleOwner) { uiState ->
            updateUiState(uiState)
        }
    }

    private fun updateUiState(uiStateEnum: UIStateEnum) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                when (uiStateEnum) {
                    UIStateEnum.NETWORK_ERROR -> {
                        binding.errorBlock.visibility = View.VISIBLE
                    }
                    UIStateEnum.NETWORK_AVAILABLE -> {
                        binding.errorBlock.visibility = View.GONE
                    }
                    UIStateEnum.START_LOADING -> {
                        binding.listProgressBar.visibility = View.VISIBLE
                    }
                    UIStateEnum.END_LOADING -> {
                        binding.listProgressBar.visibility = View.GONE
                    }
                    UIStateEnum.DEFAULT_STATE -> {
                        binding.errorBlock.visibility = View.GONE
                        binding.listProgressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setNetworkListener() {
        if (this@FilmListFragment.checkNetworkConnectAll()) {
            updateUiState(UIStateEnum.NETWORK_AVAILABLE)
        } else updateUiState(UIStateEnum.NETWORK_ERROR)
    }

    companion object {
        private const val PAGE_KEY = "page_key"
        const val ID_KEY = "id_key"
    }

}