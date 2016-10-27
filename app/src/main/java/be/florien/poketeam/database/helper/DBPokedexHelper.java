
package be.florien.poketeam.database.helper;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBPokedexHelper extends SQLiteAssetHelper {

    public static final String TAG = "pokedexDB";

    private static final String DATABASE_NAME = "veekun-pokedex.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DBPokedexHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
