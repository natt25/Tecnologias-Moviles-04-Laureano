package com.example.e01_catalogoproductos

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageView: ImageView
    private lateinit var tvNombre: TextView
    private lateinit var tvCantidad: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var btnGuardarFavorito: Button
    private lateinit var btnVerFavoritos: Button

    private lateinit var prefs: SharedPreferences
    private lateinit var adapter: ImagenAdapter

    private val listaProductos = mutableListOf<Producto>()
    private var productoSeleccionado: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        imageView = findViewById(R.id.imageView)
        tvNombre = findViewById(R.id.tvNombre)
        tvCantidad = findViewById(R.id.tvCantidad)
        tvPrecio = findViewById(R.id.tvPrecio)
        btnGuardarFavorito = findViewById(R.id.btnGuardarFavorito)
        btnVerFavoritos = findViewById(R.id.btnVerFavoritos)

        prefs = getSharedPreferences("pasteles_prefs", MODE_PRIVATE)

        cargarProductos()

        adapter = ImagenAdapter(listaProductos) { producto ->
            productoSeleccionado = producto
            mostrarProducto(producto)
        }

        recyclerView.adapter = adapter

        if (listaProductos.isNotEmpty()) {
            productoSeleccionado = listaProductos[0]
            mostrarProducto(listaProductos[0])
        }

        btnGuardarFavorito.setOnClickListener {
            guardarFavorito()
        }

        btnVerFavoritos.setOnClickListener {
            startActivity(Intent(this, FavoritosActivity::class.java))
        }
    }

    private fun cargarProductos() {
        listaProductos.clear()
        listaProductos.add(Producto("Pastel de Chocolate", 5, 25.00, R.drawable.pastel1))
        listaProductos.add(Producto("Pastel de Fresa", 8, 22.00, R.drawable.pastel2))
        listaProductos.add(Producto("Pastel de Tres Leches", 6, 20.00, R.drawable.pastel3))
        listaProductos.add(Producto("Pastel Selva Negra", 4, 30.00, R.drawable.pastel4))
        listaProductos.add(Producto("Pastel Lúcuma", 3, 35.00, R.drawable.pastel5))
    }

    private fun mostrarProducto(producto: Producto) {
        imageView.setImageResource(producto.imagenResId)
        tvNombre.text = producto.nombre
        tvCantidad.text = "Cantidad: ${producto.cantidad}"
        tvPrecio.text = "Precio: S/ %.2f".format(producto.precio)
    }

    private fun guardarFavorito() {
        val producto = productoSeleccionado

        if (producto == null) {
            Toast.makeText(this, "Primero selecciona un pastel", Toast.LENGTH_SHORT).show()
            return
        }

        val favoritosJson = prefs.getString("favoritos", "[]")
        val jsonArray = JSONArray(favoritosJson)

        var yaExiste = false

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            if (obj.getString("nombre") == producto.nombre) {
                yaExiste = true
                break
            }
        }

        if (yaExiste) {
            Toast.makeText(this, "Ese pastel ya está en favoritos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoFavorito = JSONObject().apply {
            put("nombre", producto.nombre)
            put("cantidad", producto.cantidad)
            put("precio", producto.precio)
            put("imagenResId", producto.imagenResId)
        }

        jsonArray.put(nuevoFavorito)

        prefs.edit()
            .putString("favoritos", jsonArray.toString())
            .apply()

        Toast.makeText(this, "Pastel agregado a favoritos", Toast.LENGTH_SHORT).show()
    }
}