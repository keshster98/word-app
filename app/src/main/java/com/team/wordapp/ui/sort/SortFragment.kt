package com.team.wordapp.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.team.wordapp.R
import com.team.wordapp.databinding.FragmentSortBinding

class SortFragment : DialogFragment() {
    private lateinit var binding: FragmentSortBinding
    private val args: SortFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedOrder = args.sort1
        var selectedBy = args.sort2

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
                val resultBundle = bundleOf("sort1" to selectedOrder, "sort2" to selectedBy)
                setFragmentResult("manage_sort", resultBundle)
                dismiss()
            }
        }
    }
}