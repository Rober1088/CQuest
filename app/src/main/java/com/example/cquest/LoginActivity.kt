package com.example.cquest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los botones
        val crearCuentaButton: Button = findViewById(R.id.CrearCuenta)
        val iniciarSesionButton: Button = findViewById(R.id.IniciarSesion)

        // Acción al presionar el botón de crear cuenta
        crearCuentaButton.setOnClickListener {
            // Navegar a la actividad de registro
            val intent = Intent(this, ActivityAge::class.java)
            startActivity(intent)
        }

        // Acción al presionar el botón de iniciar sesión
        iniciarSesionButton.setOnClickListener {
            // Navegar a la actividad de inicio de sesión
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
