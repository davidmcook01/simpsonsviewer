package com.sample.simpsonsviewer.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.model.CharacterModel
import com.sample.simpsonsviewer.model.CharacterResponse
import com.sample.simpsonsviewer.model.RetrofitClient
import com.sample.simpsonsviewer.viewmodel.CharacterListAdapter
import na.demoapp.myweatherapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CharacterListAdapter.CharacterClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterListAdapter
    private lateinit var characters: List<CharacterModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        characterAdapter = CharacterListAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
        }
        val searchButton: Button = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            val searchEditText: EditText = findViewById(R.id.searchEditText)
            val query = searchEditText.text.toString().trim()
            fetchCharacters(query)
        }
        fetchCharacters()
    }

    private fun fetchCharacters(query: String) {
        val service = RetrofitClient.demoAppService
        val call: Call<CharacterResponse> = service.getCharacters(query)

        call.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    characters = characterResponse?.relatedTopics ?: emptyList()
                    characterAdapter.submitList(characters)
                    Log.e(TAG, "Successful fetching characters $characterResponse")
                } else {
                    Log.e(TAG, "Error fetching characters")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e(TAG, "Failure fetching characters", t)
            }
        })
    }

    private fun fetchCharacters() {
        val service = RetrofitClient.demoAppService
        val call: Call<CharacterResponse> = service.getCharacters()

        call.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    characters = characterResponse?.relatedTopics ?: emptyList()
                    characterAdapter.submitList(characters)
                    Log.e(TAG, "Successful fetching characters $characterResponse")
                } else {
                    Log.e(TAG, "Error fetching characters")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Log.e(TAG, "Failure fetching characters", t)
            }
        })
    }




    override fun onCharacterClick(character: CharacterModel) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("character", character)
        startActivity(intent)
    }
}
