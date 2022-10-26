package com.example.iaccalc

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quad: EditText = findViewById(R.id.edtQuadril)
        val alt: EditText = findViewById(R.id.edtAltura)
        val btCalc: Button = findViewById(R.id.btnCalc)

        val db = Firebase.firestore

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

            val calculaIAC = hashMapOf(
                "circunferenciaQuadril" to quad.text.toString(),
                "altura" to alt.text.toString(),
                "resultado" to calcIAC().toString()
            )

            // Add a new document with a generated ID
            db.collection("iac")
                .add(calculaIAC)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener{ e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}