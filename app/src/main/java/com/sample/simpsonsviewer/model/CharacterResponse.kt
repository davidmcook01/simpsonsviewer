package com.sample.simpsonsviewer.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("relatedTopics") val relatedTopics: List<CharacterModel> = emptyList(
))
