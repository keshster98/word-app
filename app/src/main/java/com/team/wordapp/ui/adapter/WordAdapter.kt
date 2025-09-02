package com.team.wordapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.wordapp.data.model.Word
import com.team.wordapp.databinding.LayoutItemWordBinding

class WordAdapter(
    private var words: List<Word>,
    private val onClick: (Word) -> Unit
): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordViewHolder {
        val binding = LayoutItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WordViewHolder,
        position: Int
    ) {
        val word = words[position]
        holder.binding.run {
            tvTitle.text = word.title
            tvMeaning.text = word.meaning

            llWord.setOnClickListener {
                onClick(word)
            }
        }
    }

    override fun getItemCount() = words.size

    fun setNotes(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    inner class WordViewHolder(
        val binding: LayoutItemWordBinding
    ): RecyclerView.ViewHolder(binding.root) {

    }
}