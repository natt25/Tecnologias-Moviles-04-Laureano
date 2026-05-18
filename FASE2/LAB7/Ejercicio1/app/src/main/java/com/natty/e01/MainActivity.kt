package com.natty.e01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.natty.e01.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val FILE_NAME = "archivo_externo.txt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener { guardarArchivoExterno() }
        binding.btnLoad.setOnClickListener { cargarArchivoExterno() }
    }

    private fun guardarArchivoExterno() {
        val texto = binding.editText.text.toString()

        if (texto.isEmpty()) {
            Toast.makeText(this, "Ingrese un texto para guardar", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val directorioExterno = getExternalFilesDir(null)

            if (directorioExterno == null) {
                Toast.makeText(this, "No se encontró almacenamiento externo", Toast.LENGTH_SHORT).show()
                return
            }

            val archivo = File(directorioExterno, FILE_NAME)

            archivo.writeText(texto)

            Toast.makeText(
                this,
                "Archivo guardado en almacenamiento externo",
                Toast.LENGTH_SHORT
            ).show()

            binding.editText.setText("")

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this,
                "Error al guardar: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun cargarArchivoExterno() {
        try {
            val directorioExterno = getExternalFilesDir(null)

            if (directorioExterno == null) {
                Toast.makeText(this, "No se encontró almacenamiento externo", Toast.LENGTH_SHORT).show()
                return
            }

            val archivo = File(directorioExterno, FILE_NAME)

            if (!archivo.exists()) {
                Toast.makeText(this, "El archivo externo no existe aún", Toast.LENGTH_SHORT).show()
                return
            }

            val contenido = archivo.readText()

            binding.editText.setText(contenido)

            Toast.makeText(
                this,
                "Archivo cargado desde almacenamiento externo",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this,
                "Error al cargar: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}