package com.example.workoutapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private var history_binding: ActivityHistoryBinding ?= null

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        history_binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(history_binding?.root)

        setSupportActionBar(history_binding?.toolbarHistoryActivity)

        if (supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI Calculator"
        }

        history_binding?.toolbarHistoryActivity?.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}