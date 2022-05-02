package com.example.taskappkotlin.presentation.fragment.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.taskappkotlin.App
import com.example.taskappkotlin.R
import com.example.taskappkotlin.databinding.ActivityMainBinding
import com.example.taskappkotlin.presentation.fragment.add.AddActivity
import com.example.taskappkotlin.presentation.fragment.main.viewModel.MainViewModel
import com.example.taskappkotlin.presentation.fragment.main.viewModel.MainViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    //    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterMain: MainAdapter

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        (applicationContext as App).shopComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)
            .get(MainViewModel::class.java)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.getShopList.collectLatest {
                adapterMain.submitList(it)
            }
        }
    }

    private fun initRecycler() {
        with(binding.recycler) {
            adapterMain = MainAdapter()
            adapter = adapterMain
        }
        setupSwipeListeners(binding.recycler)
    }

    private fun setupSwipeListeners(rv: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapterMain.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun initListeners() {
        adapterMain.onShopItemLongClickListener = {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.fab.setOnClickListener {
            Intent(this, AddActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}