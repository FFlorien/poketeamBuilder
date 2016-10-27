package be.florien.poketeam.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.florien.poketeam.R
import be.florien.poketeam.databinding.ItemPokemonBinding
import be.florien.poketeam.databinding.ItemSpeciesBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.inflateBinding
import be.florien.poketeam.ui.viewmodel.PokemonItemVM
import be.florien.poketeam.ui.viewmodel.SpeciesItemVM

class SpeciesAdapter() : RecyclerView.Adapter<SpeciesAdapter.SpeciesItemViewHolder>() {
    val species = mutableListOf<PokemonSpecie>()

    fun addToList(list: List<PokemonSpecie>) {
        species.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesItemViewHolder {
        val speciesBinding = parent.inflateBinding(R.layout.item_species) as ItemSpeciesBinding
        val childrenVM = mutableListOf<PokemonItemVM>()
        for (formViewIterator in 0..(viewType - 1)) {
            val pokemonBinding = speciesBinding.pokemonContainer.inflateBinding(R.layout.item_pokemon) as ItemPokemonBinding
            childrenVM.add(PokemonItemVM(pokemonBinding))
            speciesBinding.pokemonContainer.addView(pokemonBinding.root)
        }

        return SpeciesItemViewHolder(speciesBinding, childrenVM)
    }

    override fun onBindViewHolder(holder: SpeciesItemViewHolder, position: Int) {
        holder.setSpecies(species[position])
    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun getItemViewType(position: Int): Int {
        return species[position].pokemon.size
    }


    class SpeciesItemViewHolder(speciesBinding: ItemSpeciesBinding,
                                val pokemonItemVMs: List<PokemonItemVM>) : RecyclerView.ViewHolder(speciesBinding.root) {
        private var itemVM: SpeciesItemVM = SpeciesItemVM(speciesBinding)

        fun setSpecies(species: PokemonSpecie) {
            itemVM.setSpecies(species)
            for ((index, value) in species.pokemon.withIndex()) {
                pokemonItemVMs[index].setPokemon(value)
            }
        }
    }
}