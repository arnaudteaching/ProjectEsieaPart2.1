package fr.leopold.projectesiea.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("item")
    fun getPokemonList(@Query("limit") limit :String): Call<PokemonListResponse>

    @GET("item/{id}")
    fun getPokemonDetail(@Path("id") id: Int): Call<PokemonDetailResponse>
}