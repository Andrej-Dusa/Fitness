package com.ezatpanah.roomdatabase_youtube.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//datova trieda predstavujuca entitu cviiku z tabulky v databaze
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "time")
    val time: Int,
    @ColumnInfo(name = "calories")
    val calories: Int,
    @ColumnInfo(name = "img")
    val img: Int
)
