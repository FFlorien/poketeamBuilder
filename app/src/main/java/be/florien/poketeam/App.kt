package be.florien.poketeam

import android.app.Application
import be.florien.joinorm.async.JoAsyncManager
import be.florien.poketeam.database.helper.DBPokedexHelper

/**
 * Created by florien on 26/10/16.
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        JoAsyncManager.getInstance().addHelper(DBPokedexHelper(this), DBPokedexHelper.TAG)
    }
}