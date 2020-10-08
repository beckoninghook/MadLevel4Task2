package com.example.madlevel4task2

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        activity?.title = "Your Game History"

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println(item.toString())
        if (item.toString() == "delete"){
            deleteAll()
        }
        if (item.toString().contains("androidx.appcompat.view.menu.ActionMenuItem")){
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        return true;
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