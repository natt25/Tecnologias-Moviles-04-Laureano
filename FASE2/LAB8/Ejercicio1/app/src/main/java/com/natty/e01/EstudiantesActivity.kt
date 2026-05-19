package com.natty.e01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EstudiantesActivity : AppCompatActivity() {

    private lateinit var recyclerEstudiantes: RecyclerView
    private lateinit var adapter: EstudianteAdapter
    private val listaEstudiantes = ArrayList<Estudiante>()

    private val referencia = FirebaseDatabase.getInstance().getReference("estudiantes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes)

        recyclerEstudiantes = findViewById(R.id.recyclerEstudiantes)

        adapter = EstudianteAdapter(listaEstudiantes)

        recyclerEstudiantes.layoutManager = LinearLayoutManager(this)
        recyclerEstudiantes.adapter = adapter

        leerEstudiantes()
    }

    private fun leerEstudiantes() {
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaEstudiantes.clear()

                for (dato in snapshot.children) {
                    val estudiante = dato.getValue(Estudiante::class.java)

                    if (estudiante != null) {
                        listaEstudiantes.add(estudiante)
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