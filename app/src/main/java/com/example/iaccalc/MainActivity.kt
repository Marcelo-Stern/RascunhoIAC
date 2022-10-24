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

        val quad: EditText = findViewById(R.id.edtQuadril)
        val alt: EditText = findViewById(R.id.edtAltura)
        val btCalc: Button = findViewById(R.id.btnCalc)

        fun iac(): Double {
            val inputQuad = quad.text.toString()
            val inputAlt = alt.text.toString()

            val quad = inputQuad.toDouble()
            val alt = inputAlt.toDouble()

            val resultIAC = quad / (alt * kotlin.math.sqrt(alt)) - 18
            return resultIAC
        }

        fun calcIAC(){
            if(quad.text.isEmpty() || alt.text.isEmpty()){
                Toast.makeText(this, "Preencha os campos corretamente para efetuar o cálculo!", Toast.LENGTH_SHORT).show()
                return
            }else{
                val resultado = iac()
                Toast.makeText(this, "O seu Índice de Adiposidade Corporal é: $resultado", Toast.LENGTH_SHORT).show()
                return
            }
        }

        btCalc.setOnClickListener{
            calcIAC()
        }
    }
}