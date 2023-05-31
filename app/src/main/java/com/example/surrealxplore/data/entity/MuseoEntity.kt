package com.example.surrealxplore.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "tabla_museos")
@TypeConverters(StringListConverter::class)
data class MuseoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")val id: Int = 0,
    @ColumnInfo("nombre")val nombre: String,
    @ColumnInfo("calle")val calle: String,
    @ColumnInfo("colonia")val colonia: String,
    @ColumnInfo("cp")val cp: String,
    @ColumnInfo("ciudad")val ciudad: String,
    @ColumnInfo("estado")val estado: String,
    @ColumnInfo("telefono")val telefono: String,
    @ColumnInfo("horario")val horario: String,
    @ColumnInfo("costos")val costos: String,
    @ColumnInfo("descripcion")val descripcion: String,
    @ColumnInfo("fecha_fundacion")val fecha_fundacion: String,
    @ColumnInfo("servicios")val servicios: String,
    @ColumnInfo("imagen")val imagen: List<Int>
)