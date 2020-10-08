package com.example.madlevel4task2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface GameDao {
    @Query("SELECT * FROM game_table")
    suspend fun getAllProducts(): List<Game>

    @Insert
    suspend fun insertProduct(game: Game)

    @Delete
    suspend fun deleteProduct(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllProducts()

    @Query("SELECT COUNT(finalScore) FROM game_table WHERE finalScore = 'won' ")
    suspend fun getWinCount() : String

    @Query("SELECT COUNT(finalScore) FROM game_table WHERE finalScore = 'lost' ")
    suspend fun getLostCount() : String

    @Query("SELECT COUNT(finalScore) FROM game_table WHERE finalScore = 'draw' ")
    suspend fun getDrawCount(): String
}