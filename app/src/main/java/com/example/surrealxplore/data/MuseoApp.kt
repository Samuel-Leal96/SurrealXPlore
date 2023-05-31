package com.example.surrealxplore.data

import android.app.Application
import androidx.room.Room
import com.example.surrealxplore.data.entity.MuseoDataBase

class MuseoApp: Application() {

    lateinit var museoDao: MuseoDataBase

    override fun onCreate() {
        super.onCreate()
        museoDao = Room.databaseBuilder(
            applicationContext,
            MuseoDataBase::class.java, "museoDB"
        ).build()
    }
}