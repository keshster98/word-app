package com.team.wordapp.ui.confirmation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.team.wordapp.R
import com.team.wordapp.databinding.FragmentConfirmationBinding

class ConfirmationFragment(
    private val wordId: Int
) : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding
    private val viewModel: ConfirmationViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mbCancel.setOnClickListener {

        }

        binding.mbDelete.setOnClickListener {
            viewModel.delete(wordId)
            setFragmentResult("manage_word", Bundle())
        }
    }
}