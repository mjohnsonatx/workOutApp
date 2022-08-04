package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityBmiactivityBinding

class BMIActivity : AppCompatActivity() {

    private var bmi_binding:ActivityBmiactivityBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bmi_binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(bmi_binding?.root)

        // setting the action bar up
        setSupportActionBar(bmi_binding?.toolbarBmiActivity)

        if (supportActionBar !=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        bmi_binding?.toolbarBmiActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

    }

    override fun onDestroy(){
        super.onDestroy()

        bmi_binding = null
    }
}