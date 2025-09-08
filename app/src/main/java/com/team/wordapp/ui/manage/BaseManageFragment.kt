package com.team.wordapp.ui.manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.team.wordapp.R
import com.team.wordapp.databinding.FragmentBaseManageBinding
import kotlinx.coroutines.launch

abstract class BaseManageFragment : Fragment() {
    protected lateinit var binding: FragmentBaseManageBinding
    protected abstract val viewModel: BaseManageViewModel

    protected abstract fun getManageWordPageTitle(): String

    protected abstract fun getManageWordButtonLabel(): String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseManageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHeader.text = getManageWordPageTitle()
        binding.mbSubmit.text = getManageWordButtonLabel()

        // Tell the home fragment or wherever you want to notify that you have made some changes
        lifecycleScope.launch {
            viewModel.finish.collect {
                setFragmentResult("manage_word", Bundle())
                findNavController().popBackStack(R.id.homeFragment, false)
            }
        }

        // Catch the error and call the snack bar function
        lifecycleScope.launch {
            viewModel.error.collect {
                showSnackbar(it)
            }
        }
    }

    // Function to show the snack bar and put the error message in it
    private fun showSnackbar(msg: String) {
        Snackbar.make(binding.root, msg , Snackbar.LENGTH_LONG).show()
    }
}