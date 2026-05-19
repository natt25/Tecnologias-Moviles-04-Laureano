package com.natty.e01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstudianteAdapter(
    private val lista: ArrayList<Estudiante>
) : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    class EstudianteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtCarrera: TextView = itemView.findViewById(R.id.txtCarrera)
        val txtCurso: TextView = itemView.findViewById(R.id.txtCurso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estudiante, parent, false)

        return EstudianteViewHolder(vista)
    }

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        val estudiante = lista[position]

        holder.txtNombre.text = "Nombre: " + estudiante.nombre
        holder.txtCarrera.text = "Carrera: " + estudiante.carrera
        holder.txtCurso.text = "Curso: " + estudiante.curso
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}