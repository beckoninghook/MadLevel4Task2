package com.example.madlevel4task2

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameHistoryFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val games = arrayListOf<Game>()
    private val gamesHistoryAdapter = GamesHistoryAdapter(games)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_history, container, false)

    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        getGameHistoryFromDatabase()

        initRv()


        view.findViewById<Button>(R.id.button_second).setOnClickListener {
          findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.delete).setOnClickListener {
            deleteAll()
        }


    }

    private fun deleteAll(){
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
            this@GameHistoryFragment.games.clear()
          
            this@GameHistoryFragment.gamesHistoryAdapter.notifyDataSetChanged()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        println("faka")

        menu.clear()

        inflater.inflate(R.menu.menu_history, menu)
        //super.onCreateOptionsMenu(menu, inflater)
    }





    private fun initRv() {

        // Initialize the recycler view with a linear layout manager, adapter
        rvGameHistory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGameHistory.adapter = gamesHistoryAdapter
        rvGameHistory.setHasFixedSize(true)
        rvGameHistory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    }

    private fun getGameHistoryFromDatabase() {
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@GameHistoryFragment.games.clear()
            this@GameHistoryFragment.games.addAll(shoppingList)
            this@GameHistoryFragment.gamesHistoryAdapter.notifyDataSetChanged()
        }
    }

}