package be.florien.poketeam.ui.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import be.florien.poketeam.BR
import be.florien.poketeam.databinding.ItemPokemonBinding
import be.florien.poketeam.model.Pokemon
import be.florien.poketeam.model.TypeEnum

class PokemonItemVM(val pokemonItemBinding: ItemPokemonBinding) : BaseObservable() {
    private var pokemon: Pokemon? = null
    init {
        pokemonItemBinding.setVariable(BR.pokemon, this)
    }

    fun setPokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
        notifyPropertyChanged(BR.pokemonName)
        notifyPropertyChanged(BR.type1)
        notifyPropertyChanged(BR.type1Background)
        notifyPropertyChanged(BR.type2)
        notifyPropertyChanged(BR.type2Background)
        notifyPropertyChanged(BR.type2Visibility)
    }

    @Bindable
    fun getPokemonName() : String {
        return pokemon?.pokemon_forms?.get(0)?.pokemon_form_names?.second
                ?:pokemon?.pokemon_forms?.get(0)?.pokemon_form_names?.first
                ?: pokemon?.pokemon_forms?.get(0)?.identifier
                ?: pokemon?.pokemon_forms?.get(0)?.form_identifier
                ?: "No form name"
    }

    @Bindable
    fun getType1() : String {
        return pokemon?.types?.get(0)?.type_names?.first ?: "No type ???"
    }

    @Bindable
    fun getType1Background() : Int {
        return ContextCompat.getColor(pokemonItemBinding.root.context, TypeEnum.getType(pokemon?.types?.get(0)?.id ?: TypeEnum.ALL.id).color)
    }

    @Bindable
    fun getType2Background() : Int {
        return if (getType2Present()) {
            ContextCompat.getColor(pokemonItemBinding.root.context, TypeEnum.getType(pokemon?.types?.get(1)?.id ?: TypeEnum.ALL.id).color)
        } else {
            ContextCompat.getColor(pokemonItemBinding.root.context, TypeEnum.ALL.color)
        }
    }

    @Bindable
    fun getType2() : String {
        return if (getType2Present()) {
            pokemon?.types?.get(1)?.type_names?.first ?: pokemon?.types?.get(1)?.identifier ?: ""
        } else {
            ""
        }
    }

    fun getNameVisibility() : Int {
        return if ((pokemonItemBinding.root.parent as LinearLayout).indexOfChild(pokemonItemBinding.root) == 0) {
            View.GONE
        }else {
            View.VISIBLE
        }
    }

    @Bindable
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