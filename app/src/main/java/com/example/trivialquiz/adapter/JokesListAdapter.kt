package com.example.trivialquiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trivialquiz.R
import com.example.trivialquiz.model.JokesModelItem
import kotlinx.android.synthetic.main.item_joke.view.*


class JokesListAdapter(
    private val items: ArrayList<JokesModelItem>,
    private val context: Context
) : RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_joke, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvJokesSetup.text = items[position].setup
        holder.tvPunchLine.text = items[position].punchline
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJokesSetup: TextView = view.setup
        val tvPunchLine: TextView = view.punchline
    }
}
