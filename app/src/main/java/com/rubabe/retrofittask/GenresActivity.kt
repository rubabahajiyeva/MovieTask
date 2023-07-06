package com.rubabe.retrofittask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rubabe.retrofittask.api.Api
import com.rubabe.retrofittask.databinding.ActivityGenresBinding
import com.rubabe.retrofittask.model.GenreResult
import com.rubabe.retrofittask.model.Movie
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenresActivity : AppCompatActivity() {
    lateinit var binding: ActivityGenresBinding
    lateinit var api: Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        api = Constants.getApi()
        val movie = intent.getSerializableExtra("Movie") as Movie
        Picasso.get().load(Constants.IMAGE_BASE_URL + movie.poster_path)
            .into(binding.genresMovieImage)
        getGenres()
    }

    fun getGenres(){
     api.genresOfMovies(Constants.API_KEY).enqueue(object: Callback<GenreResult>{
         override fun onResponse(call: Call<GenreResult>, response: Response<GenreResult>) {

         }

         override fun onFailure(call: Call<GenreResult>, t: Throwable) {
          Toast.makeText(this@GenresActivity, "An error has occurred", Toast.LENGTH_SHORT).show()
         }

     })
    }

}
