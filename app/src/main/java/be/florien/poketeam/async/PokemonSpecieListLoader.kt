package be.florien.poketeam.async

import android.content.Context
import android.util.Log

import be.florien.joinorm.queryhandling.JOQueryHelper
import be.florien.poketeam.database.helper.DBPokedexHelper
import be.florien.poketeam.database.table.TranslationTableField
import be.florien.poketeam.database.table.TypeTableTmpForPokemon
import be.florien.poketeam.model.PokemonSpecie
import be.florien.poketeam.model.table.PokemonFormTable
import be.florien.poketeam.model.table.PokemonSpecieTable
import be.florien.poketeam.model.table.PokemonTable

class PokemonSpecieListLoader(context: Context)
: AbstractAsyncTaskLoader<List<PokemonSpecie>>(context) {

    override fun loadInBackground(): List<PokemonSpecie> {
        val dataQueryHelper = JOQueryHelper(DBPokedexHelper(context))
        val pokemonFormTable = PokemonFormTable().selectId().selectPokemonFormNames(TranslationTableField.forPokemonForm())
        val pokemonTable = PokemonTable().selectId().selectTypes(TypeTableTmpForPokemon().selectId()).selectPokemonForms(pokemonFormTable)
        val specieTable = PokemonSpecieTable().selectId().selectPokemonSpeciesNames(TranslationTableField.forPokemonSpecie()).selectPokemon(pokemonTable)
        val start = System.nanoTime()
        val query = dataQueryHelper.queryList(specieTable)
        val stop = System.nanoTime()
        Log.d("PKMN", "Duration for loading of species with reflection == " + (stop - start))
        return query
    }
}
