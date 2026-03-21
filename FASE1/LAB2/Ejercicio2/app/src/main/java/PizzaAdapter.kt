package com.example.ejercicio2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PizzaAdapter(
    private val context: Context,
    private val nombres: Array<String>,
    private val imagenes: IntArray
) : BaseAdapter() {

    override fun getCount(): Int {
        return nombres.size
    }

    override fun getItem(position: Int): Any {
        return nombres[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista: View
        val holder: ViewHolder

        if (convertView == null) {
            vista = LayoutInflater.from(context).inflate(R.layout.item_pizza, parent, false)
            holder = ViewHolder()
            holder.imagen = vista.findViewById(R.id.imgPizza)
            holder.nombre = vista.findViewById(R.id.txtPizza)
            vista.tag = holder
        } else {
            vista = convertView
            holder = vista.tag as ViewHolder
        }

        holder.imagen.setImageResource(imagenes[position])
        holder.nombre.text = nombres[position]

        return vista
    }

    private class ViewHolder {
        lateinit var imagen: ImageView
        lateinit var nombre: TextView
    }
}