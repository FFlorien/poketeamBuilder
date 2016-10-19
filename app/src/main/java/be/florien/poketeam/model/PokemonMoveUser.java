
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PokemonMoveUser implements Parcelable {

    public int pokemon_id;
    public int move_id;

    public static Creator<PokemonMoveUser> CREATOR = new Creator<PokemonMoveUser>() {

        @Override
        public PokemonMoveUser[] newArray(int size) {
            return new PokemonMoveUser[size];
        }

        @Override
        public PokemonMoveUser createFromParcel(Parcel source) {
            return new PokemonMoveUser(source);
        }
    };

    public PokemonMoveUser() {
    }

    private PokemonMoveUser(Parcel in) {
        pokemon_id = in.readInt();
        move_id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pokemon_id);
        dest.writeInt(move_id);
    }

}
