package com.natty.e02

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey
    val codigo: Int,
    val nombre: String,
    val categoria: String,
    val talla: String,
    val precio: Double
)
