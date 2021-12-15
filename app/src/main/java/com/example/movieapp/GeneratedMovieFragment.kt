package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.FragmentGeneratedMovieBinding
import com.example.movieapp.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GeneratedMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GeneratedMovieFragment : Fragment() {

    private var _binding: FragmentGeneratedMovieBinding? = null
    private val binding get() = _binding!!

    //Get the viewModel thing from MyObservable
    private lateinit var viewModel : MyObservable

    //private var randomMovie: String? = ""
    //private var generatedmoviefragment: FragmentGeneratedMovieBinding? = null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGeneratedMovieBinding.inflate(inflater, container, false)

        //This was from first attempt at making data transfer between fragments
        //Problem was randomMovie first got proper value, then it ran again and got null

        //generatedmoviefragment = binding
        //randomMovie = arguments?.getString("input_txt")

        /*binding.button15.text = randomMovie
        Log.i("randomMovieloggy", binding.button15.text.toString())
        binding.button15.text = "hello"
        binding.generatedMovie.text = randomMovie */

        //Toast.makeText(context, randomMovie +  " is your movie to watch", Toast.LENGTH_SHORT).show()
        //generatedmoviefragment!!.generatedMovie.text = randomMovie

        return binding.root
    }

    //Still do not know when to use onCreateView or onViewCreated
    //viewModel activity thing no clue
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MyObservable::class.java)
        } ?: throw Exception("Invalid Activity")
        //Here i get the data from viewModel that was set on button press in SecondFragment
        viewModel.data.observe(viewLifecycleOwner, Observer {
            //Change the text in fragment to display movie
            binding.generatedMovie.text = viewModel.data.value
        })
        //Make a little popup text
        Toast.makeText(context, viewModel.data.value +  " is your movie to watch", Toast.LENGTH_SHORT).show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GeneratedMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GeneratedMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}