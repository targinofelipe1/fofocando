package com.example.fofoca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.*
import br.edu.ifpb.fofoca.Fofocando

class MainActivity : AppCompatActivity() {
    private lateinit var btnJogar: Button
    private lateinit var btnCadastrar: Button
    private lateinit var tvQuantidade: TextView
    private val fofoqueiro = Fofocando()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnJogar = findViewById(R.id.btnJogar)
        btnCadastrar = findViewById(R.id.btnCadastrar)
        tvQuantidade = findViewById(R.id.tvQuantidade)

        btnJogar.setOnClickListener { startPlayActivity() }
        btnCadastrar.setOnClickListener { startRegisterActivity() }

        updateLayoutInfo()
    }

    private fun startPlayActivity() {
        if (fofoqueiro.getTotalFofocas() > 0) {
            val intent = Intent(this, JogarActivity::class.java).apply {
                putExtra("FOFOCA", fofoqueiro.getRandomFofoca())
            }
            startActivityForResult(intent, PLAY_ACTIVITY_REQUEST_CODE)
        } else {
            showToastMessage("NENHUMA FOFOCA CADASTRADA")
        }
    }

    private fun startRegisterActivity() {
        val intent = Intent(this, CadastrarActivity::class.java)
        startActivityForResult(intent, REGISTER_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REGISTER_ACTIVITY_REQUEST_CODE -> {
                    val fofoca = data?.getSerializableExtra("FOFOCA") as? Fofoca
                    fofoca?.let { fofoqueiro.addFofoca(it) }
                    showToastMessage("Fofoca cadastrada")
                    updateLayoutInfo()
                }
                PLAY_ACTIVITY_REQUEST_CODE -> {
                    showToastMessage("GANHOU")
                }
            }
        } else if (resultCode == RESULT_CANCELED && requestCode == PLAY_ACTIVITY_REQUEST_CODE) {
            showToastMessage("PERDEU")
        }
    }

    private fun updateLayoutInfo() {
        tvQuantidade.text = "${fofoqueiro.getTotalFofocas()} fofoca(s) cadastrada(s)"
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REGISTER_ACTIVITY_REQUEST_CODE = 1
        private const val PLAY_ACTIVITY_REQUEST_CODE = 2
    }
}