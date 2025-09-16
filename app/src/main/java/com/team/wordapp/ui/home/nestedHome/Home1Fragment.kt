package com.team.wordapp.ui.home.nestedHome

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.wordapp.databinding.FragmentHome1Binding
import com.team.wordapp.ui.adapter.WordAdapter
import com.team.wordapp.ui.home.HomeFragmentDirections
import com.team.wordapp.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class Home1Fragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )
    private lateinit var adapter: WordAdapter
    private lateinit var binding: FragmentHome1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHome1Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        lifecycleScope.launch {
            viewModel.notCompletedWords.collect {
                if (viewModel.notCompletedSize()) {
                    binding.emptyView1.visibility = View.VISIBLE
                } else {
                    binding.emptyView1.visibility = View.GONE
                }
                adapter.setWords(it)
            }
        }
    }

    fun setupAdapter() {
        adapter = WordAdapter(emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToWordDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.rvWords1.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWords1.adapter = adapter
    }
}