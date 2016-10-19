
package be.florien.poketeam.model;

import android.content.Context;

import be.florien.poketeam.R;

public enum PokemonMoveMethodEnum {

    LEVELUP(1, R.string.move_method_level),
    EGG(2, R.string.move_method_egg),
    TUTOR(3, R.string.move_method_tutor),
    MACHINE(4, R.string.move_method_machine),
    STADIUM_SURFING_PIKACHU(5, R.string.move_method_stadium),
    LIGHT_BALL_EGG(6, R.string.move_method_light_ball),
    COLOSSEUM_PURIFICATION(7, R.string.move_method_colosseum),
    XD_SHADOW(8, R.string.move_method_xd_shadow),
    XD_PURIFICATION(9, R.string.move_method_xd_purification),
    FORM_CHANGE(10, R.string.move_method_form);

    private int mId;
    private int mRes;

    private PokemonMoveMethodEnum(int id, int res) {
        mId = id;
        mRes = res;
    }

    public static String getLabel(Context context, int methodid, int level) {
        PokemonMoveMethodEnum method = getMethodFromId(methodid);
        if (method == LEVELUP) {
            return context.getString(method.mRes, level);
        } else {
            return context.getString(method.mRes);
        }
    }

    private static PokemonMoveMethodEnum getMethodFromId(int methodid) {
        for (PokemonMoveMethodEnum item : PokemonMoveMethodEnum.values()) {
            if (item.mId == methodid) {
                return item;
            }
        }
        return MACHINE;
    }

}
