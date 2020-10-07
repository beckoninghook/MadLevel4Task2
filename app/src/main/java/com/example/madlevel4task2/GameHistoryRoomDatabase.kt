package com.example.madlevel4task2


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Game::class], version = 1, exportSchema = false)

abstract class GameHistoryRoomDatabase : RoomDatabase() {

    abstract fun productDao(): GameDao


    companion object {
        private const val DATABASE_NAME = "SHOPPING_LIST_DATABASE"

        @Volatile
        private var gameHistoryRoomDatabaseInstance: GameHistoryRoomDatabase? = null

        fun getDatabase(context: Context): GameHistoryRoomDatabase? {
            if (gameHistoryRoomDatabaseInstance == null) {
                synchronized(GameHistoryRoomDatabase::class.java) {
                    if (gameHistoryRoomDatabaseInstance == null) {
                        gameHistoryRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,GameHistoryRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return gameHistoryRoomDatabaseInstance
        }
    }

}
