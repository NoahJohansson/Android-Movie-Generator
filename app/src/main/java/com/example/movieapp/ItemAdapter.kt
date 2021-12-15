package com.example.movieapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import com.example.movieapp.databinding.FragmentRemoveMovieBinding

class ItemAdapter(val context: Context):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var movieList: ArrayList<MovieModelClass> = ArrayList()

    fun addMovies(items: ArrayList<MovieModelClass>) {
        this.movieList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bindView(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        private var name = view.findViewById<TextView>(R.id.textViewTitle)

        fun bindView(movie: MovieModelClass) {
            name.text = movie.movie_title
        }
    }
}