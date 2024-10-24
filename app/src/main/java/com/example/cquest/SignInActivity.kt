package com.example.cquest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class SignInActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvResetPassword: TextView
    private lateinit var closeButton: ImageButton

    // Instancia de la base de datos
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Inicialización de las vistas
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvResetPassword = findViewById(R.id.tvResetPassword)
        closeButton = findViewById(R.id.close_button)

        // Inicialización de la base de datos
        dbHelper = DatabaseHelper(this)

        // Acciones de los botones
        btnLogin.setOnClickListener {
            val email = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Verificar que los campos no estén vacíos
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Verificar credenciales
                if (dbHelper.checkUserCredentials(email, password)) {
                    // Redirigir a la actividad principal si el login es exitoso
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvResetPassword.setOnClickListener {
            // Acción para restablecer la contraseña (navegar a otra pantalla o mostrar un diálogo)
            Toast.makeText(this, "Restablecer contraseña no implementado", Toast.LENGTH_SHORT).show()
        }

        closeButton.setOnClickListener {
            // Cerrar la actividad actual y volver al login
            finish()
        }
    }
}
