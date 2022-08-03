package com.example.workoutapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityFinishBinding
import android.widget.Toolbar

class FinishActivity : AppCompatActivity() {

    private var finishBinding: ActivityFinishBinding? = null

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finishBinding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(finishBinding?.root)

        setSupportActionBar(finishBinding?.toolbarFinishActivity)

        if (actionBar !=null){
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        finishBinding?.toolbarFinishActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        finishBinding?.btnFinish?.setOnClickListener{
            finish()
        }
    }
}
