package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import kotlin.getValue
import com.team.wordapp.R

class EditWordFragment: BaseManageFragment() {
    override val viewModel: EditWordViewModel by viewModels {
        EditWordViewModel.Factory
    }

    override fun getManageWordPageTitle() = getString(R.string.update_word)
    override fun getManageWordButtonLabel() = getString(R.string.update)

    private val args: EditWordFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel= viewModel
        viewModel.getWord(args.wordId)
    }
}