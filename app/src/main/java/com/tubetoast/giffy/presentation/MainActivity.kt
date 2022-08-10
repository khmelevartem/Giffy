package com.tubetoast.giffy.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tubetoast.giffy.R
import com.tubetoast.giffy.presentation.fragments.content.ContentFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ContentFragment.newInstance())
                .commit()
        }
    }
}