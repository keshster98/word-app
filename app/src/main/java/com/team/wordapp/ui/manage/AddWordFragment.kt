package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController

class AddWordFragment : BaseManageFragment() {
    override val viewModel: AddWordViewModel by viewModels()

    override fun getManageWordPageTitle() = "Add Word"

    override fun getManageWordButtonLabel() = "Add"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

}