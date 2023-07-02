package com.example.fofoca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView

class JogarActivity : AppCompatActivity() {
    private lateinit var tvFofocando: TextView
    private lateinit var rgJogarVerdade: RadioButton
    private lateinit var rgJogarMentira: RadioButton
    private lateinit var btnResposta: Button
    private lateinit var pbTempo: ProgressBar
    private var fofoca: Fofoca? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogar)

        fofoca = intent.getSerializableExtra("FOFOCA") as? Fofoca

        tvFofocando = findViewById(R.id.tvFofocando)
        rgJogarVerdade = findViewById(R.id.rbJogarVerdade)
        rgJogarMentira = findViewById(R.id.rbJogarMentira)
        btnResposta = findViewById(R.id.btnResposta)
        pbTempo = findViewById(R.id.pbTempo)

        btnResposta.setOnClickListener { answer() }

        tvFofocando.text = fofoca?.description
        startTimer()
    }

    private fun answer() {
        if ((fofoca?.veracity == true && rgJogarVerdade.isChecked) ||
            (fofoca?.veracity == false && rgJogarMentira.isChecked)
        ) {
            setResult(RESULT_OK)
        } else {
            setResult(RESULT_CANCELED)
        }

        finish()
    }

    private fun startTimer() {
        Thread {
            while (pbTempo.progress < 100) {
                pbTempo.progress += 1
                Thread.sleep(100)
            }
            finish()
        }.start()
    }
}