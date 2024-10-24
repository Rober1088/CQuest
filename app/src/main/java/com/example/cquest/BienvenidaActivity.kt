package com.example.cquest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.ImageView

class BienvenidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val gifImageView = findViewById<ImageView>(R.id.gif_image)
        val welcomeTextView = findViewById<TextView>(R.id.bienvenida_text)

        // Obtener el nombre del Intent
        val userName = intent.getStringExtra("user_name")

        // Actualizar el TextView con el nombre del usuario
        welcomeTextView.text = "¡Hola, $userName! Creaste tu perfil con éxito."

        // Cargar el GIF desde los recursos
        Glide.with(this)
            .asGif()
            .load(R.drawable.sapo)
            .into(gifImageView)
    }
}
