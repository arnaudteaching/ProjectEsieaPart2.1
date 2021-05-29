package fr.leopold.projectesiea.presentation

import androidx.core.content.contentValuesOf
import fr.leopold.projectesiea.api.PokeApi
import fr.leopold.projectesiea.presentation.PokeApplication.Companion.context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singleton {
    companion object {
        var cache = Cache(File(context?.cacheDir, "responses"), 10*1024)
        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()
        val pokeApi: PokeApi = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokeApi::class.java)



    }
}