package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "game_table")
data class Game(
    @ColumnInfo(name = "computerResult")
    var computerResult : Result,
    @ColumnInfo(name = "playerResult")
    var playerResult : Result,
    @ColumnInfo(name = "date")
    var date : String,
    @ColumnInfo(name = "finalScore")
    var finalScore : FinalScore,



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) {
}