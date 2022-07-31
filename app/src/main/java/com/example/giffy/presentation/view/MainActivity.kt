package com.example.giffy.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giffy.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SearchFragment.newInstance())
                .commit()
        }
    }
}