package favourites

import ArticlesFavAdapter
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.example.utils.models.DogArticle
import di.ArticlesComponentFactory
import di.ArticlesComponentViewModel
import javax.inject.Inject

class FavouritesFragment : Fragment(),ArticlesFavAdapter.onArticleListener {

    companion object {
        fun newInstance() = FavouritesFragment()
    }
    @Inject
    lateinit var viewmodelFactory: FavouritesViewModel.FavouritessViewModelFactory
    private val  viewModel by viewModels<FavouritesViewModel> { viewmodelFactory }
    private val favArticlesAdapter = ArticlesFavAdapter(this)

    override fun onAttach(context: Context) {
        ViewModelProvider(this, ArticlesComponentFactory(context)).get<ArticlesComponentViewModel>()
            .component.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
            .apply {
                findViewById<RecyclerView>(R.id.articlesFavRecycler).apply {
                    adapter = favArticlesAdapter
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false,
                    )
                    setHasFixedSize(true)
                }

                viewModel.state.observe(viewLifecycleOwner) { state ->
                    when (state) {
                        is State.LoadedState<*> -> {
                            favArticlesAdapter.setData(state.data as MutableList<DogArticle>)
                        }
                        is State.DeletedState<*> ->{
                            if(state.position is Int)
                               favArticlesAdapter.deleteItem(state.position)

                        }
                        else -> {

                        }
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadArticles()
    }
    override fun onArticleClick(position: Int) {
        viewModel.deleteArticle(position)
    }

}