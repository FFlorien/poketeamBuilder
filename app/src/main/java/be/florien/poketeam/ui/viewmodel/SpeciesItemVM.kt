package be.florien.poketeam.ui.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import be.florien.poketeam.R
import be.florien.poketeam.model.PokemonSpecie

/**
 * Created by florien on 23/10/16.
 */


class SpeciesItemVM(itemView: View,
                    var pokemonItemVMs: List<PokemonItemVM>) : RecyclerView.ViewHolder(itemView) {
    var specieName : TextView
    init {
        specieName = itemView.findViewById(R.id.speciesName) as TextView
    }

    fun setSpecies(species : PokemonSpecie) {
        specieName.text = species.pokemon_species_names.first
        for ((index, value) in species.pokemon.withIndex()) {
            pokemonItemVMs[index].setPokemon(value)
        }
    }
}