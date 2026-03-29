package com.example.e01

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class CuartaActividad : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var txtContenido: TextView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuarta)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        txtContenido = findViewById(R.id.txtContenido)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> {
                    txtContenido.text = "Has seleccionado: Inicio"
                    Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_perfil -> {
                    txtContenido.text = "Has seleccionado: Perfil"
                    Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_configuracion -> {
                    txtContenido.text = "Has seleccionado: Configuración"
                    Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_salir -> {
                    Toast.makeText(this, "Saliendo del menú", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}