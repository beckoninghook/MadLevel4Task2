package com.example.madlevel4task2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val results = arrayListOf("ROCK" , "PAPER" , "SCISSORS")
    private val mainScope = CoroutineScope(Dispatchers.Main)
    var drawAmount = "";
    var lostAmount = "";
    var winAmount = "";


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
            getStats(view)
        }

        view.findViewById<ImageButton>(R.id.btnPaper).setOnClickListener {
            play(results[1] , view)

            img.setImageResource(R.drawable.paper)
            getStats(view)
        }

        view.findViewById<ImageButton>(R.id.btnScissors).setOnClickListener {
            play(results[2] , view)
            img.setImageResource(R.drawable.scissors)
            getStats(view)
        }



    }

    @SuppressLint("SetTextI18n")
    fun getStats(view : View) {
        mainScope.launch {
            val wAmount = withContext(Dispatchers.IO) {
                gameRepository.getWinCount()
            }

            val lAmount = withContext(Dispatchers.IO) {
                gameRepository.getLostcount()
            }

            val dAmount = withContext(Dispatchers.IO) {
                gameRepository.getDrawCount()
            }

            var stats = view.findViewById<TextView>(R.id.statsTitleTxt2)
            stats.text = " wins: $wAmount lost: $lAmount Draw $dAmount"
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
        val game = Game(result  ,computerResult , Date().toString() ,  score)
        addGame(game)

    }

    override fun onCreateOptionsMenu(menu: Menu , menuInflate : MenuInflater) {
        println(menu)
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflate.inflate(R.menu.menu_main, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println(item)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        return true
    }

    private fun addGame(game: Game) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game)
                }
            }
        }

}