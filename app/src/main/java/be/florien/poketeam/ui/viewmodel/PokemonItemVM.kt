package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import android.view.View
import be.florien.poketeam.BR
import be.florien.poketeam.databinding.ItemPokemonBinding
import be.florien.poketeam.model.Pokemon

/**
 * Created by florien on 23/10/16.
 */
class PokemonItemVM(pokemonView: ItemPokemonBinding) : BaseObservable() {
    private var pokemon: Pokemon? = null
    init {
        pokemonView.pokemon = this
    }

    fun setPokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
        notifyPropertyChanged(BR.pokemon)
    }

    fun getPokemonName() : String {
        return pokemon?.pokemon_forms?.get(0)?.pokemon_form_names?.first ?: "No pokemon name"
    }

    fun getType1() : String {
        return pokemon?.types?.get(0)?.type_names?.first ?: "No type ???"
    }

    fun getType2() : String {
        return if (getType2Present()) {
            pokemon?.types?.get(1)?.type_names?.first ?: pokemon?.types?.get(1)?.identifier ?: ""
        } else {
            ""
        }
    }

    fun getType2Visibility() : Int {
        return if (getType2Present()) {
            View.VISIBLE
        }else {
            View.GONE
        }
    }

    private fun getType2Present() : Boolean {
        return if ((pokemon?.types?.size ?: 0) > 1) {
            true
        } else {
            false
        }
    }



}