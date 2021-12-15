package com.example.movieapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_remove_movie.*

class RemoveMovieFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_remove_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies: ArrayList<Movie> = fetchMovies(this.requireContext())
        linearLayoutManager = LinearLayoutManager(this.context)
        recyclerviewRemoveMovieId.layoutManager = linearLayoutManager
        adapter = MoviesAdapter(this.requireContext(), movies)
        recyclerviewRemoveMovieId.adapter = adapter
    }

    private fun fetchMovies(context: Context): ArrayList<Movie> {
        val db: MyDatabaseHelper = MyDatabaseHelper(context)
        val movieList: ArrayList<Movie> = db.getAllMovies()

        return movieList
    }

    fun deleteMovie(movie:Movie, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Record")
        builder.setMessage("Are you sure you want to delete ${movie.title}?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        val databaseHandler: MyDatabaseHelper = MyDatabaseHelper(context)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            databaseHandler.removeMovie(movie.id)
            Toast.makeText(
                context, "Record deleted successfully.",
                Toast.LENGTH_LONG
            ).show()

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun showMovies(movies: ArrayList<Movie>) {

    }

    fun deleteMovieFromDb(movie:Movie, context: Context) {
        val databaseHandler: MyDatabaseHelper = MyDatabaseHelper(context)
        databaseHandler.removeMovie(movie.id)
    }


        /*private var _binding: FragmentRemoveMovieBinding? = null
        private val binding get() = _binding!!

        private lateinit var recyclerView: RecyclerView
        private var adapter: ItemAdapter? = null

        */
    /* val db = MyDatabaseHelper(context)
    val movie_id: ArrayList<String> = ArrayList()
    val movie_title: ArrayList<String> = ArrayList()

    */
/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRemoveMovieBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        /*val db = MyDatabaseHelper(context)
        var array_list = ArrayList<String>()
        array_list = db.getAllMovies()
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, array_list)
        binding.listviewRemoveMovieId.setAdapter(arrayAdapter)
        */
        //setupListofDataIntoRecyclerView()
        /* storeMoviesInArray()

        val customAdapter = CustomAdapter(this.activity, context, movie_id, movie_title)
        binding.recyclerviewRemoveMovieId.setAdapter(customAdapter)
        binding.recyclerviewRemoveMovieId.setLayoutManager(LinearLayoutManager(this.activity))

        */


    }
    /*
    private fun storeMoviesInArray() {
        val cursor = db.getAllMovies()
        if(cursor.count == 0) {
            Toast.makeText(context,"There are no movies in your list", Toast.LENGTH_SHORT).show()
        }
        else {
            for (i in 0..cursor.count) {
                movie_id.add(cursor.getString(0))
                movie_title.add(cursor.getString(1))
            }
        } */

    private fun getAllMovies(): ArrayList<MovieModelClass> {
        val db: MyDatabaseHelper = MyDatabaseHelper(this.context)
        val movieList: ArrayList<MovieModelClass> = db.getAllMovies()

        return movieList
    }

    private fun setupListofDataIntoRecyclerView() {
        if (getAllMovies().size > 0) {
            binding.recyclerviewRemoveMovieId.layoutManager = LinearLayoutManager(this.context)
            val itemAdapter = ItemAdapter(this.requireContext())
        }
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerviewRemoveMovieId
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = ItemAdapter(this.requireContext())
        recyclerView.adapter = adapter

    }

    private fun getMovies() {
        val db: MyDatabaseHelper = MyDatabaseHelper(this.context)
        val list = db.allMovies

        adapter?.addMovies(list)
    }
*/
}