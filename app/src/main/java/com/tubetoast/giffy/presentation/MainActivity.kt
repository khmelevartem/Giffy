package com.tubetoast.giffy.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.tubetoast.giffy.R
import com.tubetoast.giffy.domain.SearchInteractor
import com.tubetoast.giffy.models.domain.SearchState
import com.tubetoast.giffy.presentation.fragments.content.ContentFragment
import com.tubetoast.giffy.presentation.fragments.searchdetails.SearchDetailsFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val searchInteractor: SearchInteractor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ContentFragment.newInstance(), ContentFragment.TAG)
                .commit()
        }
        lifecycleScope.launchWhenStarted {
            searchInteractor.searchState.collectLatest {
                when (it) {
                    is SearchState.Forming -> showSearchDetails()
                    is SearchState.Loading -> hideSearchDetails()
                    else -> Unit
                }
            }
        }
    }

    private fun showSearchDetails() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SearchDetailsFragment.newInstance())
            .addToBackStack(ContentFragment.TAG)
            .setReorderingAllowed(true)
            .commit()
    }

    private fun hideSearchDetails() {
        supportFragmentManager.popBackStack()
    }

}