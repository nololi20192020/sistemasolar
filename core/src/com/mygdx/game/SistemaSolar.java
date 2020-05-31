package com.mygdx.game;

import com.badlogic.gdx.Game;

public class SistemaSolar extends Game {
    private Pantalla pantalla;

    @Override
    public void create() {
        pantalla = new Pantalla();
        setScreen(pantalla);
    }

    @Override
    public void dispose() {
        super.dispose();
        pantalla.dispose();
    }
}
