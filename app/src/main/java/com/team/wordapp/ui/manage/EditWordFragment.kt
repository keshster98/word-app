package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
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