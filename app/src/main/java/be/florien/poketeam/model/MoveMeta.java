
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;

@JoTable(tableName = "move_meta", isGeneratingWrite = false)
public class MoveMeta implements Parcelable {

    @JoId
    public int move_id;
    public int meta_category_id;
    public int meta_ailment_id;
    public int min_hits;
    public int max_hits;
    public int min_turns;
    public int max_turns;
    public int recoil;
    public int healing;
    public int crit_rate;
    public int ailment_chance;
    public int flinch_chance;
    public int stat_chance;
    @JoJoin(getTableRef = "meta_ailment_id", isReferenceJoin = true)
    public MoveMetaAilment move_meta_ailments;

    @JoIgnore
    public static Creator<MoveMeta> CREATOR = new Creator<MoveMeta>() {

        @Override
        public MoveMeta[] newArray(int size) {
            return new MoveMeta[size];
        }

        @Override
        public MoveMeta createFromParcel(Parcel source) {
            return new MoveMeta(source);
        }
    };

    public MoveMeta() {

    }

    private MoveMeta(Parcel in) {
        move_id = in.readInt();
        meta_category_id = in.readInt();
        meta_ailment_id = in.readInt();
        min_hits = in.readInt();
        max_hits = in.readInt();
        min_turns = in.readInt();
        max_turns = in.readInt();
        recoil = in.readInt();
        healing = in.readInt();
        crit_rate = in.readInt();
        ailment_chance = in.readInt();
        flinch_chance = in.readInt();
        stat_chance = in.readInt();
        move_meta_ailments = in.readParcelable(MoveMetaAilment.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(move_id);
        dest.writeInt(meta_category_id);
        dest.writeInt(meta_ailment_id);
        dest.writeInt(min_hits);
        dest.writeInt(max_hits);
        dest.writeInt(min_turns);
        dest.writeInt(max_turns);
        dest.writeInt(recoil);
        dest.writeInt(healing);
        dest.writeInt(crit_rate);
        dest.writeInt(ailment_chance);
        dest.writeInt(flinch_chance);
        dest.writeInt(stat_chance);
        dest.writeParcelable(move_meta_ailments, flags);
    }

}
