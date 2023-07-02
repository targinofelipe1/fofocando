package com.example.fofoca


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class CadastrarActivity : AppCompatActivity() {
    private lateinit var etCadastrarFofoca: EditText
    private lateinit var rbCadastrarVerdade: RadioButton
    private lateinit var btnSalvar: Button
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        etCadastrarFofoca = findViewById(R.id.etCadastrarFofoca)
        rbCadastrarVerdade = findViewById(R.id.rbCadastrarVerdade)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnCadastrar = findViewById(R.id.btnCancelar)

        btnSalvar.setOnClickListener { finishRegistration() }
        btnCadastrar.setOnClickListener { cancelRegistration() }
    }

    private fun finishRegistration() {
        val description = etCadastrarFofoca.text.toString().trim()
        val veracity = rbCadastrarVerdade.isChecked

        if (description.isBlank()) {
            Toast.makeText(this, "DESCRIÇÃO DE FOFOCA INVÁLIDA", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent().apply {
                putExtra("FOFOCA", Fofoca(description, veracity))
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun cancelRegistration() {
        finish()
    }
}