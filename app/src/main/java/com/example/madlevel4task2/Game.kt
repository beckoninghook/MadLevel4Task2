package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "game_table")
data class Game(

    @ColumnInfo(name = "computerResult")
    public var computerResult : String,

    @ColumnInfo(name = "playerResult")
    public var playerResult : String,

    @ColumnInfo(name = "date")
    var date : String,


    @ColumnInfo(name = "finalScore")
    public var finalScore : String,



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) {



}