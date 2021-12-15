package com.example.movieapp

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MoviesAdapter(val context: Context, val movies : ArrayList<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var db: MyDatabaseHelper = MyDatabaseHelper(context)
    var test: RemoveMovieFragment = RemoveMovieFragment()

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.view.findViewById<TextView>(R.id.textViewTitle).text = movie.title

        holder.view.findViewById<ImageButton>(R.id.imageButtonDelete).setOnClickListener(View.OnClickListener { //deleting note
            Log.i(" did the "," did the code go here check" )
            //db.removeMovie(movie.id)
            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
            builder.setTitle("Delete Record")
            builder.setMessage("Are you sure you want to remove        ${movie.title} from your list?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            val databaseHandler: MyDatabaseHelper = MyDatabaseHelper(context)

            builder.setPositiveButton("Yes") { dialogInterface, which ->
                movies.removeAt(holder.adapterPosition)
                databaseHandler.removeMovie(movie.id)
                notifyItemRemoved(holder.adapterPosition)
                Toast.makeText(
                    context, "${movie.title} was successfully removed.",
                    Toast.LENGTH_LONG
                ).show()

                dialogInterface.dismiss() // Dialog will be dismissed
            }
            builder.setNegativeButton("No") { dialogInterface, which ->
                dialogInterface.dismiss() // Dialog will be dismissed
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.show()
        })
    }

    override fun getItemCount() = movies.size

}