
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DualStringTranslation implements Parcelable {

    public int id;

    public String first;
    public String second;

    public static Creator<DualStringTranslation> CREATOR = new Creator<DualStringTranslation>() {
        
        @Override
        public DualStringTranslation[] newArray(int size) {
            return new DualStringTranslation[size];
        }
        
        @Override
        public DualStringTranslation createFromParcel(Parcel source) {
            return new DualStringTranslation(source);
        }
    };

    public DualStringTranslation() {
    }

    private DualStringTranslation(Parcel source) {
        id = source.readInt();
        first = source.readString();
        second = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(first);
        dest.writeString(second);
    }

}
