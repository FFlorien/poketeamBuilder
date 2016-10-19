
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;

@JoTable(tableName = "type_efficacy", isGeneratingWrite = false)
public class TypeEfficacyAsDefense implements Parcelable {

    @JoJoin(isReferenceJoin = true, getTableRef = "damage_type_id", getAlias = "typeAttacking")
    public Type typeAttacking;
    public int damage_factor;
    @JoId
    public int damage_type_id;

    public TypeEfficacyAsDefense() {
    }

    private TypeEfficacyAsDefense(Parcel in) {
        typeAttacking = in.readParcelable(Type.class.getClassLoader());
        damage_factor = in.readInt();
        damage_type_id = in.readInt();
    }

    @JoIgnore
    public static final Creator<TypeEfficacyAsDefense> CREATOR = new Creator<TypeEfficacyAsDefense>() {
        public TypeEfficacyAsDefense createFromParcel(Parcel in) {
            return new TypeEfficacyAsDefense(in);
        }

        public TypeEfficacyAsDefense[] newArray(int size) {
            return new TypeEfficacyAsDefense[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(typeAttacking, flags);
        dest.writeInt(damage_factor);
        dest.writeInt(damage_type_id);
    }

}
