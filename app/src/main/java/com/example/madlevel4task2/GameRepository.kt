package com.example.madlevel4task2

import android.content.Context

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameHistoryRoomDatabase.getDatabase(context)
        gameDao = database!!.productDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllProducts()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertProduct(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteProduct(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllProducts()
    }

    suspend fun getWinCount() : String{
        return gameDao.getWinCount()
    }

    suspend fun getLostcount() : String{
        return gameDao.getLostCount()
    }

    suspend fun getDrawCount(): String {
        return gameDao.getDrawCount()
    }
}