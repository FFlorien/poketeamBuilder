package be.florien.poketeam.ui.viewmodel

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.adapter.SpeciesAdapter

/**
 * Created by FlamentF on 13-10-16.
 */

class SpeciesListActivityVM(private val viewBinding: ActivityPokemonListBinding) {

    fun init() {
        viewBinding.listStateView.text = "Loading"
        viewBinding.listStateView.visibility = View.VISIBLE
        viewBinding.pokemonSpeciesRecycler.visibility = View.GONE
        viewBinding.pokemonSpeciesRecycler.layoutManager = LinearLayoutManager(viewBinding.root.context)
    }

    fun setPokemonData(list: List<PokemonSpecie>) {
        viewBinding.pokemonSpeciesRecycler.adapter = SpeciesAdapter(list)
        viewBinding.listStateView.visibility = View.GONE
        viewBinding.pokemonSpeciesRecycler.visibility = View.VISIBLE
    }
}
