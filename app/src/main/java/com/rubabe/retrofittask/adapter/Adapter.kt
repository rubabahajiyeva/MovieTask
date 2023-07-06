package com.rubabe.retrofittask.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubabe.retrofittask.Constants
import com.rubabe.retrofittask.GenresActivity
import com.rubabe.retrofittask.R
import com.rubabe.retrofittask.model.Movie
import com.squareup.picasso.Picasso

class Adapter(val mContext: Context, var list: List<Movie>):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(design: View) : RecyclerView.ViewHolder(design) {

        var imageView: ImageView
        var titleView: TextView


        init {
           imageView = design.findViewById(R.id.movieImage)
           titleView = design.findViewById(R.id.movieTitle)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(design)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]

        holder.titleView.text = movie.title

        Picasso.get().load(Constants.IMAGE_BASE_URL+movie.poster_path).into(holder.imageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(mContext, GenresActivity::class.java)
            intent.putExtra("Movie",movie)
            mContext.startActivity(intent)

        }

    }
}
