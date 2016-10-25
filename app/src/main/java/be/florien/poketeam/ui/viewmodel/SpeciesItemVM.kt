package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.widget.ImageView
import be.florien.poketeam.BR
import be.florien.poketeam.R
import be.florien.poketeam.databinding.ItemSpeciesBinding
import be.florien.poketeam.model.PokemonSpecie
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    val pixelSize = view.context.resources.getDimensionPixelSize(R.dimen.item_image_size)
    Picasso.with(view.context)
            .load(imageUrl)
            .resize(pixelSize,pixelSize)
//            .centerInside()
            .into(view)
}

class SpeciesItemVM(var speciesBinding: ItemSpeciesBinding) : BaseObservable() {
    private var species: PokemonSpecie? = null

    init {
        speciesBinding.setVariable(BR.species, this)
    }

    fun setSpecies(species: PokemonSpecie) {
        this.species = species
        notifyPropertyChanged(BR.speciesName)
        notifyPropertyChanged(BR.imageUrl)
    }

    @Bindable
    fun getSpeciesName(): String {
        return species?.pokemon_species_names?.first
                ?: species?.identifier
                ?: "Unknown name"
    }

    @Bindable
    fun getImageUrl(): String {
        return "http://www.pokestadium.com/assets/img/sprites/${species?.id}.png"
    }
}