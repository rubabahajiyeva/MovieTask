package com.rubabe.retrofittask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.rubabe.retrofittask.api.Api
import com.rubabe.retrofittask.databinding.ActivityMainBinding
import retrofit2.Callback
import com.rubabe.retrofittask.model.Result
import retrofit2.Call
import retrofit2.Response
import com.rubabe.retrofittask.adapter.Adapter


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var api: Api
    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        api = Constants.getApi()
        onClickListener()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL,false)
        binding.recyclerView.setOnClickListener {
            startActivity(Intent(this, GenresActivity::class.java))
        }
    }

    fun onClickListener() {
        binding.popularButton.setOnClickListener {
            getPopularMovies()
        }
        binding.upcomingButton.setOnClickListener {
            getUpcomingMovies()
        }
        binding.searchButton.setOnClickListener {
            getSearchMovies()
        }
    }

    fun getSearchMovies() {
        api.searchMovies(Constants.API_KEY, binding.searchField.text.toString()).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val list = response.body()?.results
                adapter = list?.let { Adapter(this@MainActivity, it) }!!
                binding.recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@MainActivity, "An error has occurred", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    fun getPopularMovies() {
        api.popularMovies(Constants.API_KEY).enqueue(
            object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    val list = response.body()?.results
                    adapter = list?.let { Adapter(this@MainActivity, it) }!!
                    binding.recyclerView.adapter = adapter
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "An error has occurred", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    fun getUpcomingMovies() {
        api.upcomingMovies(Constants.API_KEY).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val newsList: Result = response.body() as Result
                binding.recyclerView.adapter = Adapter(this@MainActivity, newsList.results)
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@MainActivity, "An error has occurred", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

}