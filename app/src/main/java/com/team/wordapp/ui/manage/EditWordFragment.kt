package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.team.wordapp.R
import kotlin.getValue

class EditWordFragment: BaseManageFragment() {
    override val viewModel: EditWordViewModel by viewModels()
    private val args: EditWordFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel= viewModel
        viewModel.getWord(args.wordId)
    }
}