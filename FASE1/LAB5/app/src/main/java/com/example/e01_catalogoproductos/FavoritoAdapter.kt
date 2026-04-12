package com.example.e01_catalogoproductos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoritoAdapter(
    private val lista: List<Producto>
) : RecyclerView.Adapter<FavoritoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFavorito: ImageView = itemView.findViewById(R.id.ivFavorito)
        val tvNombreFavorito: TextView = itemView.findViewById(R.id.tvNombreFavorito)
        val tvCantidadFavorito: TextView = itemView.findViewById(R.id.tvCantidadFavorito)
        val tvPrecioFavorito: TextView = itemView.findViewById(R.id.tvPrecioFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]
        holder.ivFavorito.setImageResource(producto.imagenResId)
        holder.tvNombreFavorito.text = producto.nombre
        holder.tvCantidadFavorito.text = "Cantidad: ${producto.cantidad}"
        holder.tvPrecioFavorito.text = "Precio: S/ %.2f".format(producto.precio)
    }

    override fun getItemCount(): Int = lista.size
}