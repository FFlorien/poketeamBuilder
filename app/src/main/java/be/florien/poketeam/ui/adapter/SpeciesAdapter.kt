package be.florien.poketeam.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import be.florien.poketeam.R
import be.florien.poketeam.databinding.ItemPokemonBinding
import be.florien.poketeam.databinding.ItemSpeciesBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.viewmodel.PokemonItemVM
import be.florien.poketeam.ui.viewmodel.SpeciesItemVM

/**
 * Created by florien on 23/10/16.
 */

class SpeciesAdapter(val species: List<PokemonSpecie>) : RecyclerView.Adapter<SpeciesAdapter.SpeciesItemViewHolder>() {
    override fun onBindViewHolder(holder: SpeciesItemViewHolder, position: Int) {
        holder.setSpecies(species[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesItemViewHolder {
        val speciesBinding = DataBindingUtil.inflate<ItemSpeciesBinding>(LayoutInflater.from(parent.context),R.layout.item_species, parent, false)
        val childrenVM = mutableListOf<PokemonItemVM>()
        for (formViewIterator in 0..(viewType - 1)) {
            val pokemonBinding = DataBindingUtil.inflate<ItemPokemonBinding>(LayoutInflater.from(parent.context), R.layout.item_pokemon, speciesBinding.speciesContainer as ViewGroup?, false)
            childrenVM.add(PokemonItemVM(pokemonBinding))
            speciesBinding.speciesContainer.addView(pokemonBinding.root)
        }

        return SpeciesItemViewHolder(speciesBinding, childrenVM)
    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun getItemViewType(position: Int): Int {
        return species[position].pokemon.size
    }


    class SpeciesItemViewHolder(speciesBinding: ItemSpeciesBinding,
                                var pokemonItemVMs: List<PokemonItemVM>) : RecyclerView.ViewHolder(speciesBinding.root) {
        private var itemVM : SpeciesItemVM = SpeciesItemVM(speciesBinding, pokemonItemVMs)

        fun setSpecies(species: PokemonSpecie) {
            itemVM.setSpecies(species)
        }
    }
}