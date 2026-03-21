package com.example.ejercicio2

import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridViewPizzas: GridView

    private val nombresPizzas = arrayOf(
        "Hawaiana",
        "Pepperoni",
        "Americana",
        "Vegetariana",
        "Cuatro Quesos",
        "Suprema"
    )

    private val imagenesPizzas = intArrayOf(
        R.drawable.pizza_hawaiana,
        R.drawable.pizza_pepperoni,
        R.drawable.pizza_americana,
        R.drawable.pizza_vegetariana,
        R.drawable.pizza_cuatroquesos,
        R.drawable.pizza_suprema
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridViewPizzas = findViewById(R.id.gridViewPizzas)

        val adapter = PizzaAdapter(this, nombresPizzas, imagenesPizzas)
        gridViewPizzas.adapter = adapter

        gridViewPizzas.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                "Pizza seleccionada: ${nombresPizzas[position]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}