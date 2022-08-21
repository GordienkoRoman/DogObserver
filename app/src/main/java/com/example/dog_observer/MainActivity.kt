package com.example.dog_observer

import DogsAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_observer.viewmodels.DogsViewModel
import com.example.dog_observer.models.DogArticle
import com.example.dog_observer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main), DogsAdapter.OnItemClickListener {
    private lateinit var dogsAdapter: DogsAdapter

    // @Inject
    private lateinit var viewModel: DogsViewModel

    // private lateinit var binding: ActivityMainBinding
    private val recyclerJob: RecyclerView by lazy {
        findViewById(R.id.jobRecycler)
    }

    /* val Context.appComponent: AppComponent
         get() = when (this) {
             is App -> appComponent
             else -> applicationContext.appComponent
         }*/
    lateinit var factory: ViewModelProvider.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  appComponent.inject(this)
        // val appCompoonent:AppCompoonent = DaggerAppCompoonent.create()
        //viewModel=appCompoonent.getViewModel()
        viewModel = ViewModelProvider(this).get(DogsViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewmodel=viewModel
        }
        // viewModel = DogsViewModel(DogArticlesLoadImpl())
        // binding.viewModel = viewModel
        setupAdapter()
        // viewModel = ViewModelProvider(this).get(DogsViewModel::class.java)
        viewModel.state.observe(
            this@MainActivity
        ) { state ->
            when (state) {
                is State.LoadedState<*> -> {
                    dogsAdapter.setData(state.data as MutableList<DogArticle>)
                }
                else -> {

                }
            }

        }
        viewModel.loadData(0)

    }

    fun setupAdapter() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dogsAdapter = DogsAdapter()
        recyclerJob.layoutManager = layoutManager
        dogsAdapter.setOnItemClickListener(this)
        recyclerJob.adapter = dogsAdapter
    }


    override fun onItemClick(position: Int) {
        viewModel.setFavorite(position)
    }

    override fun onFooterClick() {
        recyclerJob.adapter?.let { viewModel.loadData(it.itemCount - 1) }
    }


}