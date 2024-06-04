package com.example.calculatrice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.calculatrice.ui.theme.CalculatriceTheme
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : ComponentActivity() {

    var displayResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatriceTheme {
                // Utilisation du fichier de mise en page XML
                val mainActivityLayout = R.layout.activity_main

                val view = layoutInflater.inflate(mainActivityLayout, null)
                setContentView(view)
            }
        }


    }


    fun receiveValue(view: View) {
        val button = view as Button
        val value = button.text.toString()
        if (this.displayResult) {
            clearCalcul(view)
            this.displayResult = false
        }
        display(value)
    }

    fun display(text: String) {
        val textView = findViewById<TextView>(R.id.textViewResult)
        val textBefore = textView.text.toString()
        val textAfter = textBefore + text
        textView.text = textAfter
    }

    fun evaluate(view: View) {
        val textView = findViewById<TextView>(R.id.textViewResult)
        this.displayResult = true
        try {
            val result = ExpressionBuilder(textView.text.toString()).build().evaluate()
            clearCalcul(view)
            display(result.toString())
            //println("Résultat de l'expression : $result")
        } catch (e: Exception) {
            clearCalcul(view)
            display("ERROR")
            println("Une exception s'est produite : ${e.message}")
            e.printStackTrace()
        }
    }

    fun clearCalcul(view: View) {
        val textView = findViewById<TextView>(R.id.textViewResult)
        if (textView.text.length !== 0) {
            textView.text = ""
        }
    }
}