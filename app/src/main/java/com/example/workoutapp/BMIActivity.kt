package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
            supportActionBar?.title = "BMI Calculator"
        }

        bmi_binding?.toolbarBmiActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        bmi_binding?.btnCalculateUnits?.setOnClickListener{
            if(validateMetricUnits()){
                val heightValue : Float =
                    bmi_binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                val weightValue : Float =
                    bmi_binding?.etMetricUnitWeight?.text.toString().toFloat() / 100

                val bmi = weightValue/(heightValue*heightValue)

                //TODO display results
            }else{
                Toast.makeText(this,"Please enter valid numbers.",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun displayBMIResult(bmi:Float){

        val bmilabel : String
        val bmiDescription: String

        if(bmi.compareTo(15f))

        bmi_binding?.llDiplayBMIResult?.visibility = View.VISIBLE

    }

    private fun validateMetricUnits():Boolean {
        var isValid:Boolean = true

        if (bmi_binding?.etMetricUnitWeight?.text.toString().isEmpty() ||
            bmi_binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }

        return isValid
    }

    override fun onDestroy(){
        super.onDestroy()
        bmi_binding = null
    }
}