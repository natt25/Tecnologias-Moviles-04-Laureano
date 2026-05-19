package com.natty.e01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : Activity() {

    private lateinit var edtNombre: EditText
    private lateinit var edtCarrera: EditText
    private lateinit var edtCurso: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnVerEstudiantes: Button

    private val referencia = FirebaseDatabase.getInstance().getReference("estudiantes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNombre = findViewById(R.id.edtNombre)
        edtCarrera = findViewById(R.id.edtCarrera)
        edtCurso = findViewById(R.id.edtCurso)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnVerEstudiantes = findViewById(R.id.btnVerEstudiantes)

        btnGuardar.setOnClickListener {
            crearEstudiante()
        }

        btnVerEstudiantes.setOnClickListener {
            startActivity(Intent(this, EstudiantesActivity::class.java))
        }
    }

    private fun crearEstudiante() {
        val nombre = edtNombre.text.toString().trim()
        val carrera = edtCarrera.text.toString().trim()
        val curso = edtCurso.text.toString().trim()

        if (nombre.isEmpty() || carrera.isEmpty() || curso.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val id = referencia.push().key

        if (id == null) {
            Toast.makeText(this, "Error al generar ID", Toast.LENGTH_SHORT).show()
            return
        }

        val estudiante = Estudiante(id, nombre, carrera, curso)

        referencia.child(id).setValue(estudiante)
            .addOnSuccessListener {
                Toast.makeText(this, "Estudiante guardado", Toast.LENGTH_SHORT).show()
                edtNombre.setText("")
                edtCarrera.setText("")
                edtCurso.setText("")
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
    }
}