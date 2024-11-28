package com.aone.quickutility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

//This class creates a fragment that allows users to convert values between different units
class ConverterFragment : Fragment() {

    //A spinner (dropdown menu) for selecting the unit to convert from
    private lateinit var fromSpinner: Spinner
    //A spinner for selecting the unit to convert to
    private lateinit var toSpinner: Spinner
    private lateinit var fromEditText: EditText
    private lateinit var toEditText: EditText
    private lateinit var convertButton: Button

    private val units = arrayOf("Meters", "Feet", "Kilometers", "Miles")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_converter, container, false)

        fromSpinner = view.findViewById(R.id.from_spinner)
        toSpinner = view.findViewById(R.id.to_spinner)
        fromEditText = view.findViewById(R.id.from_edit_text)
        toEditText = view.findViewById(R.id.to_edit_text)
        convertButton = view.findViewById(R.id.convert_button)


        //An ArrayAdapter is created to populate the spinners with the units array
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        convertButton.setOnClickListener { convert() }

        return view
    }

    private fun convert() {
        val fromValue = fromEditText.text.toString().toDoubleOrNull()
        if (fromValue == null) {
            Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        val fromUnit = fromSpinner.selectedItem.toString()
        val toUnit = toSpinner.selectedItem.toString()

        val result = when {
            fromUnit == "Meters" && toUnit == "Feet" -> fromValue * 3.28084
            fromUnit == "Feet" && toUnit == "Meters" -> fromValue / 3.28084
            fromUnit == "Kilometers" && toUnit == "Miles" -> fromValue * 0.621371
            fromUnit == "Miles" && toUnit == "Kilometers" -> fromValue / 0.621371
            else -> fromValue // Same unit, no conversion needed
        }

        toEditText.setText(String.format("%.2f", result))
    }
}