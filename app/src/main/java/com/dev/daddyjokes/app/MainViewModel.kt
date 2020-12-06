package com.dev.daddyjokes.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dev.daddyjokes.R
import com.dev.daddyjokes.data.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    val joke = MutableLiveData<String>()
    private val viewedJokes = mutableListOf<String>()
    private val repository = JokeRepository(app)

    fun nextJoke() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.getRandomJoke().run {
                val hasInternet = this != app.getString(R.string.no_internet)
                if (hasInternet)
                joke.postValue(this)
                viewedJokes.add(this)
            }
        }
    }

    fun prevJoke() {
        viewModelScope.launch(Dispatchers.Default) {
            viewedJokes.run {
                if (size > 1) {
                    removeLast()
                    joke.postValue(last())
                }
            }
        }
    }

}