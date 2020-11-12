package com.iteso.game.utils;

import com.iteso.game.entity.GameObject;
import com.iteso.game.math.AABB;

public class GameObjectKey {

    public float value;
    public GameObject go;

    public GameObjectKey(float value, GameObject go) {
        this.value = value;
        this.go = go;
    }

    public AABB getBounds() { return go.getBounds(); }
}