package com.dev.daddyjokes.data

import android.content.Context
import com.dev.daddyjokes.R
import org.jsoup.Jsoup
import java.lang.Exception

class JokeRepository(private val context: Context) {

    fun getRandomJoke() : String =
        try {
            val doc = Jsoup.connect("https://www.anekdot.ru/random/anekdot/").get()
            val elements = doc.getElementsByClass("topicbox")
            val joke = elements[1].getElementsByClass("text")
            joke.text()
        } catch (e: Exception) { context.getString(R.string.no_internet) }

}