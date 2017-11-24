package com.group4;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Tobias on 13/11/2017.
 */

public class UserInputProcessor extends GameLogic implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        if (keycode== Input.Keys.UP){
            super.remove();
            super.init();
        }
        if (keycode== Input.Keys.LEFT){
            super.remove();
            super.moveRight();
        }
        if (keycode== Input.Keys.RIGHT){
            super.remove();
            super.moveLeft();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
