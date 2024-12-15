package com.example.to02hlc_naviaortegajuanmanuel
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.to02hlc_naviaortegajuanmanuel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var tiempoInicial = 60000L
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var mediaPlayer: MediaPlayer
    private var contadorCafes = 0
    private var incrementarTiempo = 10000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.pigle)
        updateTimerText()

        binding.btnCount.setOnClickListener {
            if (binding.btnCount.text == "Iniciar cuenta regresiva") {
                startTimer()
                // Deshabilitar botones de más y menos
                binding.btnIncrease.isEnabled = false
                binding.btnDecrease.isEnabled = false
            } else {
                stopTimer()
            }
        }
// Reiniciar los contadores
        binding.btnReset.setOnClickListener {
            resetCounters()
        }

        // Actualizaremos el texto del temporizador
        binding.btnIncrease.setOnClickListener {
            tiempoInicial += incrementarTiempo
            updateTimerText()
        }

        // Actualizaremos el texto del temporizador
        binding.btnDecrease.setOnClickListener {
            tiempoInicial -= incrementarTiempo
            if (tiempoInicial < 0) tiempoInicial = 0
            updateTimerText()
        }
    }
//funcion para iniciar el temporizador
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(tiempoInicial, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCounter.text = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                playSound()
                if (contadorCafes < 10) {
                    contadorCafes++
                    binding.tvCoffeeCounter.text = contadorCafes.toString()

                } else {
                    showPopup()
                    binding.btnCount.isEnabled = false
                }
                // Habilitar botones de más y menos al finalizar
                binding.btnIncrease.isEnabled = true
                binding.btnDecrease.isEnabled = true
                binding.btnCount.text = "Iniciar"
            }
        }.start()
        binding.btnCount.text = "Detener"
    }
//funcion para detener el temporizador
    private fun stopTimer() {
        countDownTimer.cancel()
        resetTimer()
        // Habilitar botones de más y menos
        binding.btnIncrease.isEnabled = true
        binding.btnDecrease.isEnabled = true
    }


    private fun resetCounters() {
        countDownTimer.cancel() // Detener el temporizador
        tiempoInicial = 60000L // Restablecer el tiempo inicial
        updateTimerText() // Actualizar el texto del temporizador
        binding.btnCount.isEnabled = true
        binding.btnCount.text = "Iniciar cuenta regresiva"
        contadorCafes = 0
        binding.tvCoffeeCounter.text = contadorCafes.toString()
    }

    private fun resetTimer() {
        updateTimerText()
        binding.btnCount.isEnabled = true
        binding.btnCount.text = "Iniciar cuenta regresiva"
    }

    private fun updateTimerText() {
        binding.tvCounter.text = formatTime(tiempoInicial)
    }

    // Función para formatear el tiempo en minutos y segundos
    private fun formatTime(millis: Long): String {
        val totalSeconds = millis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return if (minutes > 0) {
            String.format("%02d:%02d", minutes, seconds)
        } else {
            seconds.toString()
        }
    }
//funcion para el sonido cuando acabe el tiempo
    private fun playSound() {
        mediaPlayer.start()
    }

    private fun showPopup() {
        AlertDialog.Builder(this)
            .setTitle("Fin!")
            .setMessage("¡Has alcanzado el límite de 10 cafés!")
            .setPositiveButton("Ok", null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}