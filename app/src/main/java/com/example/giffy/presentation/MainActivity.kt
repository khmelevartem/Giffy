package com.example.giffy.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.giffy.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSearch: Button
    private lateinit var resultImage: ImageView
    private lateinit var queryInput: EditText
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
    }

    private fun initObservers() {
        viewModel.result.observe(this) {
            TODO()
        }
    }
}