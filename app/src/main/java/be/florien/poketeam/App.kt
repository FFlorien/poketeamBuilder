package be.florien.poketeam

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by florien on 30/10/16.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}