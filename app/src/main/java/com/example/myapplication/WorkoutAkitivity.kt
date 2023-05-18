package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityWorkoutAkitivityBinding
import com.ezatpanah.roomdatabase_youtube.db.Workout

class WorkoutAkitivity : AppCompatActivity() {
    lateinit var binding: ActivityWorkoutAkitivityBinding

    // creating object of our database
    private val workoutDB : WorkoutDatabase by lazy {
        Room.databaseBuilder(this,WorkoutDatabase::class.java, "workoutsDTB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val workoutAdapter by lazy { WorkoutAdapter() }
    private var totalMin = 0
    private var totalCal = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWorkoutAkitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this,AddWorkoutActivity::class.java))
        }

        workoutDB.dao().getAllWorkouts().forEach {
            totalMin += it.time
            totalCal += it.calories
        }
        binding.idTVCalories.text = totalCal.toString()
        binding.idTVTime.text = totalMin.toString()
    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }

    private fun checkItem(){
        binding.apply {
            workoutAdapter.differ.submitList(workoutDB.dao().getAllWorkouts())
            setupRecyclerView()
            totalMin = 0
            totalCal = 0
            workoutDB.dao().getAllWorkouts().forEach {
                totalMin += it.time
                totalCal += it.calories
            }
            idTVCalories.text = totalCal.toString()
            idTVTime.text = totalMin.toString()
        }
    }

    private fun setupRecyclerView(){
        binding.idWorkoutRV.apply {
            layoutManager=LinearLayoutManager(this@WorkoutAkitivity)
            adapter=workoutAdapter
        }

    }
}