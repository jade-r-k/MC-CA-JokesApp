package com.example.jokes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokes.databinding.MainFragmentBinding
import com.example.jokes.models.Joke

class MainFragment : Fragment(),
JokesListAdapter.ListItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: JokesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }

        viewModel.jokesList?.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, it.toString())
            adapter = JokesListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuId =
            if (this::adapter.isInitialized &&
                    adapter.selectedJokes.isNotEmpty()) {
                R.menu.menu_main_selected_items
            } else {
                R.menu.menu_main
            }

        inflater.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_random_jokes -> addRandomJoke()
            R.id.action_delete -> deleteSelectedJokes()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteSelectedJokes(): Boolean {
        viewModel.deleteJokes(adapter.selectedJokes)
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedJokes.clear()
            requireActivity().invalidateOptionsMenu()
        },100)
        return true

    }

    private fun addRandomJoke(): Boolean {
        viewModel.randomJokes()
        return true
    }

    private fun getJokes(): Boolean {
        viewModel.getJokes()
        return true
    }

    override fun onItemClick(jokeId: Int) {
        Log.i(TAG, "onItemClick: received joke id $jokeId")
        val action = MainFragmentDirections.actionViewJoke(jokeId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu()
    }

}