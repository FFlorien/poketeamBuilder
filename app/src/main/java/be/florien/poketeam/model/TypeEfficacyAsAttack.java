package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;

@JoTable(tableName = "type_efficacy", isGeneratingWrite = false)
public class TypeEfficacyAsAttack implements Parcelable {

    @JoJoin(isReferenceJoin = true, getTableRef = "target_type_id", getAlias = "typeTargeted")
    public Type typeTargeted;
    public int damage_factor;
    @JoId
    public int target_type_id;

    public TypeEfficacyAsAttack() {
    }

    private TypeEfficacyAsAttack(Parcel in) {
        typeTargeted = in.readParcelable(Type.class.getClassLoader());
        damage_factor = in.readInt();
        target_type_id = in.readInt();
    }

    @JoIgnore
    public static final Creator<TypeEfficacyAsAttack> CREATOR = new Creator<TypeEfficacyAsAttack>() {
        public TypeEfficacyAsAttack createFromParcel(Parcel in) {
            return new TypeEfficacyAsAttack(in);
        }

        public TypeEfficacyAsAttack[] newArray(int size) {
            return new TypeEfficacyAsAttack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(typeTargeted, flags);
        dest.writeInt(damage_factor);
        dest.writeInt(target_type_id);
    }

}
