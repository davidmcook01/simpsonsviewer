package com.sample.simpsonsviewer.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterModel(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String
): Serializable
