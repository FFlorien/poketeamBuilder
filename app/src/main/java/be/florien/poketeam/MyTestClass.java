package be.florien.poketeam;

import be.florien.joinorm.annotation.JoId;
import be.florien.joinorm.annotation.JoTable;

/**
 * Created by FlamentF on 22-08-16.
 */

@JoTable
public class MyTestClass {

    @JoId
    private String tutu;

    private String tataTutu;

    private int toto;

    private MyTestClassBis tutututu;
}
