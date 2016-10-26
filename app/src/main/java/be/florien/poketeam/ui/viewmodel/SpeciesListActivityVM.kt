package be.florien.poketeam.ui.viewmodel

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.adapter.SpeciesAdapter

class SpeciesListActivityVM(private val viewBinding: ActivityPokemonListBinding, private val listener : LoadMoreItem) {

    val speciesAdapter = SpeciesAdapter()
    var updateAsked = false
    fun init() {
        viewBinding.apply {
            listStateView.text = "Loading"
            listStateView.visibility = View.VISIBLE
            pokemonSpeciesRecycler.visibility = View.GONE
            pokemonSpeciesRecycler.layoutManager = LinearLayoutManager(viewBinding.root.context)
            pokemonSpeciesRecycler.adapter = speciesAdapter
            pokemonSpeciesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    //super.onScrolled(recyclerView, dx, dy)
                    if ((pokemonSpeciesRecycler.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() > (pokemonSpeciesRecycler.adapter.itemCount -5) && !updateAsked) {
                        listener.loadMoreItems()
                        updateAsked = true
                    }
                }
            })
        }
    }

    fun setPokemonData(list: List<PokemonSpecie>) {
        viewBinding.apply {
            speciesAdapter.setList(list)
            listStateView.visibility = View.GONE
            pokemonSpeciesRecycler.visibility = View.VISIBLE
        }
        updateAsked = false
    }

    interface LoadMoreItem {
        fun loadMoreItems()
    }
}
