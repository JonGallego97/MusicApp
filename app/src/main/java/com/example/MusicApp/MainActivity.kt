package com.example.MusicApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private var isPlaying = false
    private var repeatSong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperar el estado de isPlaying y repeatSong si savedInstanceState no es nulo
        if (savedInstanceState != null) {
            isPlaying = savedInstanceState.getBoolean("isPlaying")
            repeatSong = savedInstanceState.getInt("repeatSong")
        }

        val playPauseButton = findViewById<ImageButton>(R.id.PlayPauseButton)
        playPauseButton.setOnClickListener { playPauseButtonClicked() }

        val repeatButton = findViewById<ImageButton>(R.id.repeatButton)
        repeatButton.setOnClickListener { repeatSongButtonClicked() }

        // Actualizar las imágenes de los botones según el estado actual
        updatePlayPauseButton(playPauseButton)
        updateRepeatImageButton(repeatButton)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Guardar el estado actual en el Bundle
        outState.putBoolean("isPlaying", isPlaying)
        outState.putInt("repeatSong", repeatSong)
    }

    private fun repeatSongButtonClicked() {
        // Actualizar el estado y la imagen del botón de repetición
        updateRepeatSong(findViewById(R.id.repeatButton))
    }

    private fun updateRepeatSong(imageButton: ImageButton) {
        repeatSong = when (repeatSong) {
            0 -> 1
            1 -> 2
            2 -> 0
            else -> 0
        }

        // Actualizar la imagen del botón de repetición
        updateRepeatImageButton(imageButton)
    }

    private fun updateRepeatImageButton(imageButton: ImageButton) {
        when (repeatSong) {
            1 -> imageButton.setImageResource(R.drawable.ic_repeat_one)
            2 -> imageButton.setImageResource(R.drawable.ic_repeat_activated)
            else -> imageButton.setImageResource(R.drawable.ic_repeat)
        }
    }

    private fun playPauseButtonClicked() {
        // Cambiar el estado de isPlaying y actualizar la imagen del botón de reproducción
        isPlaying = !isPlaying
        updatePlayPauseButton(findViewById(R.id.PlayPauseButton))
    }

    private fun updatePlayPauseButton(imageButton: ImageButton) {
        if (isPlaying) {
            imageButton.setImageResource(R.drawable.ic_play)
        } else {
            imageButton.setImageResource(R.drawable.ic_pause)
        }
    }
}
