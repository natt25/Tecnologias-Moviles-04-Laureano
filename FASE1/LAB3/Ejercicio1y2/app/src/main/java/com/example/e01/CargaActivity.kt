package com.example.e01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CargaActivity : AppCompatActivity() {

    companion object {
        const val TIMER_RUNTIME = 3000
    }

    private var nbActivo = false
    private lateinit var progressBar: ProgressBar
    private var destino: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga)

        progressBar = findViewById(R.id.progressBarCarga)
        destino = intent.getStringExtra("destino")

        val timerThread = object : Thread() {
            override fun run() {
                nbActivo = true
                try {
                    var espera = 0
                    while (nbActivo && espera < TIMER_RUNTIME) {
                        sleep(200)
                        if (nbActivo) {
                            espera += 200
                            runOnUiThread {
                                actualizarProgress(espera)
                            }
                        }
                    }
                } catch (e: InterruptedException) {
                    Log.d("CargaActivity", "Hilo interrumpido")
                } finally {
                    runOnUiThread {
                        onContinuar()
                    }
                }
            }
        }
        timerThread.start()
    }

    private fun actualizarProgress(timePassed: Int) {
        val progress = progressBar.max * timePassed / TIMER_RUNTIME
        progressBar.progress = progress
    }

    private fun onContinuar() {
        Toast.makeText(this, "Carga completa", Toast.LENGTH_SHORT).show()

        when (destino) {
            "segunda" -> startActivity(Intent(this, SegundaActividad::class.java))
            "tercera" -> startActivity(Intent(this, TerceraActividad::class.java))
            "cuarta" -> startActivity(Intent(this, CuartaActividad::class.java))
        }

        finish()
    }
}