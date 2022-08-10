package com.tubetoast.giffy.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.tubetoast.giffy.R
import com.tubetoast.giffy.presentation.fragments.content.ContentFragment
import com.tubetoast.giffy.presentation.fragments.searchdetails.SearchDetailsFragment
import com.tubetoast.giffy.presentation.view.SearchView

class MainActivity : AppCompatActivity(), SearchView.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ContentFragment.newInstance(), ContentFragment.TAG)
                .commit()
        }
        findViewById<SearchView>(R.id.search_view).setListener(this)
    }

    override fun onActiveStateChangeListener(active: Boolean) {
        if(active) showSearchDetails() else hideSearchDetails()
    }

    private fun showSearchDetails() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SearchDetailsFragment.newInstance())
            .addToBackStack(SearchDetailsFragment.TAG)
            .setReorderingAllowed(true)
            .commit()
    }

    private fun hideSearchDetails() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ContentFragment.newInstance())
            .setReorderingAllowed(true)
            .commit()
    }

}