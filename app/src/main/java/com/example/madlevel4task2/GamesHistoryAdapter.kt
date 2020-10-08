package com.example.madlevel4task2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.match.view.*
import kotlinx.android.synthetic.main.match.view.imgComputer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class GamesHistoryAdapter (private val games: List<Game>) : RecyclerView.Adapter<GamesHistoryAdapter.ViewHolder>() {


    private val mainScope = CoroutineScope(Dispatchers.Main)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SimpleDateFormat")
        fun databind(game: Game) {
            itemView.txtResult.text = game.finalScore
            itemView.txtDate.text = game.date
            if (game.computerResult == "PAPER"){
                itemView.imgComputer.setImageResource(R.drawable.paper)
            }
            if (game.computerResult == "ROCK"){
                itemView.imgComputer.setImageResource(R.drawable.rock)
            }

            if (game.computerResult == "SCISSOR"){
                itemView.imgComputer.setImageResource(R.drawable.scissors)
            }

            if (game.playerResult == "PAPER"){
                itemView.imagePlayer.setImageResource(R.drawable.paper)
            }
            if (game.playerResult == "ROCK"){
                itemView.imagePlayer.setImageResource(R.drawable.rock)
            }

            if (game.playerResult == "SCISSOR"){
                itemView.imagePlayer.setImageResource(R.drawable.scissors)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHistoryAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.match, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GamesHistoryAdapter.ViewHolder, position: Int) {
        holder.databind(games[position])
    }


}