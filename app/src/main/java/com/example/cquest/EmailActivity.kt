package com.example.cquest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmailActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var continueButton: Button
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        emailInput = findViewById(R.id.email_input)
        continueButton = findViewById(R.id.continue_button3)

        // Inicializa el DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        continueButton.setOnClickListener {
            saveEmail()
        }

        findViewById<ImageButton>(R.id.close_button).setOnClickListener {
            finish() // Cierra la actividad y regresa a la anterior
        }
    }

    private fun saveEmail() {
        try {
            val email = emailInput.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, introduce tu correo", Toast.LENGTH_SHORT).show()
                return
            }

            // Verifica si el correo ya existe
            if (databaseHelper.emailExists(email)) {
                Toast.makeText(this, "El correo ya está registrado", Toast.LENGTH_SHORT).show()
                return
            }

            // Obtén el último ID insertado desde la base de datos
            val id = databaseHelper.getLastInsertedId()

            if (id != -1L) {
                // Actualiza el correo electrónico para el ID dado
                val updateSuccess = databaseHelper.updateEmail(id, email)

                if (updateSuccess) {
                    Toast.makeText(this, "Correo guardado correctamente", Toast.LENGTH_SHORT).show()

                    // Navega a NameActivity en lugar de PasswordActivity
                    val intent = Intent(this, NameActivity::class.java)
                    startActivity(intent)
                    finish() // Finaliza la actividad actual
                } else {
                    Toast.makeText(this, "Error al guardar el correo", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error al obtener el último ID", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Ocurrió un error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
