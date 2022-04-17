package com.example.meowlib.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavsDao {

    @Insert
    fun addFav(favs: FavsModel)

    @Query("DELETE FROM fav_cats WHERE favName= :favs")
    fun delFav(favs: String)

    @Query("SELECT * FROM fav_cats")
    fun listFav(): List<FavsModel>
}