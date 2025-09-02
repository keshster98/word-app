package com.team.wordapp.ui.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.wordapp.data.model.Word
import com.team.wordapp.databinding.FragmentWordDetailsBinding

class WordDetailsFragment(
    private val word: Word
) : Fragment() {
    private lateinit var binding: FragmentWordDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            tvTitle.text = word.title
            tvMeaning.text = word.meaning
            tvSynonyms.text = word.synonyms
            tvDetails.text = word.details

            mbDone.setOnClickListener {
                //Navigate back to home
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