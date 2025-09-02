package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View

class AddWordFragment : BaseManageFragment() {
    override val viewModel: AddWordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}