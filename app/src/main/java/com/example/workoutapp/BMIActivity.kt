package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = " US_UNIT_VIEW"
    }

    // default unit view, this is the value that will make a selected view visible
    private var currentVisibleView: String = METRIC_UNITS_VIEW

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

        makeUsUnitsVisible()

        bmi_binding?.rgUnits?.setOnCheckedChangeListener{_, checkerID: Int ->
            if (checkerID == R.id.rbMetricUnits){
                makeMetricUnitsVisible()
            }else{
                makeUsUnitsVisible()
            }
        }

        bmi_binding?.btnCalculateUnits?.setOnClickListener{
            if (currentVisibleView == METRIC_UNITS_VIEW) {
                if (validateMetricUnits()) {
                    val heightValue: Float =
                        bmi_binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                    val weightValue: Float =
                        bmi_binding?.etMetricUnitWeight?.text.toString().toFloat() / 100

                    val bmi = weightValue / (heightValue * heightValue)

                    displayBMIResult(bmi)

                } else {
                    Toast.makeText(
                        this, "Please enter valid numbers.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                if (validateUsUnits()){
                    val height_feet: Float =
                        bmi_binding?.etUsUnitHeightFeet?.text.toString().toFloat()

                    val height_inches: Float =
                        bmi_binding?.etUsUnitHeightInch?.text.toString().toFloat()

                    val pounds: Float =
                        bmi_binding?.etUsUnitWeight?.text.toString().toFloat()

                    val heightValue: Float = height_feet * 12 + height_inches

                    val bmi: Float = 703 * (pounds / (heightValue * heightValue))

                    displayBMIResult(bmi)

                }else{
                    Toast.makeText(
                        this, "Please enter valid numbers.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }


    private fun makeMetricUnitsVisible(){
        currentVisibleView = METRIC_UNITS_VIEW

        bmi_binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        bmi_binding?.tilMetricUnitWeight?.visibility = View.VISIBLE

        bmi_binding?.tilUsUnitWeight?.visibility = View.INVISIBLE
        bmi_binding?.tilUsUnitHeightFeet?.visibility = View.INVISIBLE
        bmi_binding?.tilUsUnitHeightInch?.visibility = View.INVISIBLE

        bmi_binding?.etMetricUnitHeight?.text!!.clear()
        bmi_binding?.etMetricUnitWeight?.text!!.clear()

        bmi_binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeUsUnitsVisible(){
        currentVisibleView = US_UNITS_VIEW

        bmi_binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        bmi_binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE

        bmi_binding?.tilUsUnitWeight?.visibility = View.VISIBLE
        bmi_binding?.tilUsUnitHeightFeet?.visibility = View.VISIBLE
        bmi_binding?.tilUsUnitHeightInch?.visibility = View.VISIBLE

        bmi_binding?.etUsUnitWeight?.text!!.clear()
        bmi_binding?.etUsUnitHeightFeet?.text!!.clear()
        bmi_binding?.etUsUnitHeightInch?.text!!.clear()

        bmi_binding?.llDiplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float){

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

        if (bmi_binding?.etMetricUnitWeight?.text.toString().isEmpty() ||
            bmi_binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            return false
        }
        return true
    }

    private fun validateUsUnits():Boolean {

        if (bmi_binding?.etUsUnitWeight?.text.toString().isEmpty() ||
            bmi_binding?.etUsUnitHeightFeet?.text.toString().isEmpty() ||
            bmi_binding?.etUsUnitHeightInch?.text.toString().isEmpty()){
            return false
        }
        return true
    }

    override fun onDestroy(){
        super.onDestroy()
        bmi_binding = null
    }
}