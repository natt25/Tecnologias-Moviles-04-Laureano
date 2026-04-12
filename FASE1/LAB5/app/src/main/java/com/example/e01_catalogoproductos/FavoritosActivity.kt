package com.example.e01_catalogoproductos

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class FavoritosActivity : AppCompatActivity() {

    private lateinit var recyclerFavoritos: RecyclerView
    private lateinit var tvSinFavoritos: TextView
    private lateinit var prefs: SharedPreferences

    private val listaFavoritos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        recyclerFavoritos = findViewById(R.id.recyclerFavoritos)
        tvSinFavoritos = findViewById(R.id.tvSinFavoritos)

        prefs = getSharedPreferences("pasteles_prefs", MODE_PRIVATE)

        cargarFavoritos()

        recyclerFavoritos.layoutManager = LinearLayoutManager(this)
        recyclerFavoritos.adapter = FavoritoAdapter(listaFavoritos)

        if (listaFavoritos.isEmpty()) {
            tvSinFavoritos.visibility = TextView.VISIBLE
            recyclerFavoritos.visibility = RecyclerView.GONE
        } else {
            tvSinFavoritos.visibility = TextView.GONE
            recyclerFavoritos.visibility = RecyclerView.VISIBLE
        }
    }

    private fun cargarFavoritos() {
        listaFavoritos.clear()

        val favoritosJson = prefs.getString("favoritos", "[]")
        val jsonArray = JSONArray(favoritosJson)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)

            val producto = Producto(
                nombre = obj.getString("nombre"),
                cantidad = obj.getInt("cantidad"),
                precio = obj.getDouble("precio"),
                imagenResId = obj.getInt("imagenResId")
            )

            listaFavoritos.add(producto)
        }
    }
}