package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;

@JoTable(tableName = "machines", isGeneratingWrite = false)
public class Machine implements Parcelable {

    @JoId
    public int machine_number;
    public int version_group_id;
    public int item_id;
    public int move_id;
    @JoJoin(getTableRef = "item_id", isReferenceJoin = true, isLeftJoin = true)
    public Item items;
    @JoJoin(getTableRef = "move_id", isReferenceJoin = true)
    public Move moves;

    public Machine(){
    }
    
    private Machine(Parcel in){
        machine_number = in.readInt();
        version_group_id = in.readInt();
        item_id = in.readInt();
        move_id = in.readInt();
        items = in.readParcelable(Item.class.getClassLoader());
        moves = in.readParcelable(Move.class.getClassLoader());
    }

    @JoIgnore
    public static Creator<Machine> CREATOR = new Creator<Machine>() {
        
        @Override
        public Machine[] newArray(int size) {
            return new Machine[size];
        }
        
        @Override
        public Machine createFromParcel(Parcel source) {
            return new Machine(source);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(machine_number);
        dest.writeInt(version_group_id);
        dest.writeInt(item_id);
        dest.writeInt(move_id);
        dest.writeParcelable(items, flags);
        dest.writeParcelable(moves, flags);
    }

}
