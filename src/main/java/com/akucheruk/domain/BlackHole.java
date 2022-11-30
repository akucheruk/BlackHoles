package com.akucheruk.domain;

/**
 * black hole object, represents danger structure on the board
 */
public class BlackHole {

    private boolean isHide = true;

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }
}
