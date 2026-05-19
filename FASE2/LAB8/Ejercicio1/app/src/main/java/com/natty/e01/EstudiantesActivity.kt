package com.natty.e01

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EstudiantesActivity : Activity() {

    private lateinit var recyclerEstudiantes: RecyclerView
    private lateinit var adapter: EstudianteAdapter

    private val lista = ArrayList<Estudiante>()
    private val referencia = FirebaseDatabase.getInstance().getReference("estudiantes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes)

        recyclerEstudiantes = findViewById(R.id.recyclerEstudiantes)

        adapter = EstudianteAdapter(lista, referencia)

        recyclerEstudiantes.layoutManager = LinearLayoutManager(this)
        recyclerEstudiantes.adapter = adapter

        leerEstudiantes()
    }

    private fun leerEstudiantes() {
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lista.clear()

                for (dato in snapshot.children) {

                    if (dato.hasChildren()) {
                        val id = dato.child("id").getValue(String::class.java) ?: dato.key ?: ""
                        val nombre = dato.child("nombre").getValue(String::class.java) ?: ""
                        val carrera = dato.child("carrera").getValue(String::class.java) ?: ""
                        val curso = dato.child("curso").getValue(String::class.java) ?: ""

                        val estudiante = Estudiante(
                            id = id,
                            nombre = nombre,
                            carrera = carrera,
                            curso = curso
                        )

                        lista.add(estudiante)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@EstudiantesActivity,
                    "Error al leer datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}