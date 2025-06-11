package com.example.zenmode

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zenmode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hour Picker (1 to 12)
        binding.hourPicker.minValue = 1
        binding.hourPicker.maxValue = 12
        binding.hourPicker.wrapSelectorWheel = true
        // Minute Picker (0 to 59)
        binding.minutePicker.minValue = 0
        binding.minutePicker.maxValue = 59
        binding.minutePicker.setFormatter { String.format("%02d", it) }
        binding.minutePicker.wrapSelectorWheel = true
        // AM/PM Picker
        binding.amPmPicker.minValue = 0
        binding.amPmPicker.maxValue = 1
        binding.amPmPicker.displayedValues = arrayOf("AM", "PM")
        binding.datePicker.minDate = System.currentTimeMillis()

binding.startButton.setOnClickListener{
    val year = binding.datePicker.year
    val month = binding.datePicker.month
    val day = binding.datePicker.dayOfMonth
    val hour = binding.hourPicker.value + if (binding.amPmPicker.value == 1) 12 else 0
    val minute = binding.minutePicker.value

    val calendar = java.util.Calendar.getInstance()
    calendar.set(year, month, day, hour, minute, 0)
    val unlockTime = calendar.timeInMillis
    val intent = Intent(this, Lock::class.java)
    intent.putExtra("UNLOCK_TIME", unlockTime)
    startActivity(intent)
}
    }
}
