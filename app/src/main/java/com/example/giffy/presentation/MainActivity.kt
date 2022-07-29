package com.example.giffy.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.giffy.R
import com.example.giffy.models.domain.Gif
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSearch: Button
    private lateinit var resultImage: ImageView
    private lateinit var queryInput: EditText
    private lateinit var root: View
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObservers()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        buttonSearch = findViewById<Button>(R.id.button_search).apply {
            setOnClickListener {
                val query = queryInput.text.toString()
                viewModel.search(query)
            }
        }
        resultImage = findViewById(R.id.result_image)
        queryInput = findViewById(R.id.query_input)
        root = findViewById(R.id.root)
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.result.collectLatest {
                setItems(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.alert.collectLatest {
                showAlert(it)
            }
        }
    }

    private fun showAlert(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setItems(gifs: List<Gif>) {
        gifs.firstOrNull()?.url?.let {
            Glide.with(this).load(it.toString()).into(resultImage)

        }
    }
}