
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;
import be.florien.poketeam.database.table.TranslationTableField;

@JoTable(tableName = "move_effects", isGeneratingWrite = false)
public class MoveEffect implements Parcelable {

    @JoId
    public int id;
    @JoJoin(getTableClass = TranslationTableField.class)
    public DualStringTranslation move_effect_prose;

    @JoIgnore
    public static Creator<MoveEffect> CREATOR = new Creator<MoveEffect>() {
        
        @Override
        public MoveEffect[] newArray(int size) {
            return new MoveEffect[size];
        }
        
        @Override
        public MoveEffect createFromParcel(Parcel source) {
            return new MoveEffect(source);
        }
    };

    public MoveEffect() {
    }
    
    private MoveEffect(Parcel source) {
        id = source.readInt();
        move_effect_prose = source.readParcelable(DualStringTranslation.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(move_effect_prose, flags);
    }

}
