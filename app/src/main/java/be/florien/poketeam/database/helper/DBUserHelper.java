
package be.florien.poketeam.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBUserHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pokedex_user_infos.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public DBUserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.beginTransaction();
        try {
            InputStream stream = context.getAssets().open("user_db_create.sql");
            StringBuilder builder = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String create;

            while ((create = reader.readLine()) != null) {
                builder.append(create);
            }
            reader.close();
            String statements[] = builder.toString().split(";");
            for (String statement : statements) {
                database.execSQL(statement);
            }
            database.setTransactionSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBUserHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        // TODO db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
