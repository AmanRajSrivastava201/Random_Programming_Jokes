package com.example.trivialquiz.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trivialquiz.model.JokesList
import com.example.trivialquiz.repository.JokesRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokesViewModel : ViewModel() {

    val jokesList = MutableLiveData<JokesList>()
    val error = MutableLiveData<String>()
    private val quizRepo = JokesRepo()

    fun getJokes() {
        val quizApi = quizRepo.getQuizApi()
        quizApi.getJokes()
            .enqueue(object : Callback<JokesList> {
                override fun onFailure(call: Call<JokesList>, t: Throwable) {
                    jokesList.postValue(null)
                    error.postValue(t.message.toString())
                }

                override fun onResponse(call: Call<JokesList>, response: Response<JokesList>) {
                    jokesList.postValue(response.body())
                }
            })
    }
}