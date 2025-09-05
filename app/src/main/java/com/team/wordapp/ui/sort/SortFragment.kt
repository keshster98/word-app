package com.team.wordapp.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.team.wordapp.R
import com.team.wordapp.databinding.FragmentSortBinding
import com.team.wordapp.ui.home.HomeViewModel

class SortFragment : DialogFragment() {
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentSortBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedOrder = viewModel.sortState1
        var selectedBy = viewModel.sortState2

        fun radioCheck() {
            binding.run {
                rgOrder.check(when(selectedOrder) {
                    0 -> R.id.rbAsc
                    1 -> R.id.rbDesc
                    else -> R.id.rbAsc
                })
                rgBy.check(when(selectedBy) {
                    0 -> R.id.rbTitle
                    1 -> R.id.rbDate
                    else -> R.id.rbTitle
                })
            }
        }

        binding.run {
            radioCheck()
            rgOrder.setOnCheckedChangeListener { _, checkedId ->
                selectedOrder = when(checkedId) {
                    R.id.rbAsc -> 0
                    R.id.rbDesc -> 1
                    else -> 0
                }
            }
            rgBy.setOnCheckedChangeListener { _, checkedId ->
                selectedBy = when(checkedId) {
                    R.id.rbTitle -> 0
                    R.id.rbDate -> 1
                    else -> 0
                }
            }
            mbDone.setOnClickListener {
                viewModel.sortState1 = selectedOrder
                viewModel.sortState2 = selectedBy
                setFragmentResult("manage_word", Bundle())
                dismiss()
            }
        }
    }
}