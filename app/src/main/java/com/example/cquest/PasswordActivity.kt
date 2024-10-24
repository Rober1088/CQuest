package com.example.cquest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PasswordActivity : AppCompatActivity() {

    private lateinit var passwordInput: EditText
    private lateinit var continueButton3: Button
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        passwordInput = findViewById(R.id.password_input)
        continueButton3 = findViewById(R.id.continue_button4)

        // Initialize the DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        continueButton3.setOnClickListener {
            savePassword()
        }

        findViewById<ImageButton>(R.id.close_button).setOnClickListener {
            finish() // Close the activity and return to the previous one
        }
    }

    private fun savePassword() {
        val password = passwordInput.text.toString().trim()
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            return
        }

        // Get the last inserted ID from the database (from EmailActivity)
        val id = databaseHelper.getLastInsertedId()
        if (id != -1L) {
            // Update the password for the given ID
            val updateSuccess = databaseHelper.updatePassword(id, password)
            if (updateSuccess) {
                Toast.makeText(this, "Password saved successfully", Toast.LENGTH_SHORT).show()

                // Obtener el nombre del usuario a partir del ID
                val userName = databaseHelper.getUserNameById(id)

                // Navegar a BienvenidaActivity, pasando el nombre del usuario con Intent
                val intent = Intent(this, BienvenidaActivity::class.java)
                intent.putExtra("user_name", userName) // Pasar el nombre del usuario
                startActivity(intent)
                finish() // Finalizar esta actividad
            } else {
                Toast.makeText(this, "Failed to save password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Failed to get last inserted ID", Toast.LENGTH_SHORT).show()
        }
    }
}
