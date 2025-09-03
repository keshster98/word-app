package com.team.wordapp.ui.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.team.wordapp.data.model.Word
import com.team.wordapp.databinding.FragmentWordDetailsBinding
import com.team.wordapp.ui.confirmation.ConfirmationViewModel
import kotlinx.coroutines.launch

class WordDetailsFragment: Fragment() {
    private lateinit var binding: FragmentWordDetailsBinding
    private val viewModel: WordDetailsViewModel by viewModels()
    private val args: WordDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            binding.run {
                tvTitle.text = args.word.title
                tvMeaning.text = args.word.meaning
                tvSynonyms.text = args.word.synonyms
                tvDetails.text = args.word.details

                mbDone.setOnClickListener {

                }

                mbUpdate.setOnClickListener {
                    //Navigate to update fragment
                }

                mbDelete.setOnClickListener {
                    //Navigate to confirmation fragment
                }
            }
        }
    }
}