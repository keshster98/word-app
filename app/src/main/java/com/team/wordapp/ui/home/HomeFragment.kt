package com.team.wordapp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.team.wordapp.databinding.FragmentHomeBinding
import com.team.wordapp.ui.home.nestedHome.Home1Fragment
import com.team.wordapp.ui.home.nestedHome.Home2Fragment
import com.team.wordapp.ui.home.nestedHome.TabsAdapter
import com.team.wordapp.R


class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

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

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchState = s.toString()
                viewModel.refresh()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.ivSort.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSortFragment(
                viewModel.sortState1,
                viewModel.sortState2
            )
            findNavController().navigate(action)
        }

        binding.fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddWordFragment()
            findNavController().navigate(action)
        }

        val adapter = TabsAdapter(
            fragments = listOf(Home1Fragment(), Home2Fragment()),
            fragment = this
        )

        binding.vpTabs.adapter = adapter

        TabLayoutMediator(binding.tlTabs, binding.vpTabs) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.new_word)
                else -> tab.text = getString(R.string.completed_word)
            }
        }.attach()

        setFragmentResultListener("manage_word") {_, _ -> viewModel.refresh()}
        setFragmentResultListener("manage_sort") { _, bundle ->
            viewModel.run {
                sortState1 = bundle.getInt("sort1")
                sortState2 = bundle.getInt("sort2")
                refresh()
            }
        }
    }
}