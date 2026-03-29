package com.example.e01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.e01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tag = "Ciclo de Vida"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(tag, "En el evento onCreate()")

        binding.btnMostrar.setOnClickListener {
            val intent = Intent(this, CargaActivity::class.java)
            intent.putExtra("destino", "segunda")
            startActivity(intent)
        }

        binding.btnConversor.setOnClickListener {
            val intent = Intent(this, CargaActivity::class.java)
            intent.putExtra("destino", "tercera")
            startActivity(intent)
        }

        binding.btnDrawer.setOnClickListener {
            val intent = Intent(this, CargaActivity::class.java)
            intent.putExtra("destino", "cuarta")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "En el evento onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "En el evento onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "En el evento onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "En el evento onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "En el evento onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "En el evento onDestroy()")
    }
}