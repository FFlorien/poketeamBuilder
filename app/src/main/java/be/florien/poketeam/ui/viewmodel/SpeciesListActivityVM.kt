package be.florien.poketeam.ui.viewmodel

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.adapter.SpeciesAdapter

class SpeciesListActivityVM(private val viewBinding: ActivityPokemonListBinding, private val listener : LoadMoreItem) {

    val speciesAdapter = SpeciesAdapter()
    val layoutManager = LinearLayoutManager(viewBinding.root.context)
    var updateAsked = false

    fun init() {
        viewBinding.apply {
            listStateView.text = "Loading"
            listStateView.visibility = View.VISIBLE
            pokemonSpeciesRecycler.visibility = View.GONE
            pokemonSpeciesRecycler.layoutManager = layoutManager
            pokemonSpeciesRecycler.adapter = speciesAdapter
            pokemonSpeciesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    //super.onScrolled(recyclerView, dx, dy)
                    Log.d("PKMN", "onScrolled, last item visible is ${layoutManager.findLastCompletelyVisibleItemPosition()}, itemCount is ${speciesAdapter.itemCount}, and updateAsked = $updateAsked")
                    if (layoutManager.findLastVisibleItemPosition() > (speciesAdapter.itemCount -5) && !updateAsked) {
                        listener.loadMoreItems()
//                        updateAsked = true
                    }
                }
            })
        }
    }

    fun setPokemonData(list: List<PokemonSpecie>) {
        Log.d("PKMN", "setPokemonData 1")
        viewBinding.apply {
            speciesAdapter.setList(list)
            listStateView.visibility = View.GONE
            pokemonSpeciesRecycler.visibility = View.VISIBLE
        }
        Log.d("PKMN", "setPokemonData 2")
//        updateAsked = false
    }

    interface LoadMoreItem {
        fun loadMoreItems()
    }
}
