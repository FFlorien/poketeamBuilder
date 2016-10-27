package be.florien.poketeam.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import be.florien.joinorm.async.JoAsyncManager

/**
 * Created by florien on 26/10/16.
 */
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        JoAsyncManager.getInstance().activityAlive(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        JoAsyncManager.getInstance().activityDead(this)
    }
}