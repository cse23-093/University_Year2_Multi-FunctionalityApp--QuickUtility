package com.aone.quickutility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorFragment : Fragment() {
    //displays results in view
    private lateinit var resultTextView: TextView

    //keeps track of the current mathematical expression
    private var currentExpression = StringBuilder()

    //The fragment's layout is inflated from the XML file Resource file layout fragment_calculator
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        //links textview from layout file
        resultTextView = view.findViewById(R.id.result_text_view)


        val buttons = listOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_plus, R.id.button_minus, R.id.button_multiply, R.id.button_divide,
            R.id.button_dot, R.id.button_clear, R.id.button_equals
        )

        buttons.forEach { buttonId ->
            view.findViewById<Button>(buttonId).setOnClickListener { onButtonClick(it) }
        }

        return view
    }

    //listen for when each button is clicked
    private fun onButtonClick(view: View) {
        when ((view as Button).text.toString()) {
            "=" -> calculateResult()
            "C" -> clearExpression()

            //other buttons, it calls appendToExpression() to add the button's text to the current expression
            else -> appendToExpression((view as Button).text.toString())
        }
    }

    private fun appendToExpression(value: String) {
        currentExpression.append(value)
        resultTextView.text = currentExpression.toString()
    }

    private fun clearExpression() {
        currentExpression.clear()
        resultTextView.text = "0"
    }

    private fun calculateResult() {
        try {
            val expression = ExpressionBuilder(currentExpression.toString()).build()
            val result = expression.evaluate()
            resultTextView.text = result.toString()
            currentExpression.clear()
            currentExpression.append(result)
        } catch (e: Exception) {
            resultTextView.text = "Error"
            currentExpression.clear()
        }
    }
}