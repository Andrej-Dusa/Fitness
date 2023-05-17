package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutAkitivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutList: ArrayList<Workout>
    private lateinit var workoutAdapter: WorkoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_akitivity)

        recyclerView = findViewById(R.id.idWorkoutRV)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        workoutList = ArrayList()

        workoutList.add(Workout(R.drawable.plank, "Plank", "Best for core muscles", 5, 30))
        workoutList.add(Workout(R.drawable.crunches, "Crunches", "Best for upper core muscles", 3, 20))
        workoutList.add(Workout(R.drawable.pushups, "Push ups", "Really good for biceps and triceps muscles", 2,35))
        workoutList.add(Workout(R.drawable.squat, "Squats", "Good for exercising thigh muscles", 6,30))
        workoutList.add(Workout(R.drawable.lunge, "Lunges", "Good for exercising thigh muscles", 4,35))
        workoutList.add(Workout(R.drawable.jack, "Hollow hold to jackknifes", "Best exercise for whole body", 3,50))

        workoutAdapter = WorkoutAdapter(workoutList)
        recyclerView.adapter = workoutAdapter

        workoutAdapter.onItemClick = {
            val intent = Intent(this, WorkoutDetailActivity::class.java)
            intent.putExtra("workout", it)
            startActivity(intent)
        }
    }
}