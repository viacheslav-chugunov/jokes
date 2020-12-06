package com.dev.daddyjokes.app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dev.daddyjokes.R
import com.dev.daddyjokes.domain.Messenger


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.nextJoke()
    }

    override fun onStart() {
        super.onStart()
        val jokePlaceholder = findViewById<TextView>(R.id.jokePlaceholder)
        viewModel.joke.observe(this) { jokePlaceholder.text = it }
    }

    fun onClickNextButton(view: View) = viewModel.nextJoke()

    fun onClickPrevButton(view: View) = viewModel.prevJoke()

    // Menu Impl
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.send_item) {
            val joke = viewModel.joke.value
            Messenger(this).sendJokeToExternalApp(joke)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}