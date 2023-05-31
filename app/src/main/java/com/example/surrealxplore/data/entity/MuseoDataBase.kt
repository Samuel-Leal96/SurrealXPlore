package com.example.surrealxplore.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MuseoEntity::class],
    version = 2
 )
abstract class MuseoDataBase: RoomDatabase() {
    abstract fun MuseoDao(): MuseoDao
}