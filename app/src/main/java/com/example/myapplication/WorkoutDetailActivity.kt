package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Locale

class WorkoutDetailActivity : AppCompatActivity() {
    private lateinit var fab1: FloatingActionButton
    private lateinit var fab2: FloatingActionButton
    private lateinit var textClockView: TextView
    private var mTimerRunning = false;
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeft: Long = 0
    private var timeStart: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_detail)

        val workout = intent.getParcelableExtra<Workout>("workout")
        if (workout != null){
            val textNameView: TextView = findViewById(R.id.idTVWorkoutDetail)
            textClockView= findViewById(R.id.idTVClock)
            val textTextView: TextView = findViewById(R.id.idTVWorkoutDetailText)
            val textTimeView: TextView = findViewById(R.id.idTVWorkoutDetailTime)
            val textCaloriesView: TextView = findViewById(R.id.idTVWorkoutDetailCalories)
            val imageView: ImageView = findViewById(R.id.idIVWorkoutDetail)

            fab1 = findViewById(R.id.idFAB1)
            fab2 = findViewById(R.id.idFAB2)

            timeLeft = workout.time.toLong() * 60000
            timeStart = workout.time.toLong() * 60000

            textNameView.text = workout.name
            textTextView.text = workout.text
            updateClock()
            textTimeView.text = "Time: " + workout.time.toString() + " min"
            textCaloriesView.text = "Calories: " + workout.calories.toString() + " KCAL"
            imageView.setImageResource(workout.image)

            fab1.setOnClickListener{
                if(mTimerRunning) {
                    pauseTimer()
                } else {
                    startTimer()
                }
            }

            fab2.setOnClickListener{
                resetTimer()
            }
        }
    }

    private fun resetTimer() {
        countDownTimer.cancel()
        mTimerRunning = false
        fab1.setImageResource(R.drawable.ic_play)
        timeLeft = timeStart
        updateClock()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        mTimerRunning = false
        fab1.setImageResource(R.drawable.ic_play)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateClock()
            }

            override fun onFinish() {
                mTimerRunning = false
                textClockView.text = getString(R.string.finish)
            }
        }.start()
        mTimerRunning = true
        fab1.setImageResource(R.drawable.ic_pause)
    }

    private fun updateClock() {
        val minutes: Int = ((timeLeft / 1000)/60).toInt()
        val seconds: Int = ((timeLeft / 1000)%60).toInt()

        val timeLeftString = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds)
        textClockView.text = timeLeftString
    }
}