package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import be.florien.poketeam.BR
import be.florien.poketeam.databinding.ItemSpeciesBinding
import be.florien.poketeam.model.PokemonSpecie

/**
 * Created by FlamentF on 24-10-16.
 */
class SpeciesItemVM(val speciesBinding: ItemSpeciesBinding,
                    var pokemonItemVMs: List<PokemonItemVM>) : BaseObservable() {
    private var species: PokemonSpecie? = null
    init {
        speciesBinding.species = this
    }

    fun setSpecies(species : PokemonSpecie) {
        this.species = species
        speciesBinding.setVariable(BR.species, this)
        speciesBinding.executePendingBindings()
        for ((index, value) in species.pokemon.withIndex()) {
            pokemonItemVMs[index].setPokemon(value)
        }
        notifyPropertyChanged(BR.species)
    }

    fun getSpeciesName() : String {
        return species?.pokemon_species_names?.first ?: "unknown name"
    }
}