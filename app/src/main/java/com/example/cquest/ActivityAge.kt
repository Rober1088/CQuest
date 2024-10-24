package com.example.cquest

import android.widget.ImageButton
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityAge : AppCompatActivity() {

    private lateinit var ageInput: EditText
    private lateinit var continueButton: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)

        ageInput = findViewById(R.id.age_input)
        continueButton = findViewById(R.id.continue_button)

        // Initialize the DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        continueButton.setOnClickListener {
            saveAge()
        }

        findViewById<ImageButton>(R.id.close_button).setOnClickListener {
            finish() // Close the activity and return to the previous one
        }
    }

    private fun saveAge() {
        val age = ageInput.text.toString().trim()
        if (age.isEmpty()) {
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
            return
        }

        // Save the age to the database
        val id = databaseHelper.insertAge(age)
        if (id != -1L) {
            // Redirigir a EmailActivity en lugar de NameActivity
            val intent = Intent(this, EmailActivity::class.java)
            startActivity(intent)
            finish() // Close the current activity
        } else {
            Toast.makeText(this, "Failed to save age", Toast.LENGTH_SHORT).show()
        }
    }
}
