package com.natty.e01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var edtNombre: EditText
    private lateinit var edtCarrera: EditText
    private lateinit var edtCurso: EditText
    private lateinit var btnGuardar: Button

    private val database = FirebaseDatabase.getInstance()
    private val referencia = database.getReference("estudiantes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNombre = findViewById(R.id.edtNombre)
        edtCarrera = findViewById(R.id.edtCarrera)
        edtCurso = findViewById(R.id.edtCurso)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            guardarEstudiante()
        }
    }

    private fun guardarEstudiante() {
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

        val estudiante = Estudiante(
            nombre = nombre,
            carrera = carrera,
            curso = curso
        )

        referencia.child(id).setValue(estudiante)
            .addOnSuccessListener {
                Toast.makeText(this, "Estudiante guardado correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarCampos() {
        edtNombre.setText("")
        edtCarrera.setText("")
        edtCurso.setText("")
    }
}