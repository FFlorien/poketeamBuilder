package be.florien.poketeam.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.util.Log
import be.florien.poketeam.R
import be.florien.poketeam.async.PokemonSpecieListLoader
import be.florien.poketeam.databinding.ActivityPokemonListBinding
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.ui.viewmodel.SpeciesListActivityVM

class SpeciesListActivity : AppCompatActivity(), SpeciesListActivityVM.LoadMoreItem {
    override fun loadMoreItems() {
        Log.d("PKMN", "loadMore")
        supportLoaderManager.restartLoader(0, null, viewModel)
    }

    val viewModel by lazy { SpeciesListActivityVM(DataBindingUtil.setContentView<ActivityPokemonListBinding>(this, R.layout.activity_pokemon_list), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }

    override fun onResume() {
        super.onResume()
        supportLoaderManager.initLoader(0, null, viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
