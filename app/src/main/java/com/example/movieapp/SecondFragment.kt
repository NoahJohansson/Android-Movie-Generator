package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.FragmentSecondBinding
/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    //Still don't get exactly what binding does
    private val binding get() = _binding!!

    //private lateinit var comm: Communicator

    //get viewModel from MyObservable in order to store live data and send data between fragments
    private lateinit var viewModel : MyObservable

    //This was all pre-written, no clue what the View thing or inflater does
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Check when button for add new movie is pressed, then send to third fragment
        binding.addNewMovieButton.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_To_ThirdFragment)
        }
//Check when button for remove movie is pressed, then send to third fragment
        binding.removeMovieButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_RemoveMovieFragment)
        }
        //comm = requireActivity() as Communicator

        binding.generateButton.setOnClickListener {
            //get movie from database. Don't really know what context is
            val db = MyDatabaseHelper(context)
            val rows = db.numberOfRows
            val random = (0..rows).random()
            val randomMovie = db.getRandomMovie()
            //Logging some stuff
            Log.i("randomMovie Log", randomMovie)
            //comm.passDataCom(randomMovie)

            //Part of the data between fragments stuff, no clue what activity? means
            viewModel = activity?.run {
                ViewModelProviders.of(this).get(MyObservable::class.java)
            } ?: throw Exception("Invalid Activity")
            //Set viewModel.data to the random movie and it can be observed in GeneratedMovieFragment
            viewModel.data (randomMovie)

            findNavController().navigate(R.id.action_SecondFragment_to_GeneratedMovieFragment)
            //Toast.makeText(context, randomMovie +  " is your movie to watch", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}