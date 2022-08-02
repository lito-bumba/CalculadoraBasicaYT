package com.example.calculadorabasicayt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadorabasicayt.ui.theme.CalculadoraBasicaYTTheme
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tela()
        }
    }
}

@Composable
fun Botao(texto: String, modifier: Modifier = Modifier, click: () -> Unit = {}) {
    Button(
        onClick = { click() },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = modifier
            .width(100.dp)
            .height(110.dp)
    ) {
        Text(text = texto, fontSize = 20.sp)
    }
}

@Composable
fun Tela() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        var dadosTela by remember { mutableStateOf("0") }
        var resultado by remember { mutableStateOf("0") }

        Column(verticalArrangement = Arrangement.Center) {

            Text(
                text = dadosTela, fontSize = 58.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

            Text(
                text = resultado, fontSize = 28.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 15.dp)
            )

        }

        Row {
            Column {
                Row {
                    Botao(
                        texto = "7",
                        click = { dadosTela = entradaDados(dadosTela, '7') }
                    )
                    Botao(
                        texto = "8",
                        click = { dadosTela = entradaDados(dadosTela, '8') }
                    )
                    Botao(
                        texto = "9",
                        click = { dadosTela = entradaDados(dadosTela, '9') }
                    )
                }

                Row {
                    Botao(
                        texto = "4",
                        click = { dadosTela = entradaDados(dadosTela, '4') }
                    )
                    Botao(
                        texto = "5",
                        click = { dadosTela = entradaDados(dadosTela, '5') }
                    )
                    Botao(
                        texto = "6",
                        click = { dadosTela = entradaDados(dadosTela, '6') }
                    )
                }

                Row {
                    Botao(
                        texto = "1",
                        click = { dadosTela = entradaDados(dadosTela, '1') }
                    )
                    Botao(
                        texto = "2",
                        click = { dadosTela = entradaDados(dadosTela, '2') }
                    )
                    Botao(
                        texto = "3",
                        click = { dadosTela = entradaDados(dadosTela, '3') }
                    )
                }

                Row {
                    Botao(
                        texto = ",",
                        click = { dadosTela = entradaDados(dadosTela, '.') }
                    )
                    Botao(
                        texto = "0",
                        click = { dadosTela = entradaDados(dadosTela, '0') }
                    )
                    Botao(
                        texto = "=",
                        click = { resultado = calcular(dadosTela) }
                    )
                }
            }
            Column {
                Botao(
                    texto = "DEL",
                    modifier = Modifier
                        .height(100.dp)
                        .width(120.dp),
                    click = {
                        dadosTela = "0"
                        resultado = "0"
                    }
                )
                Botao(
                    texto = "/",
                    modifier = Modifier
                        .height(80.dp)
                        .width(130.dp),
                    click = { dadosTela = entradaDados(dadosTela, '/') }
                )
                Botao(
                    texto = "x",
                    modifier = Modifier
                        .height(80.dp)
                        .width(120.dp),
                    click = { dadosTela = entradaDados(dadosTela, '*') }
                )
                Botao(
                    texto = "-",
                    modifier = Modifier
                        .height(80.dp)
                        .width(120.dp),
                    click = { dadosTela = entradaDados(dadosTela, '-') }
                )
                Botao(
                    texto = "+",
                    modifier = Modifier
                        .height(100.dp)
                        .width(120.dp),
                    click = { dadosTela = entradaDados(dadosTela, '+') }
                )
            }
        }
    }
}

fun entradaDados(dadosTela: String, dado: Char): String {
    if (dado == '0' && dadosTela == "0")
        return "0"

    if (dadosTela == "0" && dado != '.')
        return dado.toString()

    return dadosTela + dado
}

fun calcular(dados: String): String {
    val calc = ExpressionBuilder(dados).build().evaluate()
    return calc.toString()
}

@Preview
@Composable
fun VerTela() {
    Tela()
}