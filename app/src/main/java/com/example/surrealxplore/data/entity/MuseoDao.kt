package com.example.surrealxplore.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MuseoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMuseos(museo: List<MuseoEntity>)

    @Query( "SELECT * FROM tabla_museos ORDER BY ciudad" )
    suspend fun obtenerMuseos(): List<MuseoEntity>

    @Query( "SELECT * FROM tabla_museos WHERE ciudad = :ciudad" )
    suspend fun obtenerMuseosPorCiudad(ciudad: String): List<MuseoEntity>

    @Query( "SELECT * FROM tabla_museos WHERE nombre = :nombre")
    suspend fun obtenerMuseosPorNombre(nombre: String): List<MuseoEntity>

}