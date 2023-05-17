package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myapplication.databinding.ActivityAddWorkoutBinding
import com.ezatpanah.roomdatabase_youtube.db.Workout
import com.google.android.material.snackbar.Snackbar

class AddWorkoutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddWorkoutBinding

    // make an object of database to access the methods
    private val workoutDB : WorkoutDatabase by lazy {
        Room.databaseBuilder(this,WorkoutDatabase::class.java,"workoutsDTB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var workoutEntity: Workout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val name = edtTitle.text.toString()
                val time = timeEdit.text.toString()
                val calories = caloriesEdit.text.toString()
                val desc = textText.text.toString()

                /* if title and description of a note are not empty, then data can be inserted and
                note can be saved in the database */
                if (name.isNotEmpty() || time.isNotEmpty() || calories.isNotEmpty()){
                    workoutEntity= Workout(0,name,desc,time.toInt(), calories.toInt(), R.drawable.workout)
                    workoutDB.dao().insertWorkout(workoutEntity)
                    finish()
                }
                else{
                    Snackbar.make(it,"Name, time and calories must be filled!",Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }
}