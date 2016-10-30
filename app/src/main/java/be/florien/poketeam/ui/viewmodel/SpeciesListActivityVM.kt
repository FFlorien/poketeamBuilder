package be.florien.poketeam.ui.viewmodel

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import be.florien.poketeam.async.PokemonSpecieListLoader
import be.florien.poketeam.database.helper.DBPokedexHelper
import be.florien.poketeam.database.table.TranslationTableField
import be.florien.poketeam.database.table.TypeTableTmpForPokemon
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.model.table.PokemonFormTable
import be.florien.poketeam.model.table.PokemonSpecieTable
import be.florien.poketeam.model.table.PokemonTable
import be.florien.poketeam.ui.adapter.SpeciesAdapter

class SpeciesListActivityVM(private val viewBinding: ActivityPokemonListBinding, private val listener: LoadMoreItem) : LoaderManager.LoaderCallbacks<List<PokemonSpecie>> {

    private val speciesAdapter = SpeciesAdapter()
    private val layoutManager = LinearLayoutManager(viewBinding.root.context)
    private var updateAsked = false
    private var shouldLoad = true
    private var counter = 0
    private var dbHelper: DBPokedexHelper
    private val specieTable: PokemonSpecieTable = PokemonSpecieTable()
            .selectId()
            .selectIdentifier()
            .selectPokemonSpeciesNames(
                    TranslationTableField.forPokemonSpecie())
            .selectPokemon(
                    PokemonTable()
                            .selectId()
                            .selectIdentifier()
                            .selectPokemonForms(
                                    PokemonFormTable()
                                            .selectId()
                                            .selectIdentifier()
                                            .selectFormIdentifier()
                                            .selectPokemonFormNames(
                                                    TranslationTableField.forPokemonForm()))
                            .selectTypes(
                                    (TypeTableTmpForPokemon()
                                            .setRepeatable() as TypeTableTmpForPokemon)
                                            .selectId()
                                            .selectIdentifier()
                                            .selectName()))

    init {
        dbHelper = DBPokedexHelper(viewBinding.root.context)
        viewBinding.apply {
            listStateView.text = "Loading"
            listStateView.visibility = View.VISIBLE
            pokemonSpeciesRecycler.visibility = View.GONE
            pokemonSpeciesRecycler.layoutManager = layoutManager
            pokemonSpeciesRecycler.adapter = speciesAdapter
            pokemonSpeciesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    if (shouldLoad && layoutManager.findLastVisibleItemPosition() > (speciesAdapter.itemCount - 15) && !updateAsked) {
                        listener.loadMoreItems()
                        updateAsked = true
                    }
                }
            })
        }
    }

    private fun setPokemonData(list: List<PokemonSpecie>) {
        viewBinding.apply {
            speciesAdapter.addToList(list)
            listStateView.visibility = View.GONE
            pokemonSpeciesRecycler.visibility = View.VISIBLE
        }
        updateAsked = false
    }

    fun onDestroy() {
        dbHelper.close()
    }

    override fun onLoadFinished(loader: Loader<List<PokemonSpecie>>?, data: List<PokemonSpecie>) {
        setPokemonData(data)
        shouldLoad = specieTable.hasMoreResults()
        nbToLoad = 50
    }

    private var nbToLoad = 20

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<PokemonSpecie>> {
        return PokemonSpecieListLoader(viewBinding.root.context, specieTable, dbHelper, nbToLoad, counter++)
    }

    override fun onLoaderReset(loader: Loader<List<PokemonSpecie>>?) {
    }

    interface LoadMoreItem {
        fun loadMoreItems()
    }
}
