package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "game_table")
data class Game(
    @TypeConverters
    @ColumnInfo(name = "computerResult")
    public var computerResult : String,

    @TypeConverters
    @ColumnInfo(name = "playerResult")
    public var playerResult : String,

    @ColumnInfo(name = "date")
    var date : String,

    @TypeConverters
    @ColumnInfo(name = "finalScore")
    public var finalScore : String,



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) {



}