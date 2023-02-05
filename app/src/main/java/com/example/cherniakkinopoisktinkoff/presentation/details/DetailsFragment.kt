package com.example.cherniakkinopoisktinkoff.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cherniakkinopoisktinkoff.R
import com.example.cherniakkinopoisktinkoff.databinding.FragmentDetailsBinding
import com.example.cherniakkinopoisktinkoff.presentation.extensions.loadImage
import com.example.cherniakkinopoisktinkoff.presentation.factory.FilmViewModelFactory
import com.example.cherniakkinopoisktinkoff.presentation.list.FilmListFragment.Companion.ID_KEY
import com.example.data.network.FilmApiClient
import com.example.data.repository.FilmDetailsRemoteRepository
import com.example.data.repository.FilmListRemoteRepository
import com.example.domain.interactors.FilmDetailsInteractor
import com.example.domain.interactors.FilmListInteractor

class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding by viewBinding()
    private val api by lazy { FilmApiClient.apiClient }
    private val viewModel: FilmDetailsViewModel by viewModels {
        FilmViewModelFactory(
            FilmListInteractor(FilmListRemoteRepository(api)),
            FilmDetailsInteractor(FilmDetailsRemoteRepository(api))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getString(ID_KEY, "0")
        viewModel.getFilmById(id)

        setDataIntoViews()
    }

    private fun setDataIntoViews() {
        viewModel.filmLiveData.observe(viewLifecycleOwner) { film ->
            binding.detailsTitleTextView.text = film.title
            binding.detailsDescriptionTextView.text = film.description
            binding.countriesTextView.text = film.countries.joinToString { it.country }
            binding.genresTextView.text = film.genres.joinToString { it.genre }
            binding.detailsPosterImageView.loadImage(film.poster)
        }
    }
}