package fr.leopold.projectesiea.api

//Import presentation list pokemon
import fr.leopold.projectesiea.presentation.Pokemon

data class PokemonListResponse (
    val count : String,
    val next : String,
    val results : List<Pokemon>

)