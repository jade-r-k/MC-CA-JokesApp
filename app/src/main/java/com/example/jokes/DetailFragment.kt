package com.example.jokes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jokes.databinding.DetailFragmentBinding

// Detail Fragment is used to display joke in better detail
class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.jokeText.setText("")

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        viewModel.currentJoke.observe(viewLifecycleOwner, Observer {
            binding.jokeText.setText("Category: "+it.category + "\n\n" + it.joke)
        })
        viewModel.getJokeById(args.jokeId)


        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    //return to previous fragment
    private fun saveAndReturn(): Boolean {
        findNavController().navigateUp()

        return true
    }

}