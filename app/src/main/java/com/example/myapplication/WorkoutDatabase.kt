package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezatpanah.roomdatabase_youtube.db.Workout

//abstraaktna triedy zodpovedajuca Room databaze
@Database(entities = [Workout::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase(){
    abstract fun dao(): WorkoutDao
}