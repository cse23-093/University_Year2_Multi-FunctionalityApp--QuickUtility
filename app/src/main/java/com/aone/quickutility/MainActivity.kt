package com.aone.quickutility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        // Set Calculator as the default fragment
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, CalculatorFragment()).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_calculator -> selectedFragment = CalculatorFragment()
            R.id.nav_converter -> selectedFragment = ConverterFragment()
            R.id.nav_flash_events -> selectedFragment = FlashEventsFragment()
            R.id.nav_calendar -> selectedFragment = CalendarFragment()
        }

        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
        }

        true
    }
}