
package be.florien.poketeam.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoIgnore;
import be.florien.joinorm.annotation.JoJoin;
import be.florien.joinorm.annotation.JoTable;
import be.florien.poketeam.database.table.TranslationTableField;

@JoTable(tableName = "moves", isGeneratingWrite = false)
public class Move implements Comparable<Move>, Parcelable {

    @JoId
    public int id = -10;
    public String identifier;
    public int power;
    public int pp;
    public int accuracy;
    public int priority;
    public int effect_chance;
    //    public MoveTarget move_targets;
    @JoJoin(getTableRef = "generation_id", isReferenceJoin = true)
    public Generation generations;
    @JoJoin(getTableRef = "type_id", isReferenceJoin = true)
    public Type types;
    @JoJoin(getTableRef = "damage_class_id", isReferenceJoin = true)
    public MoveDamageClass move_damage_classes;
    @JoJoin(getTableRef = "effect_id", isReferenceJoin = true)
    public MoveEffect move_effects;
    @JoJoin(getTableClass = TranslationTableField.class)
    public DualStringTranslation move_names;
    @JoJoin(getTableRef = "move_id")
    public MoveMeta move_meta;
    @JoJoin(getTableRef = "move_id", isLeftJoin = true)
    public List<PokemonMoveForMove> pokemon_moves;
    @JoJoin(getTableRef = "move_id", isLeftJoin = true)
    public Machine machines;

    public Move() {
    }

    private Move(Parcel in) {
        id = in.readInt();
        identifier = in.readString();
        generations = in.readParcelable(Generation.class.getClassLoader());
        types = in.readParcelable(Type.class.getClassLoader());
        power = in.readInt();
        pp = in.readInt();
        accuracy = in.readInt();
        priority = in.readInt();
        move_damage_classes = in.readParcelable(MoveDamageClass.class.getClassLoader());
        move_effects = in.readParcelable(MoveEffect.class.getClassLoader());
        effect_chance = in.readInt();
        move_names = in.readParcelable(DualStringTranslation.class.getClassLoader());
        move_meta = in.readParcelable(MoveMeta.class.getClassLoader());
        machines = in.readParcelable(Machine.class.getClassLoader());
        pokemon_moves = new ArrayList<>();
        in.readTypedList(pokemon_moves, PokemonMoveForMove.CREATOR);
    }

    @Override
    public int compareTo(@NonNull Move another) {
        if (move_names != null && another.move_names != null) {
            return move_names.first.compareTo(another.move_names.first);
        } else {
            return 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(identifier);
        dest.writeParcelable(generations, flags);
        dest.writeParcelable(types, flags);
        dest.writeInt(power);
        dest.writeInt(pp);
        dest.writeInt(accuracy);
        dest.writeInt(priority);
        // dest.writeMoveTarget move_targets);
        dest.writeParcelable(move_damage_classes, flags);
        dest.writeParcelable(move_effects, flags);
        dest.writeInt(effect_chance);
        dest.writeParcelable(move_names, flags);
        dest.writeParcelable(move_meta, flags);
        dest.writeParcelable(machines, flags);
        dest.writeTypedList(pokemon_moves);
    }

    @JoIgnore
    public static final Creator<Move> CREATOR = new Creator<Move>() {
        public Move createFromParcel(Parcel in) {
            return new Move(in);
        }

        public Move[] newArray(int size) {
            return new Move[size];
        }
    };
}
