package com.aone.quickutility


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

//This class creates a fragment that displays a calendar.
// It allows the user to select a date and shows the selected date in a TextView
class CalendarFragment : Fragment() {
    private lateinit var calendarView: CalendarView
    private lateinit var selectedDateTextView: TextView

    //The fragment's layout is inflated from the XML file Resource layout fragment_calendar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        calendarView = view.findViewById(R.id.calendar_view)
        selectedDateTextView = view.findViewById(R.id.selected_date_text_view)


        //A listener is set on calendarView to respond to date changes
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            updateSelectedDate(selectedDate.time)
        }

        // Set initial selected date
        updateSelectedDate(Calendar.getInstance().time)

        return view
    }

    private fun updateSelectedDate(date: Date) {
        val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
        selectedDateTextView.text = "Selected Date: ${dateFormat.format(date)}"
    }
}