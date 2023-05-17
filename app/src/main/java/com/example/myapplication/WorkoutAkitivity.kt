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

        workoutList.add(Workout(R.drawable.workout, "Plank", "Dobre pre biceps", 10, 20))
        workoutList.add(Workout(R.drawable.workout, "Plank", "Dobre pre biceps", 10, 20))
        workoutList.add(Workout(R.drawable.workout, "Plank", "Dobre pre biceps", 10,20))
        workoutList.add(Workout(R.drawable.workout, "Plank", "Dobre pre biceps", 10,20))
        workoutList.add(Workout(R.drawable.workout, "Plank", "Dobre pre biceps", 10,20))
        workoutList.add(Workout(R.drawable.workout, "Plank", "Dobre pre biceps", 10,20))

        workoutAdapter = WorkoutAdapter(workoutList)
        recyclerView.adapter = workoutAdapter

        workoutAdapter.onItemClick = {
            val intent = Intent(this, WorkoutDetailActivity::class.java)
            intent.putExtra("workout", it)
            startActivity(intent)
        }
    }
}