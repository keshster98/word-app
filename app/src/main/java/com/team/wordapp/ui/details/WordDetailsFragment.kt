package com.team.wordapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.team.wordapp.R
import com.team.wordapp.databinding.FragmentWordDetailsBinding
import kotlinx.coroutines.launch

class WordDetailsFragment: Fragment() {
    private lateinit var binding: FragmentWordDetailsBinding
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

                if (word.isCompleted) {
                    binding.mbDone.text = getString(R.string.undo)
                    binding.mbDone.setOnClickListener {
                        val action = WordDetailsFragmentDirections
                            .actionWordDetailsFragmentToConfirmationUndoDoneFragment(word)
                        findNavController().navigate(action)
                    }
                } else {
                    binding.mbDone.text = getString(R.string.done)
                    binding.mbDone.setOnClickListener {
                        val action = WordDetailsFragmentDirections
                            .actionWordDetailsToConfirmationDone(word)
                        findNavController().navigate(action)
                    }
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