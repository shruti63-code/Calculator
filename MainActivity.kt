package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var lastOperator = ""
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { numberPressed((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { operatorPressed("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { operatorPressed("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { operatorPressed("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { operatorPressed("/") }
        findViewById<Button>(R.id.btnModulus).setOnClickListener { operatorPressed("%") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clear() }
    }

    private fun numberPressed(number: String) {
        if (isNewOperation) {
            tvResult.text = ""
            isNewOperation = false
        }
        currentInput += number
        tvResult.text = currentInput
    }

    private fun operatorPressed(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            lastOperator = operator
            currentInput = ""
            isNewOperation = true
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && lastOperator.isNotEmpty()) {
            secondNumber = currentInput.toDouble()

            val result = when (lastOperator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Error"
                "%" -> firstNumber % secondNumber
                else -> "Error"
            }

            tvResult.text = result.toString()
            currentInput = ""
            isNewOperation = true
        }
    }

    private fun clear() {
        currentInput = ""
        firstNumber = 0.0
        secondNumber = 0.0
        lastOperator = ""
        isNewOperation = true
        tvResult.text = "0"
    }
}







