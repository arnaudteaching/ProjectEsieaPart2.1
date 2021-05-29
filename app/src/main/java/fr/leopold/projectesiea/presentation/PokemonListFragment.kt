package fr.leopold.projectesiea.presentation

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.leopold.projectesiea.MainActivity
import fr.leopold.projectesiea.R
import fr.leopold.projectesiea.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Observer

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment(private val context: MainActivity) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)
    private val viewModel: PokemonListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recycler_view)
        loader = view.findViewById(R.id.pokemon_loader)
        textViewError = view.findViewById(R.id.pokemon_error)
        recyclerView.apply {
            adapter = this@PokemonListFragment.adapter
            layoutManager = LinearLayoutManager(context)

        }
        viewModel.pokeList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { pokemonModel ->
            loader.isVisible = pokemonModel is PokemonLoader
            textViewError.isVisible = pokemonModel is PokemonError

            if (pokemonModel is PokemonSuccess) {
                adapter.updateList(pokemonModel.pokeList)
            }


        })


    }


    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf("pokemonId" to (id + 1)
        ))
    }
}




