package com.example.cquest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var levelList: List<Level> // Crear una lista de niveles
    private lateinit var adapter: LevelAdapter // Crear un adaptador para el RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cargar datos de los niveles
        levelList = loadLevels() // Implementa esta función para cargar tus niveles
        adapter = LevelAdapter(levelList) { level ->
            // Aquí puedes manejar el click de cada nivel
            // Por ejemplo, iniciar una nueva actividad
            // val intent = Intent(this, LevelActivity::class.java)
            // intent.putExtra("level_id", level.id)
            // startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    private fun loadLevels(): List<Level> {
        // Implementa esta función para devolver la lista de niveles
        return listOf(
            Level("Nivel 1", "contact@example.com"),
            Level("Nivel 2", "contact2@example.com")
            // Agrega más niveles aquí
        )
    }
}
