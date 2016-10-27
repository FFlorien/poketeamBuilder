package be.florien.poketeam.async

import android.content.Context
import android.util.Log
import be.florien.poketeam.database.helper.DBPokedexHelper
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.model.table.PokemonSpecieTable

class PokemonSpecieListLoader(context: Context, val specieTable: PokemonSpecieTable, val dbPokedexHelper: DBPokedexHelper, val count: Int, val counter: Int) : AbstractAsyncTaskLoader<List<PokemonSpecie>>(context) {

    override fun loadInBackground(): List<PokemonSpecie> {
        val start = System.nanoTime()
        val result = specieTable.getResult(dbPokedexHelper, count)
        val stop = System.nanoTime()
        Log.d("PKMN", "${stop - start} nanosecond for parsing iteration no $counter")
        return result
    }
}
