package com.aone.quickutility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*


//creates a fragment where users can add events with timestamps and display them in a list
class FlashEventsFragment : Fragment() {
    private lateinit var eventEditText: EditText
    private lateinit var addButton: Button
    private lateinit var eventsTextView: TextView
    private val events = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_flash_events, container, false)

        eventEditText = view.findViewById(R.id.event_edit_text)
        addButton = view.findViewById(R.id.add_button)
        eventsTextView = view.findViewById(R.id.events_text_view)

        addButton.setOnClickListener { addEvent() }

        return view
    }


    //This method adds the event from the EditText to the list and updates the display
    private fun addEvent() {
        val eventText = eventEditText.text.toString().trim()
        if (eventText.isNotEmpty()) {
            val timestamp = Calendar.getInstance().time
            val event = "$timestamp: $eventText"
            events.add(event)
            updateEventsDisplay()
            eventEditText.text.clear()
        }
    }


    //This method updates the eventsTextView with the list of events
    private fun updateEventsDisplay() {
        eventsTextView.text = events.joinToString("\n\n")
    }
}