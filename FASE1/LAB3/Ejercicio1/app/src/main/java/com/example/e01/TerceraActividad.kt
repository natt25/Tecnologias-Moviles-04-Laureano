package com.example.e01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TerceraActividad : AppCompatActivity() {

    private lateinit var editCantidad: EditText
    private lateinit var radioDolar: RadioButton
    private lateinit var radioSol: RadioButton
    private lateinit var btnConvertir: Button
    private lateinit var txtResultado: TextView

    private val tipoCambio = 3.65

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercera)

        editCantidad = findViewById(R.id.editCantidad)
        radioDolar = findViewById(R.id.radioDolar)
        radioSol = findViewById(R.id.radioSol)
        btnConvertir = findViewById(R.id.btnConvertir)
        txtResultado = findViewById(R.id.txtResultado)

        btnConvertir.setOnClickListener {
            convertirMoneda()
        }
    }

    private fun convertirMoneda() {
        val texto = editCantidad.text.toString().trim()

        if (texto.isEmpty()) {
            Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show()
            return
        }

        val cantidad = texto.toDoubleOrNull()
        if (cantidad == null || cantidad <= 0) {
            Toast.makeText(this, "Ingrese un número válido mayor a 0", Toast.LENGTH_SHORT).show()
            return
        }

        if (radioDolar.isChecked) {
            val resultado = cantidad / tipoCambio
            txtResultado.text = "Resultado: %.2f soles = %.2f dólares".format(cantidad, resultado)
        } else if (radioSol.isChecked) {
            val resultado = cantidad * tipoCambio
            txtResultado.text = "Resultado: %.2f dólares = %.2f soles".format(cantidad, resultado)
        } else {
            Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show()
        }
    }
}