
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;
import be.florien.poketeam.database.table.TranslationTableField;

@JoTable(isGeneratingWrite = false, tableName = "generations")
public class Generation implements Parcelable {

    @JoId
    public int id;
    public String identifier;
    @JoJoin(getTableClass = TranslationTableField.class)
    public DualStringTranslation generation_names;

    @JoIgnore
    public static Creator<Generation> CREATOR = new Creator<Generation>() {

        @Override
        public Generation[] newArray(int size) {
            return new Generation[size];
        }

        @Override
        public Generation createFromParcel(Parcel source) {
            return new Generation(source);
        }
    };

    public Generation() {
    }

    private Generation(Parcel source) {
        id = source.readInt();
        identifier = source.readString();
        generation_names = source.readParcelable(DualStringTranslation.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(identifier);
        dest.writeParcelable(generation_names, flags);
    }

}
