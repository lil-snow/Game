package org.lilsnow.game;

import org.lilsnow.game.core.Window;
import org.lwjgl.Version;

public class EntryPoint {

    public static void main(String[] args) {
        Window.init();
        Window.run();
    }

}
