package com.example.androidfirstlab

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.udojava.evalex.Expression
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var resultTextView: TextView? = null
    var inputTextView: TextView? = null
    var expressionInput: String = "Input"
    companion object {
        const val EXPRESSION_INPUT = "expressionInput"
        const val EXPRESSION_RESULT = "expressionResult"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zeroButton = findViewById<Button>(R.id.zeroButton)
        val oneButton = findViewById<Button>(R.id.oneButton)
        val twoButton = findViewById<Button>(R.id.twoButton)
        val threeButton = findViewById<Button>(R.id.threeButton)
        val fourButton = findViewById<Button>(R.id.fourButton)
        val fiveButton = findViewById<Button>(R.id.fiveButton)
        val sixButton = findViewById<Button>(R.id.sixButton)
        val sevenButton = findViewById<Button>(R.id.sevenButton)
        val eightButton = findViewById<Button>(R.id.eightButton)
        val nineButton = findViewById<Button>(R.id.nineButton)

        val dotButton = findViewById<Button>(R.id.dotButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val subtractionButton = findViewById<Button>(R.id.subtractionButton)
        val additionButton = findViewById<Button>(R.id.additionButton)
        val percentButton = findViewById<Button>(R.id.percentButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val deleteButton = findViewById<ImageButton>(R.id.deleteButton)
        val getResultButton = findViewById<Button>(R.id.getResultButton)

        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            val leftParanthesisButton = findViewById<Button>(R.id.leftParanthesisButton)
            val rightParanthesisButton = findViewById<Button>(R.id.rightParanthesisButton)
            val powerButton = findViewById<Button>(R.id.powerButton)
            val eButton = findViewById<Button>(R.id.eButton)
            val piButton = findViewById<Button>(R.id.piButton)

            val sinButton = findViewById<Button>(R.id.sinButton)
            val cosButton = findViewById<Button>(R.id.cosButton)
            val tanButton = findViewById<Button>(R.id.tanButton)
            val logButton = findViewById<Button>(R.id.logButton)
            val cotButton = findViewById<Button>(R.id.cotButton)

            leftParanthesisButton.setOnClickListener { addSymbolToInputTextView("(") }
            rightParanthesisButton.setOnClickListener { addSymbolToInputTextView(")") }
            powerButton.setOnClickListener { addSymbolToInputTextView("^") }
            eButton.setOnClickListener { addSymbolToInputTextView("e") }
            piButton.setOnClickListener { addSymbolToInputTextView("PI") }

            sinButton.setOnClickListener { addSymbolToInputTextView("sin(") }
            cosButton.setOnClickListener { addSymbolToInputTextView("cos(") }
            tanButton.setOnClickListener { addSymbolToInputTextView("tan(") }
            logButton.setOnClickListener { addSymbolToInputTextView("log(") }
            cotButton.setOnClickListener { addSymbolToInputTextView("cot(") }
        }

        resultTextView = findViewById<TextView>(R.id.resultTextView)
        inputTextView = findViewById<TextView>(R.id.inputTextView)

        zeroButton.setOnClickListener { addSymbolToInputTextView("0") }
        oneButton.setOnClickListener { addSymbolToInputTextView("1") }
        twoButton.setOnClickListener { addSymbolToInputTextView("2") }
        threeButton.setOnClickListener { addSymbolToInputTextView("3") }
        fourButton.setOnClickListener { addSymbolToInputTextView("4") }
        fiveButton.setOnClickListener { addSymbolToInputTextView("5") }
        sixButton.setOnClickListener { addSymbolToInputTextView("6") }
        sevenButton.setOnClickListener { addSymbolToInputTextView("7") }
        eightButton.setOnClickListener { addSymbolToInputTextView("8") }
        nineButton.setOnClickListener { addSymbolToInputTextView("9") }

        dotButton.setOnClickListener { addSymbolToInputTextView(".") }
        divideButton.setOnClickListener { addSymbolToInputTextView("/") }
        multiplyButton.setOnClickListener { addSymbolToInputTextView("*") }
        subtractionButton.setOnClickListener { addSymbolToInputTextView("-") }
        additionButton.setOnClickListener { addSymbolToInputTextView("+") }
        percentButton.setOnClickListener { addSymbolToInputTextView("%") }
        clearButton.setOnClickListener { clearInputTextField() }
        deleteButton.setOnClickListener { removeLastSymbol() }
        getResultButton.setOnClickListener { getResult() }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXPRESSION_INPUT, expressionInput)
        outState.putString(EXPRESSION_RESULT, resultTextView?.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        inputTextView?.text = savedInstanceState.getString(EXPRESSION_INPUT)
        resultTextView?.text = savedInstanceState.getString(EXPRESSION_RESULT)
    }

    fun addSymbolToInputTextView(symbol: String) {
        if (expressionInput == "Input") { expressionInput = "" }
        expressionInput += symbol
        inputTextView?.text = expressionInput
    }

    fun removeLastSymbol() {
        expressionInput = expressionInput.dropLast(1)
        inputTextView?.text = expressionInput
    }

    fun clearInputTextField() {
        expressionInput = ""
        inputTextView?.text = expressionInput
        resultTextView?.text = ""
    }

    fun getResult() {
        val expression = Expression(expressionInput)
        try {
            resultTextView?.text = expression.eval().toString()
        } catch (error: Expression.ExpressionException) {
            resultTextView?.text = "Incorrect input"
        }
    }

}