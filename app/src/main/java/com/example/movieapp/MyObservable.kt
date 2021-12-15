package com.example.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.movieapp.databinding.ActivityMainBinding


open class MyObservable : ViewModel() {
    val data = MutableLiveData<String>()

    fun data(item:String) {
        data.value = item
    }
}