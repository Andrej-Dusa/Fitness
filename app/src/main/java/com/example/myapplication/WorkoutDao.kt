package com.example.myapplication

import androidx.room.*
import com.ezatpanah.roomdatabase_youtube.db.Workout

//interface definujuci ukony ktore sa daju reobit nad tabukou z databazy
@Dao
interface WorkoutDao {

    // onConflict sa pouziva aby sa stare data nahradily novymi ak najde zhodu.
    //pridaie cviku do tabulky
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(noteEntity: Workout)

    //odstranienie cviku z tabulky
    @Delete
    fun deleteWorkout(noteEntity: Workout)

    //vratenie vsetkych cvikov v zozname
    @Query("SELECT * FROM 'workouts' ORDER BY id DESC")
    fun getAllWorkouts() : MutableList<Workout>

    //vyberanie jedneho prvku z tabulky
    @Query("SELECT * FROM 'workouts' WHERE id LIKE :id")
    fun getWorkout(id : Int) : Workout
}