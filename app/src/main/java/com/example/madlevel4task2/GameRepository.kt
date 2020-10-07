package com.example.madlevel4task2

import android.content.Context

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameHistoryRoomDatabase.getDatabase(context)
        gameDao = database!!.productDao()
    }

    suspend fun getAllProducts(): List<Game> {
        return gameDao.getAllProducts()
    }

    suspend fun insertProduct(game: Game) {
        gameDao.insertProduct(game)
    }

    suspend fun deleteProduct(game: Game) {
        gameDao.deleteProduct(game)
    }

    suspend fun deleteAllProducts() {
        gameDao.deleteAllProducts()
    }
}