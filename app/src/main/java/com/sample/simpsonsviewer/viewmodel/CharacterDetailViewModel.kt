package com.sample.simpsonsviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.simpsonsviewer.model.CharacterModel

class CharacterDetailViewModel : ViewModel() {
    private val _character = MutableLiveData<CharacterModel>()
    val character: LiveData<CharacterModel> get() = _character

    fun setCharacter(character: CharacterModel) {
        _character.value = character
    }
}
