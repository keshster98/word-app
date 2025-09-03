package com.team.wordapp.ui.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.team.wordapp.databinding.FragmentWordDetailsBinding
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

            val word = args.word

            binding.run {
                tvTitle.text = word.title
                tvMeaning.text = word.meaning
                tvSynonyms.text = word.synonyms
                tvDetails.text = word.details

                mbDone.setOnClickListener {
                    viewModel.done(word)
                    findNavController().popBackStack()
                }

                mbUpdate.setOnClickListener {
                    val action = WordDetailsFragmentDirections.
                    actionWordDetailsFragmentToEditWordFragment(word.id!!)
                    findNavController().navigate(action)
                }

                mbDelete.setOnClickListener {
                    val action = WordDetailsFragmentDirections.
                    actionWordDetailsFragmentToConfirmationFragment(word.id!!)
                    findNavController().navigate(action)
                }
            }
        }
    }
}