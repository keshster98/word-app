package com.team.wordapp.ui.comfirmationDone

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
import com.team.wordapp.databinding.FragmentConfirmationDoneBinding
import kotlin.getValue

class ConfirmationDoneFragment: DialogFragment() {
    private val viewModel: ConfirmationDoneViewModel by viewModels {
        ConfirmationDoneViewModel.Factory
    }
    private lateinit var binding: FragmentConfirmationDoneBinding
    private val args: ConfirmationDoneFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmationDoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            mbYes.setOnClickListener {
                viewModel.done(args.word)
                findNavController().popBackStack(R.id.homeFragment, false)
                setFragmentResult("manage_word", Bundle())
            }

            mbNo.setOnClickListener {
                dismiss()
            }
        }
    }
}