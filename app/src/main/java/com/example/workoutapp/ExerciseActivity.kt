package com.example.workoutapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityExerciseBinding
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var exerciseBinding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress: Int = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress: Int = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null

    private var currentExercise: Int = -1

    private var tts:TextToSpeech? = null

    private var player: MediaPlayer? = null

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseBinding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseBinding?.root)

        // setting the action bar up
        setActionBar(exerciseBinding?.toolbarExercise)

        if (actionBar !=null){
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()

        // putting a back button on it
        exerciseBinding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }

        tts = TextToSpeech(this, this)

        setUpRestView()

    }

    private fun setUpRestView(){

        try{
            val soundURI = Uri.parse(
                "android.resource://com.example.workoutapp/"
                        + R.raw.app_src_main_res_raw_press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }

        exerciseBinding?.flProgressBar?.visibility = View.VISIBLE
        exerciseBinding?.tvTitle?.visibility = View.VISIBLE

        exerciseBinding?.nextExercise?.visibility = View.VISIBLE
        exerciseBinding?.UpcomingExercise?.visibility = View.VISIBLE


        exerciseBinding?.tvExercise?.visibility = View.INVISIBLE
        exerciseBinding?.ExerciseImage?.visibility = View.INVISIBLE
        exerciseBinding?.flExerciseView?.visibility = View.INVISIBLE

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        exerciseBinding?.ExerciseImage?.setImageResource(R.drawable.rest)
        exerciseBinding?.nextExercise?.text = exerciseList!![currentExercise+1].getName()

        setRestProgressBar()
    }

    private fun setRestProgressBar(){

        val nextWorkout = exerciseList!![currentExercise+1].getName()
        speakOut("Rest. The next exercise is $nextWorkout")
        exerciseBinding?.pbProgressBar?.progress = restProgress

        restTimer = object: CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                exerciseBinding?.pbProgressBar?.progress = 10 - restProgress
                exerciseBinding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercise++
                exerciseBinding?.tvTitle?.text = "Rest"
                setupExerciseView()
            }
        }.start()
    }

    private fun setupExerciseView(){

        exerciseBinding?.flProgressBar?.visibility = View.INVISIBLE
        exerciseBinding?.tvTitle?.visibility = View.INVISIBLE
        exerciseBinding?.nextExercise?.visibility = View.INVISIBLE
        exerciseBinding?.UpcomingExercise?.visibility = View.INVISIBLE

        exerciseBinding?.tvExercise?.visibility = View.VISIBLE
        exerciseBinding?.ExerciseImage?.visibility = View.VISIBLE
        exerciseBinding?.flExerciseView?.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0

        }

        exerciseBinding?.tvExercise?.text = exerciseList!![currentExercise].getName()
        exerciseBinding?.ExerciseImage?.setImageResource(exerciseList!![currentExercise].getImage())
        setExerciseProgressBar()

    }

    @SuppressLint("SetTextI18n")
    private fun setExerciseProgressBar() {
        speakOut("Begin ${exerciseList!![currentExercise].getName()}")
        exerciseBinding?.pbProgressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                exerciseBinding?.pbProgressBarExercise?.progress = 30 - exerciseProgress
                exerciseBinding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercise < exerciseList?.size!! - 1) {
                    restProgress = 0
                    setUpRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity,
                        "Congratulations! You have completed the 7 minute workout",
                        Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }


    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "The language specified is not supported.")
            }
        }else{
            Log.e("TTS", "Initialization Failed.")
        }
    }

    private fun speakOut(text: String){
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }


    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        if(tts != null){
            tts?.stop()
            tts?.shutdown()
        }

        if(player !=null){
            player!!.stop()
        }
        exerciseBinding = null

    }
}

