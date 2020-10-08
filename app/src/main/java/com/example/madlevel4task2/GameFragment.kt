package com.example.madlevel4task2

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val results = arrayListOf("ROCK" , "PAPER" , "SCISSORS")
    private val mainScope = CoroutineScope(Dispatchers.Main)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var img = view.findViewById<ImageView>(R.id.imgPlayerStart)
        gameRepository = GameRepository(requireContext())


        view.findViewById<ImageButton>(R.id.btnHistory).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.findViewById<ImageButton>(R.id.btnRock).setOnClickListener {
            play(results[0], view)

            img.setImageResource(R.drawable.rock)
        }

        view.findViewById<ImageButton>(R.id.btnPaper).setOnClickListener {
            play(results[1] , view)

            img.setImageResource(R.drawable.paper)
        }

        view.findViewById<ImageButton>(R.id.btnScissors).setOnClickListener {
            play(results[2] , view)
            img.setImageResource(R.drawable.scissors)

        }

    }


    private fun play(result : String , view : View){

        val computerResult = results[(0 until 3).random()]
        var score ="";
        var compImg = view.findViewById<ImageView>(R.id.imgComputer)


        if (computerResult == results[0]){
            compImg.setImageResource(R.drawable.rock)
        }
        if (computerResult == results[1]){
            compImg.setImageResource(R.drawable.paper)
        }
        if (computerResult == results[2]){
            compImg.setImageResource(R.drawable.scissors)
        }

        if (result == computerResult){
            score = "draw"
        }

        //als het steen is
        if (result == results[0]  ){
            if (computerResult == results[1]){
                score = "lost"
            }
            if (computerResult == results[2]){
                score = "won"
            }
        }

        //als het papier is
        if (result == results[1]  ){
            if (computerResult == results[2]){
                score = "lost"
            }
            if (computerResult == results[0]){
                score = "won"
            }
        }

        //als het schaar is
        if (result == results[2]  ){
            if (computerResult == results[0]){
                score = "lost"
            }
            if (computerResult == results[1]){
                score = "won"
            }
        }

        view.findViewById<TextView>(R.id.resultTxt).text = score;
        val game = Game(result  ,computerResult , SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()).toString() ,  score)
        addGame(game)

    }

    private fun addGame(game: Game) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game)
                }
            }
        }

}