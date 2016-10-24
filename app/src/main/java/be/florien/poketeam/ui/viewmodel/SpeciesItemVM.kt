package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.widget.ImageView
import be.florien.poketeam.BR
import be.florien.poketeam.databinding.ItemSpeciesBinding
import be.florien.poketeam.model.PokemonSpecie
import com.squareup.picasso.Picasso

class SpeciesItemVM(var speciesBinding: ItemSpeciesBinding) : BaseObservable() {
    private var species: PokemonSpecie? = null

    init {
        speciesBinding.setVariable(BR.species, this)
    }

    fun setSpecies(species: PokemonSpecie) {
        this.species = species
        notifyPropertyChanged(BR.speciesName)
        notifyPropertyChanged(BR.imageUrl)
        Picasso.with(speciesBinding.pokemonContainer.context)
                .load(getImageUrl())
                .fit()
                .into(speciesBinding.pokemonImage)
    }

    @Bindable
    fun getSpeciesName(): String {
        return species?.pokemon_species_names?.first ?: species?.identifier ?: "Unknown name"
    }

    @Bindable
    fun getImageUrl(): String {
        return "http://www.pokestadium.com/sprites/xy/${species?.identifier}.gif"
    }
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.with(view.context)
            .load(imageUrl)
            .fit()
            .into(view)
}