package com.example.myapplication

import androidx.room.*
import com.ezatpanah.roomdatabase_youtube.db.Workout

@Dao
interface WorkoutDao {

    // onConflict is used for replacing old data and continue the transaction with the new data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(noteEntity: Workout)

    @Update
    fun updateWorkout(noteEntity: Workout)

    @Delete
    fun deleteWorkout(noteEntity: Workout)

    // getting all the notes and showing them to the recyclerView in the WorkoutActivity
    @Query("SELECT * FROM 'workouts' ORDER BY id DESC")
    fun getAllWorkouts() : MutableList<Workout>

    // selecting one note at a time
    @Query("SELECT * FROM 'workouts' WHERE id LIKE :id")
    fun getWorkout(id : Int) : Workout

}