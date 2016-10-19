
package be.florien.poketeam.model;

import be.florien.poketeam.R;

public enum MoveMetaAilmentClassEnum {
    ALL(-100, 0),
    PARALYSIS(1, R.drawable.ico_ailment_paralysis),
    SLEEP(2, R.drawable.ico_ailment_sleep),
    FREEZE(3, R.drawable.ico_ailment_freeze),
    BURN(4, R.drawable.ico_ailment_burn),
    POISON(5, R.drawable.ico_ailment_poison),
    CONFUSION(6, R.drawable.ico_ailment_confusion),
    INFATUATION(7, R.drawable.ico_ailment_infatued),
    TRAP(8, R.drawable.ico_ailment_trap),
    NIGHTMARE(9, R.drawable.ico_ailment_nightmare),
    TORMENT(12, R.drawable.ico_special_dmg),
    DISABLE(13, R.drawable.ico_special_dmg),
    YAWN(14, R.drawable.ico_special_dmg),
    HEAL_BLOCK(15, R.drawable.ico_special_dmg),
    NO_TYPE_IMMUNITY(17, R.drawable.ico_special_dmg),
    LEECH_SEED(18, R.drawable.ico_special_dmg),
    EMBARGO(19, R.drawable.ico_special_dmg),
    PERISH_SONG(20, R.drawable.ico_special_dmg),
    INGRAIN(21, R.drawable.ico_special_dmg);

    // TODO resizing and new images

    private int id;
    private int drawable;

    private MoveMetaAilmentClassEnum(int id, int backgroundColorRef) {
        this.id = id;
        this.drawable = backgroundColorRef;
    }

    public int getId() {
        return id;
    }

    public int getDrawableInt() {
        return drawable;
    }

    public static MoveMetaAilmentClassEnum getAilment(int id) {
        switch (id) {
            case -100:
                return ALL;
            case 1:
                return PARALYSIS;
            case 2:
                return SLEEP;
            case 3:
                return FREEZE;
            case 4:
                return BURN;
            case 5:
                return POISON;
            case 6:
                return CONFUSION;
            case 7:
                return INFATUATION;
            case 8:
                return TRAP;
            case 9:
                return NIGHTMARE;
            default:
                return ALL;
        }
    }

    public static int getDrawableRes(int idOfAilment) {
        return getAilment(idOfAilment).getDrawableInt();
    }

}
