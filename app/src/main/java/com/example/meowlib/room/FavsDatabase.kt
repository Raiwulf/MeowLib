package com.example.meowlib.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavsModel::class], version = 1, exportSchema = false)
abstract class FavsDatabase : RoomDatabase() {

    abstract val favsDao : FavsDao

    companion object{

        @Volatile
        private var INSTANCE: FavsDatabase? = null

        fun getFavsDatabase(context: Context) : FavsDatabase? {
            synchronized(this){
                var instance = INSTANCE

                if(instance==null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavsDatabase::class.java,
                        "fav_cats"
                    )
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                   return INSTANCE
            }
        }
    }
}