package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

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

                displayBMIResult(bmi)

            }else{
                Toast.makeText(this,"Please enter valid numbers.",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun displayBMIResult(bmi:Float){

        val bmiLabel : String
        val bmiDescription: String

        if(bmi.compareTo(15f)<=0){
            bmiLabel="Severely Underweight"
            bmiDescription="Eat more food."
        }else if (bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmiLabel="Very underweight"
            bmiDescription="Eat more food"
        }else if (bmi.compareTo(16f)>0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel="Underweight"
            bmiDescription = "Eat more food"
        }else if (bmi.compareTo(18.5f)> 0 && bmi.compareTo(25f)<=0){
            bmiLabel="Normal"
            bmiDescription="Your BMI is within normal parameters."
        }else {
            bmiLabel = "Obese Class III"
            bmiDescription = "Seek medical professional"
        }
                // TODO finish BMI levels, consider making enum.

        val bmiValue:String=BigDecimal(bmi.toDouble()).setScale(2,
            RoundingMode.HALF_EVEN).toString()

        bmi_binding?.llDiplayBMIResult?.visibility = View.VISIBLE
        bmi_binding?.tvBMIValue?.text = bmiValue
        bmi_binding?.tvBMIType?.text = bmiLabel
        bmi_binding?.tvBMIDescription?.text = bmiDescription

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