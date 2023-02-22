package com.company.albertsonstest_kushallingarkar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.company.albertsonstest_kushallingarkar.ui.adapter.AcronymAdapter
import com.company.albertsonstest_kushallingarkar.viewmodel.AcronymViewModel
import com.company.albertsonstest_kushallingarkar.R
import com.company.albertsonstest_kushallingarkar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AcronymViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflating the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Adding click listener to the search button
        binding.searchButton.setOnClickListener {
            val acronym = binding.acronymEditText.text.toString()

            if (acronym.isNotEmpty()) {
                viewModel.searchAcronym(acronym)
            } else {
                binding.messageText.text = "No Input Entered"
                binding.acronymListView.adapter = AcronymAdapter(emptyList())

            }

        }
        // Observing the changes in the acronymMeanings list of the viewModel and updating the list view accordingly
        viewModel.acronymMeanings.observe(this, { acronymMeanings ->
            if (acronymMeanings != null && acronymMeanings.size > 0) {
            binding.acronymListView.adapter = AcronymAdapter(acronymMeanings) }
            else{
                binding.acronymListView.adapter = AcronymAdapter(emptyList())
                binding.messageText.text = "No Meanings Fpund"
            }
        })

        // Observing the error message of the viewModel and displaying it in a toast message
        viewModel.error.observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }
}