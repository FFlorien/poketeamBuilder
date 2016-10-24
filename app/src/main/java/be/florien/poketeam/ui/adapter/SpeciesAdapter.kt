package be.florien.poketeam.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import be.florien.poketeam.R
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.viewmodel.PokemonItemVM
import be.florien.poketeam.ui.viewmodel.SpeciesItemVM

/**
 * Created by florien on 23/10/16.
 */

class SpeciesAdapter(val species: List<PokemonSpecie>) : RecyclerView.Adapter<SpeciesItemVM>() {
    override fun onBindViewHolder(holder: SpeciesItemVM, position: Int) {
        holder.setSpecies(species[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesItemVM {
        val parentView = LayoutInflater.from(parent.context).inflate(R.layout.item_species, parent, false) as LinearLayout
        val childrenVM = mutableListOf<PokemonItemVM>()
        for (formViewIterator in 0..(viewType - 1)) {
            val formView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parentView, false) as ViewGroup
            childrenVM.add(PokemonItemVM(formView))
            parentView.addView(formView)
        }

        return SpeciesItemVM(parentView, childrenVM)
    }

    override fun getItemCount(): Int {
        return species.size
    }

    override fun getItemViewType(position: Int): Int {
        return species[position].pokemon.size
    }
}