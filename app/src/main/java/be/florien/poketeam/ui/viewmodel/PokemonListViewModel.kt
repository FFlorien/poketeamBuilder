package be.florien.poketeam.ui.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import be.florien.poketeam.R
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie

/**
 * Created by FlamentF on 13-10-16.
 */

class PokemonListViewModel(private val viewBinding: ActivityPokemonListBinding) {

    fun init() {
        viewBinding.listStateView.text = "Loading"
        viewBinding.listStateView.visibility = View.VISIBLE
        viewBinding.pokemonSpeciesRecycler.visibility = View.GONE
    }

    fun setPokemonData(list: List<PokemonSpecie>) {
        viewBinding.pokemonSpeciesRecycler.adapter = PokemonSpecieAdapter(list)
        viewBinding.listStateView.visibility = View.GONE
        viewBinding.pokemonSpeciesRecycler.visibility = View.VISIBLE
    }
}

class PokemonSpecieAdapter(val species: List<PokemonSpecie>) : RecyclerView.Adapter<PokemonSpecieViewModel>() {
    override fun onBindViewHolder(holder: PokemonSpecieViewModel, position: Int) {
        holder.name.text = species[position].pokemon_species_names?.first
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSpecieViewModel {
        return PokemonSpecieViewModel(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (species[position].pokemon.size) {
            2 -> R.layout.item_pokemon_species
            3 -> R.layout.item_pokemon_species
            else -> R.layout.item_pokemon_species
        }
    }
}

class PokemonSpecieViewModel(itemView: View,
                             val name: TextView = itemView.findViewById(R.id.name) as TextView,
                             val form: LinearLayout = itemView.) : RecyclerView.ViewHolder(itemView) {
}
