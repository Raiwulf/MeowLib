package com.example.meowlib.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_cats")
data class FavsModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    var favId: Int,
    @ColumnInfo(name ="favName")
    var favName: String,
    @ColumnInfo(name="favOrigin")
    var favOrigin: String,
    @ColumnInfo(name="favPic")
    var favPic: String,
    @ColumnInfo(name="favDesc")
    var favDesc: String,
    @ColumnInfo(name="favTempa")
    var favTempa: String
)