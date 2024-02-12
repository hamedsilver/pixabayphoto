package com.example.pixabayphoto.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pixabayphoto.data.local.dao.SearchSuggestionDao
import com.example.pixabayphoto.data.local.model.SearchSuggestionEntity

@Database(entities = [SearchSuggestionEntity::class], version = 1, exportSchema = false)
abstract class PixabayrDataBase : RoomDatabase() {

    // DAO
    abstract fun searchDao(): SearchSuggestionDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PixabayrDataBase::class.java,
                "Pixabay.db"
            )
                .build()
    }
}
