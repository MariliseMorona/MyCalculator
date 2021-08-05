package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        txt_visor.text = "0"
        //resultadoText.text = "0"
        operacion = SIN_OPERACION

        btn_one.setOnClickListener { numberPressed("1") }
        btn_two.setOnClickListener { numberPressed("2") }
        btn_three.setOnClickListener { numberPressed("3") }
        btn_four.setOnClickListener { numberPressed("4") }
        btn_five.setOnClickListener { numberPressed("5") }
        btn_six.setOnClickListener { numberPressed("6") }
        btn_seven.setOnClickListener { numberPressed("7") }
        btn_eight.setOnClickListener { numberPressed("8") }
        btn_nine.setOnClickListener { numberPressed("9") }
        btn_zero.setOnClickListener { numberPressed("0") }


        btn_sum.setOnClickListener { operationPressed(SUMA) }
        btn_subtraction.setOnClickListener { operationPressed(RESTA) }
        btn_multiplication.setOnClickListener { operationPressed(MULTIPLICACION) }
        btn_division.setOnClickListener { operationPressed(DIVISION) }

        btn_equals.setOnClickListener { resolvePressed() }
        }

        private fun numberPressed(num: String){
            if(txt_visor.text == "0" && num != ".") {
                txt_visor.text = "$num"
            } else {
                txt_visor.text = "${txt_visor.text}$num"
            }

            if(operacion == SIN_OPERACION){
                num1 = txt_visor.text.toString().toDouble()
            } else {
                num2 = txt_visor.text.toString().toDouble()
            }
        }

        private fun operationPressed(operacion: Int){
            this.operacion = operacion
            num1 = txt_visor.text.toString().toDouble()

            txt_visor.text = "0"
        }

        private fun resolvePressed(){

            val result = when(operacion) {
                SUMA -> num1 + num2
                RESTA -> num1 - num2
                MULTIPLICACION -> num1 * num2
                DIVISION -> num1 / num2
                else -> 0
            }

            num1 = result as Double

            txt_visor.text = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }
        }

        private fun resetAll(){
            txt_visor.text = "0"
            num1 = 0.0
            num2 = 0.0
        }

        companion object {
            const val SUMA = 1
            const val RESTA = 2
            const val MULTIPLICACION = 3
            const val DIVISION = 4
            const val SIN_OPERACION = 0
        }
    }