package fr.epita.hello_game

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceInterface {
    @GET("game/list")
    fun listgame(): Call<List<GameList>>
}