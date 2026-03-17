package com.example.e01_moneda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCantidad: EditText
    private lateinit var spinnerOrigen: Spinner
    private lateinit var spinnerDestino: Spinner
    private lateinit var btnConvertir: Button
    private lateinit var txtResultado: TextView

    private val monedas = listOf(
        "Nuevo Sol (PEN)",
        "Dólar (USD)",
        "Euro (EUR)",
        "Libra (GBP)",
        "Rupia (INR)",
        "Real (BRL)",
        "Peso Mexicano (MXN)",
        "Yuan (CNY)",
        "Yen (JPY)"
    )

    private val tasasEnPen = mapOf(
        "Nuevo Sol (PEN)" to 1.0,
        "Dólar (USD)" to 3.65,
        "Euro (EUR)" to 3.98,
        "Libra (GBP)" to 4.65,
        "Rupia (INR)" to 0.044,
        "Real (BRL)" to 0.74,
        "Peso Mexicano (MXN)" to 0.21,
        "Yuan (CNY)" to 0.51,
        "Yen (JPY)" to 0.024
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCantidad = findViewById(R.id.editTextCantidad)
        spinnerOrigen = findViewById(R.id.spinnerOrigen)
        spinnerDestino = findViewById(R.id.spinnerDestino)
        btnConvertir = findViewById(R.id.btnConvertir)
        txtResultado = findViewById(R.id.txtResultado)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOrigen.adapter = adapter
        spinnerDestino.adapter = adapter

        spinnerOrigen.setSelection(0)
        spinnerDestino.setSelection(1)

        btnConvertir.setOnClickListener {
            convertirMoneda()
        }
    }

    private fun convertirMoneda() {
        val cantidadTexto = editTextCantidad.text.toString().trim()

        if (cantidadTexto.isEmpty()) {
            Toast.makeText(this, "Ingresa una cantidad", Toast.LENGTH_LONG).show()
            return
        }

        val cantidad = cantidadTexto.toDoubleOrNull()

        if (cantidad == null || cantidad <= 0) {
            Toast.makeText(this, "Ingresa un número válido mayor a 0", Toast.LENGTH_LONG).show()
            return
        }

        val monedaOrigen = spinnerOrigen.selectedItem.toString()
        val monedaDestino = spinnerDestino.selectedItem.toString()

        if (monedaOrigen == monedaDestino) {
            txtResultado.text = "Resultado: ${"%.2f".format(cantidad)}"
            return
        }

        val tasaOrigen = tasasEnPen[monedaOrigen]
        val tasaDestino = tasasEnPen[monedaDestino]

        if (tasaOrigen == null || tasaDestino == null) {
            Toast.makeText(this, "No se pudo convertir", Toast.LENGTH_LONG).show()
            return
        }

        val cantidadEnPen = cantidad * tasaOrigen
        val resultado = cantidadEnPen / tasaDestino

        txtResultado.text =
            "Resultado: ${"%.2f".format(cantidad)} $monedaOrigen = ${"%.2f".format(resultado)} $monedaDestino"
    }
}