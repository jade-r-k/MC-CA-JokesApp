package com.example.jokes

//Manages data at run time

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokes.data.JokeEntity
import com.example.jokes.databinding.ListItemBinding

class JokesListAdapter(private val jokesList: List<JokeEntity>,
                       private val listener: ListItemListener) :

    RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    val selectedJokes = arrayListOf<JokeEntity>()

    //access to views
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    //called when a new list item is generated
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    //called each time data is passed
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = jokesList[position]
        with(holder.binding) {
            jokeText.text = joke.joke
            root.setOnClickListener{
                listener.onItemClick(joke.id)
            }
            //When joke is clicked
            fab.setOnClickListener{
                if (selectedJokes.contains(joke)) {
                    selectedJokes.remove(joke)
                    fab.setImageResource(R.drawable.ic_jokes)
                }else {
                    selectedJokes.add(joke)
                    fab.setImageResource(R.drawable.ic_check)
                }
                listener.onItemSelectionChanged()
            }
            //When joke is selected
            fab.setImageResource(
                if (selectedJokes.contains(joke)) {
                    R.drawable.ic_check
                } else {
                    R.drawable.ic_jokes
                }
            )
        }
    }

    //Listens for when joke is clicked
    interface ListItemListener {
        fun onItemClick (jokeId: Int)
        fun onItemSelectionChanged()
    }

    override fun getItemCount() = jokesList.size

}