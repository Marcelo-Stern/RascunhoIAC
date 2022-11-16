package com.example.composeiaccalc

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeiaccalc.ui.theme.ComposeIACCalcTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIACCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    calcIAC()
                }
            }
        }
    }
}


@Composable
fun calcIAC() {
    val focusManager = LocalFocusManager.current

    var txtQuadril by remember {
        mutableStateOf("")
    }
    var txtAltura by remember {
        mutableStateOf("")
    }
    val resultCalc by remember {
        mutableStateOf("")
    }
    fun calculadora(){
        val quad = txtQuadril.toDouble()
        val alt = txtAltura.toDouble()

        fun mToast(context: Context) {
            Toast.makeText(
                context,
                "Preencha os campos corretamente para efetuar o cálculo!",
                Toast.LENGTH_SHORT
            ).show()
        }

        if(quad.equals("") || alt.equals("")){
            val mContext = LocalContext.current
            mToast(mContext)
        }else{
            val resultado = quad / (alt * sqrt(alt)) - 18
            resultCalc == "$resultado"
        }
    }
    Column() {
        OutlinedTextField(
            value = txtQuadril,
            onValueChange = { txtQuadril = it },
            label = { Text(text = "Circunferência do Quadril (cm)") },
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        OutlinedTextField(
            value = txtAltura,
            onValueChange = { txtAltura = it },
            label = { Text(text = "Altura (m)") },
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Button(
            onClick = {
                calculadora()
            }
        ){
            Text(
                text = "Calcular", fontSize = 20.sp, fontFamily =  FontFamily.SansSerif
            )
        }

        Text(
            text = resultCalc,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            color = Color(101, 90, 194, 255)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeIACCalcTheme {
        calcIAC()
    }
}