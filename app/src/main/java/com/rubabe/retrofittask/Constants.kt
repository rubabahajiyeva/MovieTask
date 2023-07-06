package com.rubabe.retrofittask

import com.rubabe.retrofittask.api.Api
import com.rubabe.retrofittask.network.RetrofitClient

class Constants {
    companion object{
        var BASE_URL = "https://api.themoviedb.org/3/"
        var API_KEY = "4fae678e0bf0ca0ce26f68efa69e3328"
        var IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

        fun getApi(): Api {
            return RetrofitClient.getClient(BASE_URL).create(Api::class.java)
        }
    }
}