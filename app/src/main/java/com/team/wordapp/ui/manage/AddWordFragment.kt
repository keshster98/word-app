package com.team.wordapp.ui.manage

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View
import com.team.wordapp.R

class AddWordFragment : BaseManageFragment() {
    override val viewModel: AddWordViewModel by viewModels {
        AddWordViewModel.Factory
    }

    override fun getManageWordPageTitle() = getString(R.string.add_word)
    override fun getManageWordButtonLabel() = getString(R.string.add)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}