package com.sample.simpsonsviewer.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DemoAppService {
    @GET("/")
    fun getCharacters(
        @Query("q") query: String = "simpsons+characters",
        @Query("format") format: String = "json"
    ): Call<CharacterResponse>
}