
package be.florien.poketeam.model;

import be.florien.poketeam.R;

public enum MoveDamageClassEnum {
    ALL(-100, 0),
    STATUS(1, R.drawable.ico_status_dmg),
    PHYSICAL(2, R.drawable.ico_physical_dmg),
    SPECIAL(3, R.drawable.ico_special_dmg);

    private int id;
    private int drawable;

    private MoveDamageClassEnum(int id, int backgroundColorRef) {
        this.id = id;
        this.drawable = backgroundColorRef;
    }

    public int getId() {
        return id;
    }

    public int getDrawableInt() {
        return drawable;
    }

    public static MoveDamageClassEnum getDamageClass(int id) {
        switch (id) {
            case -100:
                return ALL;
            case 1:
                return STATUS;
            case 2:
                return PHYSICAL;
            case 3:
                return SPECIAL;
            default:
                return ALL;
        }
    }

    public static int getDrawableRes(int idOfDamageClass) {
        return getDamageClass(idOfDamageClass).getDrawableInt();
    }

}
