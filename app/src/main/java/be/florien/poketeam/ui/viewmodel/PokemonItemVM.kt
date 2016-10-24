package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import be.florien.poketeam.BR
import be.florien.poketeam.R
import be.florien.poketeam.model.Pokemon

/**
 * Created by florien on 23/10/16.
 */
class PokemonItemVM(val pokemonView : ViewGroup) : BaseObservable() {
    private var pokemon: Pokemon? = null
    private val pokemonNameView: TextView by lazy {
        pokemonView.findViewById(R.id.name) as TextView
    }
    private val type1View: TextView by lazy {
        pokemonView.findViewById(R.id.type1) as TextView
    }
    private val type2View: TextView by lazy {
        pokemonView.findViewById(R.id.type2) as TextView
    }

    fun setPokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
        notifyPropertyChanged(BR.pokemon);
    }

    fun getPokemonName() : String {
        return pokemon?.pokemon_forms?.get(0)?.pokemon_form_names?.first ?: "No pokemon name"
    }

    fun getType1() : String {
        return pokemon?.pokemon_forms?.get(0)?.pokemon_form_names?.first ?: "No pokemon name"
    }

    fun getType2() : String {
        return pokemon?.types?.get(1)?.type_names?.first ?: pokemon?.types?.get(1)?.identifier ?: ""
    }

    fun getType2Visibility() : Int {
        return if ((pokemon?.types?.size ?: 0) > 1) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }



}