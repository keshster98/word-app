package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.team.wordapp.databinding.FragmentBaseManageBinding
import kotlinx.coroutines.launch

class BaseManageFragment : Fragment() {

    private lateinit var binding: FragmentBaseManageBinding
    private val viewModel: BaseManageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseManageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Tell the home fragment or wherever you want to notify that you have made some changes
        lifecycleScope.launch {
            viewModel.finish.collect {
                setFragmentResult("manage_word", Bundle())
            }
        }
        //Catch the error and call the snackbar function
        lifecycleScope.launch {
            viewModel.error.collect {
                showSnackbar(it)
            }
        }
    }
    //Function to show the snackbar and put the error msg in it
    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg , Snackbar.LENGTH_LONG).show()
    }
}