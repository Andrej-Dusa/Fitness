package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    val workoutLL: LinearLayout = findViewById(R.id.idLLWorkout);
    val stepCounterLL: LinearLayout = findViewById(R.id.idLLStepCounter);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workoutLL.setOnClickListener {view->
            val i: Intent = Intent(this@MainActivity, WorkoutAkitivity::class.java);
            startActivity(i);
        }

        stepCounterLL.setOnClickListener { view->
            val i: Intent = Intent(this@MainActivity, StepCounterAktivity::class.java);
            startActivity(i);
        }
    }
}