package com.example.doglist

import ArticlesAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.databinding.FragmentDogListBinding
import com.example.utils.models.DogArticle
import di.ArticlesComponentViewModel
import di.ArticlesComponentFactory
import room.AppDatabase
import javax.inject.Inject

class DogListFragment : Fragment(),ArticlesAdapter.onArticleListener  {


  /*  private val component by lazy{
        DaggerArticlesComponent.builder()
            .Build()
    }*/
  @Inject
  lateinit var viewmodelFactory: DogsViewModel.DogsViewModelFactory

    private val dogsViewModel by viewModels<DogsViewModel> { viewmodelFactory }

    companion object {
        fun newInstance() = DogListFragment()
    }
    private var _binding: FragmentDogListBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding not set" }

 /*   private val dogsViewModel by viewModels<DogsViewModel>(){
        component.viewModelFactory()
    }*/

    private lateinit var database: AppDatabase

    private val dogAdapter = ArticlesAdapter(this)

    override fun onAttach(context: Context) {
        ViewModelProvider(this,ArticlesComponentFactory(context)).get<ArticlesComponentViewModel>()
            .component.inject(this)
        //ViewModelProvider(this).get<ArticlesComponent
      /*  val artComponent: ArticlesComponent by lazy {
            DaggerArticlesComponent.builder()
                .Build()

        }
        artComponent.inject(this)*/
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater
        .inflate(R.layout.fragment_dog_list, container, false)
        .apply {
            findViewById<RecyclerView>(R.id.articlesRecycler).apply {
                adapter= dogAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false,
                )
                setHasFixedSize(true)
            }
            dogsViewModel.state.observe(viewLifecycleOwner) {state ->
                when (state) {
                    is State.LoadedState<*> -> {
                        dogAdapter.setData(state.data as MutableList<DogArticle>)
                    }
                    is State.LoadedItemState<*> ->
                    {
                        dogAdapter.insertItem(state.item as DogArticle)
                    }
                    is State.LoadedImgState<*> ->
                    {
                        dogAdapter.updateImg(state.item as DogArticle)
                    }
                    is State.LoadedFactsState<*> ->
                    {
                        dogAdapter.updateFacts(state.item as DogArticle)
                    }
                    else ->{

                    }
                }

            }
//            database =  Room.databaseBuilder(context,
//                com.example.dog_observer.room.AppDatabase::class.java,"database.db")
//                .createFromAsset("database.db")
//                .allowMainThreadQueries()
//                .build()

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDogListBinding.bind(view)


    }

    override fun onDestroyView() {
       // viewModel.state.value=State.DefaultState()
        _binding=null
        super.onDestroyView()
    }

    override fun onArticleClick(position: Int) {
      //  viewModel.setFavorite(position)
        dogsViewModel.insertItem(position)


    }

    override fun onFooterClick() {
        dogsViewModel.loadData(dogsViewModel.dogArticleList.size)

        // .adapter?.let { viewModel.loadData(it.itemCount - 1) }
    }
}