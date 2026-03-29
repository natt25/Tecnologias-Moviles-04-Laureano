package com.example.e01

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class SegundaActividad : AppCompatActivity() {

    companion object {
        const val TIMER_RUNTIME = 10000
    }

    private var nbActivo = false
    private lateinit var nProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_actividad)

        nProgressBar = findViewById(R.id.progressBar)

        val timerThread = object : Thread() {
            override fun run() {
                nbActivo = true
                try {
                    var espera1 = 0
                    while (nbActivo && espera1 < TIMER_RUNTIME) {
                        sleep(200)
                        if (nbActivo) {
                            espera1 += 200
                            runOnUiThread {
                                actualizarProgress(espera1)
                            }
                        }
                    }
                } catch (e: InterruptedException) {
                } finally {
                    onContinuar()
                }
            }
        }
        timerThread.start()
    }

    fun actualizarProgress(timePassed: Int) {
        val progress = nProgressBar.max * timePassed / TIMER_RUNTIME
        nProgressBar.progress = progress
    }

    fun onContinuar() {
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar")
    }
}
