package fr.leopold.projectesiea.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import fr.leopold.projectesiea.R
import fr.leopold.projectesiea.api.PokemonDetailResponse
import fr.leopold.projectesiea.presentation.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */


class PokemonDetailFragment: Fragment()
{
    private lateinit var textViewName: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigateToPokemonListFragment)

        }
         */
        textViewName = view.findViewById(R.id.pokemon_detail_name)
        callApi()
    }

    private fun callApi() {
        val id  = arguments?.getInt("pokemonId") ?: -1
        Singleton.pokeApi.getPokemonDetail(id).enqueue(object : Callback<PokemonDetailResponse> {
            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    textViewName.text = response.body()!!.name
                }
            }

        })
    }
}