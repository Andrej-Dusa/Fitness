package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class WorkoutDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_detail)

        val workout = intent.getParcelableExtra<Workout>("workout")
        if (workout != null){
            val textNameView: TextView = findViewById(R.id.idTVWorkoutDetail)
            val textTextView: TextView = findViewById(R.id.idTVWorkoutDetailText)
            val textTimeView: TextView = findViewById(R.id.idTVWorkoutDetailTime)
            val textCaloriesView: TextView = findViewById(R.id.idTVWorkoutDetailCalories)
            val imageView: ImageView = findViewById(R.id.idIVWorkoutDetail)

            textNameView.text = workout.name
            textTextView.text = workout.text
            textTimeView.text = "Time: " + workout.time.toString() + " min"
            textCaloriesView.text = "Calories: " + workout.calories.toString()
            imageView.setImageResource(workout.image)
        }
    }
}