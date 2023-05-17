package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.room.Room
import com.example.myapplication.databinding.ActivityWorkoutDetailBinding
import com.ezatpanah.roomdatabase_youtube.db.Workout
import java.util.Locale

class WorkoutDetailActivity : AppCompatActivity() {
    private var mTimerRunning = false;
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeft: Long = 0
    private var timeStart: Long = 0

    private lateinit var binding: ActivityWorkoutDetailBinding
    private val workoutDB: WorkoutDatabase by lazy {
        Room.databaseBuilder(this, WorkoutDatabase::class.java, "workoutsDTB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private lateinit var workoutEntity: Workout
    private var workoutId = 0
    private var defaultName = ""
    private var defaultDesc = ""
    private var defaultTime:Int = 0
    private var defaultCalories:Int = 0
    private var defaultImg = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            workoutId = it.getInt("bundle_workout_id")
        }

        binding.apply {
            defaultName = workoutDB.dao().getWorkout(workoutId).name
            defaultDesc = workoutDB.dao().getWorkout(workoutId).text
            defaultTime = workoutDB.dao().getWorkout(workoutId).time
            defaultCalories = workoutDB.dao().getWorkout(workoutId).calories
            defaultImg = workoutDB.dao().getWorkout(workoutId).img

            idTVWorkoutDetail.text = defaultName
            idTVWorkoutDetailText.text = getString(R.string.descriptionText) + defaultDesc
            idTVWorkoutDetailCalories.text = getString(R.string.caloriesText) + defaultCalories.toString() + " KCAL"
            idTVWorkoutDetailTime.text = getString(R.string.timeText) + defaultTime.toString() + " min"
            idTVClock.text = defaultTime.toString()
            idIVWorkoutDetail.setImageResource(defaultImg)

            btnDelete.setOnClickListener {
                workoutEntity= Workout(workoutId,defaultName,defaultDesc,defaultTime,defaultCalories,defaultImg)
                workoutDB.dao().deleteWorkout(workoutEntity)
                finish()
            }
        }

        timeLeft = defaultTime.toLong() * 60000
        timeStart = defaultTime.toLong() * 60000

        binding.idFAB1.setOnClickListener{
            if(mTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        binding.idFAB2.setOnClickListener{
            resetTimer()
        }

        updateClock()

    }

    private fun resetTimer() {
        countDownTimer.cancel()
        mTimerRunning = false
        binding.idFAB1.setImageResource(R.drawable.ic_play)
        timeLeft = timeStart
        updateClock()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        mTimerRunning = false
        binding.idFAB1.setImageResource(R.drawable.ic_play)
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateClock()
            }

            override fun onFinish() {
                mTimerRunning = false
                binding.idTVClock.text = getString(R.string.finish)
            }
        }.start()
        mTimerRunning = true
        binding.idFAB1.setImageResource(R.drawable.ic_pause)
    }

    private fun updateClock() {
        val minutes: Int = ((timeLeft / 1000)/60).toInt()
        val seconds: Int = ((timeLeft / 1000)%60).toInt()

        val timeLeftString = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds)
        binding.idTVClock.text = timeLeftString
    }
}