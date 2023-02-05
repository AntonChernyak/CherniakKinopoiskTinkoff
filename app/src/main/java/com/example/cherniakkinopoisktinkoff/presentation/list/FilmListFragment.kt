package com.example.cherniakkinopoisktinkoff.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cherniakkinopoisktinkoff.R
import com.example.cherniakkinopoisktinkoff.databinding.FragmentListBinding
import com.example.cherniakkinopoisktinkoff.presentation.factory.FilmViewModelFactory
import com.example.cherniakkinopoisktinkoff.presentation.list.adapter.FilmsAdapter
import com.example.data.network.FilmApiClient
import com.example.data.repository.FilmDetailsRemoteRepository
import com.example.data.repository.FilmListRemoteRepository
import com.example.domain.interactors.FilmDetailsInteractor
import com.example.domain.interactors.FilmListInteractor


class FilmListFragment : Fragment() {

    private val binding: FragmentListBinding by viewBinding()
    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter()
    }
    /*
    fun Fragment.factory(habitDao: HabitDao, habitApi: HabitApiInterface) = HabitViewModelFactory(
    HabitsListUseCase(HabitsLocalRepository(habitDao), HabitsRemoteListRepository(habitApi)),
    HabitCreatorUseCase(HabitsLocalRepository(habitDao), HabitsRemoteCreatorRepository(habitApi))
)
     */
    private val api by lazy { FilmApiClient.apiClient }
    private val viewModel: FilmListViewModel by viewModels {
        FilmViewModelFactory(
            FilmListInteractor(FilmListRemoteRepository(api)),
            FilmDetailsInteractor(FilmDetailsRemoteRepository(api))
        )
    }
   // private val viewModel = FilmListViewModel(FilmListInteractor(FilmListRemoteRepository(FilmApiClient)))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewSettings()

        viewModel.filmsLiveData.observe(viewLifecycleOwner){ films->
            filmsAdapter.data = films
        }
    }

    private fun setRecyclerViewSettings() {
        binding.filmsRecyclerView.apply {
            adapter = filmsAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

}