package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private lateinit var workoutLL: LinearLayout
    private lateinit var stepCounterLL: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workoutLL = findViewById(R.id.idLLWorkout)
        stepCounterLL = findViewById(R.id.idLLStepCounter)

        workoutLL.setOnClickListener {
            val i = Intent(this@MainActivity, WorkoutAkitivity::class.java)
            startActivity(i)
        }

        stepCounterLL.setOnClickListener {
            val i = Intent(this@MainActivity, StepCounterAktivity::class.java)
            startActivity(i)
        }
    }
}