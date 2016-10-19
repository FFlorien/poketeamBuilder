package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;
import be.florien.poketeam.database.table.TranslationTableField;

@JoTable(tableName = "items", isGeneratingWrite = false)
public class Item implements Parcelable {

    @JoId
    public int id;
    public String identifier;
    public int category_id;
    public int cost;
    public int fling_power;
    public int fling_effect_id;
    @JoJoin(getTableClass = TranslationTableField.class)
    public DualStringTranslation item_names;
    
    public Item(){
    }
    
    private Item(Parcel in){
        id = in.readInt();
        identifier = in.readString();
        category_id = in.readInt();
        cost = in.readInt();
        fling_power = in.readInt();
        fling_effect_id = in.readInt();
        item_names = in.readParcelable(DualStringTranslation.class.getClassLoader());
    }

    @JoIgnore
    public static Creator<Item> CREATOR = new Creator<Item>() {
        
        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
        
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(identifier);
        dest.writeInt(category_id);
        dest.writeInt(cost);
        dest.writeInt(fling_power);
        dest.writeInt(fling_effect_id);
        dest.writeParcelable(item_names, flags);
    }

}
