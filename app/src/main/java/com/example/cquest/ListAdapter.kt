package com.example.cquest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LevelAdapter(
    private val levels: List<Level>,
    private val onItemClick: (Level) -> Unit) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val Nivel: TextView = itemView.findViewById(R.id.Nivel)
        private val Tema: TextView = itemView.findViewById(R.id.Tema)
        private val startLevelButton: Button = itemView.findViewById(R.id.Iniciar_Nivel)


        fun bind(level: Level) {
            Nivel.text = level.Nivel
            Tema.text = level.Tema
            startLevelButton.setOnClickListener { onItemClick(level) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_level_list, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(levels[position])
    }

    override fun getItemCount(): Int = levels.size
}
