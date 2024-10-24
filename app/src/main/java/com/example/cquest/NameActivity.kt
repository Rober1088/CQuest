package com.example.cquest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NameActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var continueButton2: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        nameInput = findViewById(R.id.name_input)
        continueButton2 = findViewById(R.id.continue_button2)

        // Initialize the DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        continueButton2.setOnClickListener {
            saveName()
        }

        findViewById<ImageButton>(R.id.close_button).setOnClickListener {
            finish() // Close the activity and return to the previous one
        }
    }

    private fun saveName() {
        val name = nameInput.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return
        }

        // Get the last inserted ID from the database (from ActivityAge)
        val id = databaseHelper.getLastInsertedId()
        if (id != -1L) {
            // Update the name for the given ID
            databaseHelper.updateName(id, name)

            // Create an Intent to go to PasswordActivity
            val intent = Intent(this, PasswordActivity::class.java)
            intent.putExtra("user_name", name) // Optionally, pass the name to PasswordActivity if needed
            startActivity(intent)
            finish() // Close the current activity
        } else {
            Toast.makeText(this, "Failed to save name", Toast.LENGTH_SHORT).show()
        }
    }
}
