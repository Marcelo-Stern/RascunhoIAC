package com.example.iaccalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCalc: Button = findViewById(R.id.btnCalc)
        btCalc.setOnClickListener{
            calcIAC()
        }
    }

    private val quad: EditText = findViewById(R.id.edtQuadril)
    private val alt: EditText = findViewById(R.id.edtAltura)

    private fun calcIAC(){
        if(quad.text.isEmpty() || alt.text.isEmpty()){
            Toast.makeText(this, "Preencha os campos corretamente para efetuar o cálculo!", Toast.LENGTH_SHORT).show()
            return
        }else{
            val resultado = iac()
            Toast.makeText(this, "O seu Índice de Adiposidade Corporal é: $resultado", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun iac(): Double {
        val inputQuad = quad.text.toString()
        val inputAlt = alt.text.toString()

        val quad = inputQuad.toDouble()
        val alt = inputAlt.toDouble()

        val denom = alt * kotlin.math.sqrt(alt)
        val resultIAC = quad / denom - 18
        return resultIAC
    }
}