
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoTable;

@JoTable
public class UserPokemon implements Parcelable {

    @JoId
    public int id;
    public int pokemon_id;
    public int level;
    public boolean has_evolved;

    public UserPokemon() {
    }

    private UserPokemon(Parcel in) {
        id = in.readInt();
        pokemon_id = in.readInt();
        level = in.readInt();
        has_evolved = in.readInt() == 1;
    }

    @JoIgnore
    public static Creator<UserPokemon> CREATOR = new Creator<UserPokemon>() {

        @Override
        public UserPokemon[] newArray(int size) {
            return new UserPokemon[size];
        }

        @Override
        public UserPokemon createFromParcel(Parcel source) {
            return new UserPokemon(source);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(pokemon_id);
        dest.writeInt(level);
        dest.writeInt(has_evolved ? 1 : 0);
    }

}
