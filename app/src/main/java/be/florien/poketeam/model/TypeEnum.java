
package be.florien.poketeam.model;

import be.florien.poketeam.R;

public enum TypeEnum {
    ALL(-100, R.color.type_background_none),
    NORMAL(1, R.color.type_background_normal),
    FIGHTING(2, R.color.type_background_fighting),
    FLYING(3, R.color.type_background_flying),
    POISON(4, R.color.type_background_poison),
    GROUND(5, R.color.type_background_ground),
    ROCK(6, R.color.type_background_rock),
    BUG(7, R.color.type_background_bug),
    GHOST(8, R.color.type_background_ghost),
    STEEL(9, R.color.type_background_steel),
    FIRE(10, R.color.type_background_fire),
    WATER(11, R.color.type_background_water),
    GRASS(12, R.color.type_background_grass),
    ELECTRIC(13, R.color.type_background_electric),
    PSYCHIC(14, R.color.type_background_psychic),
    ICE(15, R.color.type_background_ice),
    DRAGON(16, R.color.type_background_dragon),
    DARK(17, R.color.type_background_dark),
    FAIRY(18, R.color.type_background_fairy);

    private int id;
    private int backgroundColorRef;

    TypeEnum(int id, int backgroundColorRef) {
        this.id = id;
        this.backgroundColorRef = backgroundColorRef;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return backgroundColorRef;
    }

    public static TypeEnum getType(int id) {
        switch (id) {
            case -100:
                return ALL;
            case 1:
                return NORMAL;
            case 2:
                return FIGHTING;
            case 3:
                return FLYING;
            case 4:
                return POISON;
            case 5:
                return GROUND;
            case 6:
                return ROCK;
            case 7:
                return BUG;
            case 8:
                return GHOST;
            case 9:
                return STEEL;
            case 10:
                return FIRE;
            case 11:
                return WATER;
            case 12:
                return GRASS;
            case 13:
                return ELECTRIC;
            case 14:
                return PSYCHIC;
            case 15:
                return ICE;
            case 16:
                return DRAGON;
            case 17:
                return DARK;
            case 18:
                return FAIRY;
            default:
                return NORMAL;
        }
    }

//    public static ColorFilter getColorFilter(int id, Context context) {
//        return new PorterDuffColorFilter(context.getResources().getColor(getType(id).getColor()), Mode.SRC_ATOP);
//    }

}
