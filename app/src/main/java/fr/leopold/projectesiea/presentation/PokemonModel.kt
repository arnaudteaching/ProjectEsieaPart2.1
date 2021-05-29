package fr.leopold.projectesiea.presentation

sealed class PokemonModel
data class PokemonSuccess(
    val pokeList:List<Pokemon> ): PokemonModel()
object PokemonLoader : PokemonModel()
object PokemonError : PokemonModel()
