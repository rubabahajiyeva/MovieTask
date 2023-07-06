package com.rubabe.retrofittask.api


import com.rubabe.retrofittask.model.GenreResult
import com.rubabe.retrofittask.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.rubabe.retrofittask.model.Result

interface Api {

    @GET("search/movie")
    fun searchMovies(@Query("api_key") api_key: String, @Query("query") query: String): Call<Result>

    @GET("movie/upcoming")
    fun upcomingMovies(@Query("api_key") api_key: String): Call<Result>

    @GET("movie/popular")
    fun popularMovies(@Query("api_key") api_key: String): Call<Result>

    @GET("genre/movie/list")
    fun genresOfMovies(@Query("api_key") api_key: String): Call<GenreResult>
}