package com.dev.daddyjokes.domain

import android.content.Context
import android.content.Intent

class Messenger(private val context: Context) {

    fun sendJokeToExternalApp(joke: String?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, joke)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

}