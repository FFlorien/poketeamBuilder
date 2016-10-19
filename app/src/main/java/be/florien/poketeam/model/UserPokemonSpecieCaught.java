
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoTable;

@JoTable(tableName = "pokemon_specie_caught")
public class UserPokemonSpecieCaught implements Parcelable {

    @JoId
    public int id;

    public UserPokemonSpecieCaught() {
    }

    private UserPokemonSpecieCaught(Parcel in) {
        id = in.readInt();
    }

    @JoIgnore
    public static Creator<UserPokemonSpecieCaught> CREATOR = new Creator<UserPokemonSpecieCaught>() {

        @Override
        public UserPokemonSpecieCaught[] newArray(int size) {
            return new UserPokemonSpecieCaught[size];
        }

        @Override
        public UserPokemonSpecieCaught createFromParcel(Parcel source) {
            return new UserPokemonSpecieCaught(source);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

}
