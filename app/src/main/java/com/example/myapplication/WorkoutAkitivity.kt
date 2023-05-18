package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityWorkoutAkitivityBinding
import com.ezatpanah.roomdatabase_youtube.db.Workout

class WorkoutAkitivity : AppCompatActivity() {
    //data binding pre pistup k layotu
    lateinit var binding: ActivityWorkoutAkitivityBinding

    // vytvorenie databazy
    private val workoutDB : WorkoutDatabase by lazy {
        Room.databaseBuilder(this,WorkoutDatabase::class.java, "workoutsDTB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val workoutAdapter by lazy { WorkoutAdapter() }

    //premenne pre celkovy cas a kalorie
    private var totalMin = 0
    private var totalCal = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWorkoutAkitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //tlacidlo pre otvorenie formulara pridania cviku
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this,AddWorkoutActivity::class.java))
        }

        //naplnenie premennych celkoveho casu a kalorii
        workoutDB.dao().getAllWorkouts().forEach {
            totalMin += it.time
            totalCal += it.calories
        }
        binding.idTVCalories.text = totalCal.toString()
        binding.idTVTime.text = totalMin.toString()
    }

    //nacitanie zoznamu po opetovnom prichode do aktivity
    override fun onResume() {
        super.onResume()
        checkItem()
    }

    //vypisananie zoznamu aktivit
    private fun checkItem(){
        binding.apply {
            workoutAdapter.differ.submitList(workoutDB.dao().getAllWorkouts())
            setupRecyclerView()
            totalMin = 0
            totalCal = 0
            //naplnenie premennych celkoveho casu a kalorii
            workoutDB.dao().getAllWorkouts().forEach {
                totalMin += it.time
                totalCal += it.calories
            }
            idTVCalories.text = totalCal.toString()
            idTVTime.text = totalMin.toString()
        }
    }

    //priprava recyclerView
    private fun setupRecyclerView(){
        binding.idWorkoutRV.apply {
            layoutManager=LinearLayoutManager(this@WorkoutAkitivity)
            adapter=workoutAdapter
        }

    }
}