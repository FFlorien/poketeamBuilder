
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;
import be.florien.poketeam.database.table.TranslationTableField;

@JoTable(tableName = "move_damage_classes", isGeneratingWrite = false)
public class MoveDamageClass implements Parcelable {

    @JoId
    public int id;
    @JoJoin(getTableClass = TranslationTableField.class)
    public DualStringTranslation move_damage_class_prose;

    @JoIgnore
    public static Creator<MoveDamageClass> CREATOR = new Creator<MoveDamageClass>() {
        
        @Override
        public MoveDamageClass[] newArray(int size) {
            return new MoveDamageClass[size];
        }
        
        @Override
        public MoveDamageClass createFromParcel(Parcel source) {
            return new MoveDamageClass(source);
        }
    };

    public MoveDamageClass() {
    }

    private MoveDamageClass(Parcel source) {
        id = source.readInt();
        move_damage_class_prose = source.readParcelable(DualStringTranslation.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(move_damage_class_prose, flags);
    }

}
