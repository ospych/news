package com.example.news.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.adapter.ItemAdapter
import com.example.news.databinding.FragmentMainBinding
import com.example.news.models.RoomArticle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { ItemAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllNews()
        readDetail()
        insert()
    }

    private fun getAllNews() {
        viewModel.news.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it.articles)
        }
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun readDetail() {
        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("url", it)
            }
            findNavController().navigate(R.id.detailFragment, bundle)
        }
    }

    private fun insert() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.differ.currentList[position]
                val roomArticle = RoomArticle(
                    null,
                    item.author,
                    item.title,
                    item.description,
                    item.url,
                    item.urlToImage,
                    item.publishedAt,
                    item.content
                )
                viewModel.insert(roomArticle)
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recycler)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}