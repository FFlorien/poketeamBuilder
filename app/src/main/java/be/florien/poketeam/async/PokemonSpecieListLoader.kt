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

class PokemonSpecieListLoader(context: Context) : AbstractAsyncTaskLoader<List<PokemonSpecie>>(context) {

    val specieTable : PokemonSpecieTable = PokemonSpecieTable()
            .selectId()
            .selectIdentifier()
            .selectPokemonSpeciesNames(
                    TranslationTableField.forPokemonSpecie())
            .selectPokemon(
                    PokemonTable()
                            .selectId()
                            .selectIdentifier()
                            .selectTypes(
                                    TypeTableTmpForPokemon()
                                            .selectId()
                                            .selectIdentifier())
//                                                .selectName())
                            .selectPokemonForms(
                                    PokemonFormTable()
                                            .selectId()
                                            .selectIdentifier()
                                            .selectFormIdentifier()
                                            .selectPokemonFormNames(
                                                    TranslationTableField.forPokemonForm())))

    override fun loadInBackground(): List<PokemonSpecie> {
        val dataQueryHelper = JOQueryHelper(DBPokedexHelper(context))
        val start = System.nanoTime()
        val query = dataQueryHelper.queryList(specieTable, 20 /*.addWhere(WhereStatement(PokemonSpecieTable.COLUMN_ID, 300, WhereCondition.LESS_EQUAL))*/)
        val stop = System.nanoTime()
        Log.d("PKMN", "Duration for loading of species with reflection == " + (stop - start))
        return query
    }
}
