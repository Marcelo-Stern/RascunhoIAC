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

    val quadril: EditText = findViewById(R.id.edtQuadril)
    val alt: EditText = findViewById(R.id.edtAltura)

    private fun calcIAC(){
        val quadril: EditText = findViewById(R.id.edtQuadril)
        val alt: EditText = findViewById(R.id.edtAltura)

        if(quadril.text.isEmpty() || alt.text.isEmpty()){
            Toast.makeText(this, "Preencha os campos corretamente para efetuar o cálculo!", Toast.LENGTH_SHORT).show()
            return
        }else{
            val resultado = iac()
            Toast.makeText(this, "O seu Índice de Adiposidade Corporal é: , resultado", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun iac() {
        val inputQuadril = quadril.text.toString()
        val inputAlt = alt.text.toString()

        val quadril = inputQuadril.toDouble()
        val altura = inputAlt.toDouble()

        val denom = altura * sqrt(altura)
        val resultIAC = quadril / denom - 18
    }
}