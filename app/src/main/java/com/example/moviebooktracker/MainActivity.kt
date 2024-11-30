package com.example.moviebooktracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val mediaList = mutableListOf<Media>()
    private lateinit var adapter: MediaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicjalizacja RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.media_recycler_view)
        adapter = MediaAdapter(mediaList) { media ->
            showMediaDetails(media)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Inicjalizacja SeekBar i TextView
        val seekBar: SeekBar = findViewById(R.id.rating_seekbar)
        val ratingLabel: TextView = findViewById(R.id.rating_label)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val rating = progress / 10.0f // Przeliczenie na ocenę z krokiem 0.1
                ratingLabel.text = "Ocena: $rating"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Można dodać dodatkową logikę tutaj
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Można dodać dodatkową logikę tutaj
            }
        })

        // Dodawanie nowego elementu
        findViewById<Button>(R.id.add_button).setOnClickListener {
            addMedia()
        }
    }

    private fun addMedia() {
        val title = findViewById<EditText>(R.id.title_input).text.toString()
        val creator = findViewById<EditText>(R.id.creator_input).text.toString()
        val year = findViewById<EditText>(R.id.year_input).text.toString().toIntOrNull() ?: 0
        val seekBar: SeekBar = findViewById(R.id.rating_seekbar)
        val rating = seekBar.progress / 10.0f // Przeliczenie na ocenę z krokiem 0.1

        if (title.isBlank() || creator.isBlank() || year == 0) {
            Toast.makeText(this, "Proszę wypełnić wszystkie pola poprawnie", Toast.LENGTH_SHORT).show()
            return
        }

        val media = Media(title, creator, year, rating)
        mediaList.add(media)
        adapter.notifyItemInserted(mediaList.size - 1)

        // Resetowanie formularza
        findViewById<EditText>(R.id.title_input).text.clear()
        findViewById<EditText>(R.id.creator_input).text.clear()
        findViewById<EditText>(R.id.year_input).text.clear()
        seekBar.progress = 0
    }

    private fun showMediaDetails(media: Media) {
        AlertDialog.Builder(this)
            .setTitle(media.title)
            .setMessage("Autor/Reżyser: ${media.creator}\nRok: ${media.year}\nOcena: ${media.rating}")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}