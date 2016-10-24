package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import be.florien.poketeam.BR
import be.florien.poketeam.databinding.ItemSpeciesBinding
import be.florien.poketeam.model.PokemonSpecie

class SpeciesItemVM(speciesBinding: ItemSpeciesBinding) : BaseObservable() {
    private var species: PokemonSpecie? = null
    init {
        speciesBinding.setVariable(BR.species, this)
    }

    fun setSpecies(species : PokemonSpecie) {
        this.species = species
        notifyPropertyChanged(BR.speciesName)
    }

    @Bindable
    fun getSpeciesName() : String {
        return species?.pokemon_species_names?.first ?: species?.identifier ?: "Unknown name"
    }
}