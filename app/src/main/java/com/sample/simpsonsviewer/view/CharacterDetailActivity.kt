package com.sample.simpsonsviewer.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sample.simpsonsviewer.model.CharacterModel
import com.sample.simpsonsviewer.viewmodel.CharacterDetailViewModel
import na.demoapp.myweatherapp.R
class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CharacterDetailViewModel
    private lateinit var characterImageView: ImageView
    private lateinit var characterNameTextView: TextView
    private lateinit var characterDescriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        viewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        characterImageView = findViewById(R.id.characterImageView)
        characterNameTextView = findViewById(R.id.characterNameTextView)
        characterDescriptionTextView = findViewById(R.id.characterDescriptionTextView)

        val character = intent.getSerializableExtra("character")
        character?.let {
            viewModel.setCharacter(it as CharacterModel)
        }

        viewModel.character.observe(this) { character ->
            characterNameTextView.text = character.name
            characterDescriptionTextView.text = character.description
            Glide.with(this)
                .load(character.image)
                .placeholder(R.drawable.placeholder_image)
                .into(characterImageView)
        }
    }
}
