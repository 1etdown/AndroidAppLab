// CharacterAdapter.kt
package com.example.lab4plus.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4plus.databinding.ItemCharacterBinding

class CharacterAdapter(private var characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.nameTextView.text = character.name
            binding.cultureTextView.text = "Culture: ${character.culture ?: "Unknown"}"
            binding.bornTextView.text = "Born: ${character.born ?: "Unknown"}"
            binding.titlesTextView.text = "Titles: ${character.titles.joinToString(", ").ifEmpty { "None" }}"
            binding.aliasesTextView.text = "Aliases: ${character.aliases.joinToString(", ").ifEmpty { "None" }}"
            binding.playedByTextView.text = "Played By: ${character.playedBy.joinToString(", ").ifEmpty { "Unknown" }}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    fun updateData(newCharacters: List<Character>) {
        characters = newCharacters
        notifyDataSetChanged()
    }
}