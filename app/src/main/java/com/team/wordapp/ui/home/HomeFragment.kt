package com.team.wordapp.ui.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.wordapp.databinding.FragmentHomeBinding
import com.team.wordapp.ui.adapter.WordAdapter
import kotlinx.coroutines.launch

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        lifecycleScope.launch {
            viewModel.words.collect {
                adapter.setWords(it)
            }
        }

        binding.fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddWordFragment()
            findNavController().navigate(action)
        }

        setFragmentResultListener("manage_word") {_, _ -> viewModel.getWords()}
    }

    fun setupAdapter() {
        adapter = WordAdapter(emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToWordDetailsFragment(it.id!!)
            findNavController().navigate(action)
        }
        binding.rvWords.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWords.adapter = adapter
    }
}