package com.natty.e01

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class EstudianteAdapter(
    private val lista: ArrayList<Estudiante>,
    private val referencia: DatabaseReference
) : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    class EstudianteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtCarrera: TextView = itemView.findViewById(R.id.txtCarrera)
        val txtCurso: TextView = itemView.findViewById(R.id.txtCurso)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
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

        holder.btnEditar.setOnClickListener {
            mostrarDialogoEditar(holder.itemView, estudiante)
        }

        holder.btnEliminar.setOnClickListener {
            if (estudiante.id.isNotEmpty()) {
                referencia.child(estudiante.id).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(holder.itemView.context, "Eliminado", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(holder.itemView.context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    private fun mostrarDialogoEditar(view: View, estudiante: Estudiante) {
        val contexto = view.context
        val vista = LayoutInflater.from(contexto)
            .inflate(R.layout.dialog_editar_estudiante, null)

        val edtNombre = vista.findViewById<EditText>(R.id.edtEditarNombre)
        val edtCarrera = vista.findViewById<EditText>(R.id.edtEditarCarrera)
        val edtCurso = vista.findViewById<EditText>(R.id.edtEditarCurso)

        edtNombre.setText(estudiante.nombre)
        edtCarrera.setText(estudiante.carrera)
        edtCurso.setText(estudiante.curso)

        AlertDialog.Builder(contexto)
            .setTitle("Editar estudiante")
            .setView(vista)
            .setPositiveButton("Actualizar") { _, _ ->
                val datos = HashMap<String, Any>()
                datos["id"] = estudiante.id
                datos["nombre"] = edtNombre.text.toString().trim()
                datos["carrera"] = edtCarrera.text.toString().trim()
                datos["curso"] = edtCurso.text.toString().trim()

                if (estudiante.id.isNotEmpty()) {
                    referencia.child(estudiante.id).updateChildren(datos)
                        .addOnSuccessListener {
                            Toast.makeText(contexto, "Actualizado", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(contexto, "Error al actualizar", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}