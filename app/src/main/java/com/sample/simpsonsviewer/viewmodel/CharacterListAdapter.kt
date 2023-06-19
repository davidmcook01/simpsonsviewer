package com.sample.simpsonsviewer.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sample.simpsonsviewer.model.CharacterModel
import na.demoapp.myweatherapp.R

class CharacterListAdapter(private val characterClickListener: CharacterClickListener) :
    ListAdapter<CharacterModel, CharacterListAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterImageView: ImageView = itemView.findViewById(R.id.characterImageView)
        private val characterNameTextView: TextView = itemView.findViewById(R.id.characterNameTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val character = getItem(position)
                    characterClickListener.onCharacterClick(character)
                }
            }
        }

        fun bind(character: CharacterModel) {
            characterNameTextView.text = character.name

            Glide.with(itemView.context)
                .load(character.image)
                .apply(RequestOptions.placeholderOf(R.drawable.placeholder_image))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(characterImageView)
        }
    }

    interface CharacterClickListener {
        fun onCharacterClick(character: CharacterModel)
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }
}
