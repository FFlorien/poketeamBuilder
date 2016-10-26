package be.florien.poketeam.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import be.florien.poketeam.R
import be.florien.poketeam.async.PokemonSpecieListLoader
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.viewmodel.SpeciesListActivityVM

class SpeciesListActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<PokemonSpecie>>, SpeciesListActivityVM.LoadMoreItem {
    override fun loadMoreItems() {
        supportLoaderManager.getLoader<List<PokemonSpecie>>(0).forceLoad()
    }

    val viewModel by lazy { SpeciesListActivityVM(DataBindingUtil.setContentView<ActivityPokemonListBinding>(this, R.layout.activity_pokemon_list), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onResume() {
        super.onResume()
        supportLoaderManager.initLoader(0, null, this)
    }

    override fun onLoadFinished(loader: Loader<List<PokemonSpecie>>?, data: List<PokemonSpecie>) {
        viewModel.setPokemonData(data)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<PokemonSpecie>> {
        return PokemonSpecieListLoader(this)
    }

    override fun onLoaderReset(loader: Loader<List<PokemonSpecie>>?) {
    }
}
