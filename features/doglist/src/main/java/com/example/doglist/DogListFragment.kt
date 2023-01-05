package com.example.doglist

import DogsAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.doglist.databinding.FragmentDogListBinding
import com.example.utils.models.DogArticle
import di.ArticlesComponent
import room.AppDatabase
import room.RoomArticlesRepository
import javax.inject.Inject

class DogListFragment : Fragment(),DogsAdapter.onArticleListener  {

    @Inject
    internal lateinit var dogArticleViewModelFactory:dagger.Lazy <DogsViewModel.Factory>

    companion object {
        fun newInstance() = DogListFragment()
    }
    private var _binding: FragmentDogListBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding not set" }

    private val dogsViewModel by viewModels<DogsViewModel>()

    private lateinit var database: AppDatabase
    private lateinit var articlesRepository: RoomArticlesRepository

    private val dogAdapter = DogsAdapter(this)

    override fun onAttach(context: Context) {
        //ViewModelProvider(this).get<ArticlesComponent
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater
        .inflate(R.layout.fragment_dog_list, container, false)
        .apply {
            findViewById<RecyclerView>(R.id.jobRecycler).apply {
                adapter= dogAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                setHasFixedSize(true)
            }
            database =  Room.databaseBuilder(context,AppDatabase::class.java,"database.db")
                .createFromAsset("database.db")
                .allowMainThreadQueries()
                .build()
            articlesRepository = RoomArticlesRepository(database.getsArticlesDao())

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDogListBinding.bind(view)


    }

    override fun onDestroyView() {
       // viewModel.state.value=State.DefaultState()
        super.onDestroyView()
    }

    override fun onArticleClick(position: Int) {
      //  viewModel.setFavorite(position)


    }

    override fun onFooterClick() {
        //viewModel.loadData(viewModel.dogArticleList.size)

        // .adapter?.let { viewModel.loadData(it.itemCount - 1) }
    }
}