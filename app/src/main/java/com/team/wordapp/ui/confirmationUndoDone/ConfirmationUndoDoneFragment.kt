package com.team.wordapp.ui.confirmationUndoDone

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.team.wordapp.R
import com.team.wordapp.databinding.FragmentConfirmationUndoDoneBinding
import kotlin.getValue

class ConfirmationUndoDoneFragment: DialogFragment() {
    private val viewModel: ConfirmationUndoDoneViewModel by viewModels {
        ConfirmationUndoDoneViewModel.Factory
    }
    private lateinit var binding: FragmentConfirmationUndoDoneBinding
    private val args: ConfirmationUndoDoneFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationUndoDoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            mbYes.setOnClickListener {
                viewModel.undoDone(args.word)
                findNavController().popBackStack(R.id.homeFragment, false)
                setFragmentResult("manage_word", Bundle())
            }

            mbNo.setOnClickListener {
                dismiss()
            }
        }
    }
}