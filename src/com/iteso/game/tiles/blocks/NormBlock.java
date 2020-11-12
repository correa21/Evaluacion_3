package com.iteso.game.tiles.blocks;

import java.awt.Graphics2D;

import com.iteso.game.graphics.Sprite;
import com.iteso.game.math.Vector2f;
import com.iteso.game.math.AABB;

public class NormBlock extends Block {

    public NormBlock(Sprite img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);

        img.setEffect(Sprite.effect.DECAY);
    }

    public boolean update(AABB p) {
        return false;
    }

    public Sprite getImage() { return img; }

    public boolean isInside(AABB p) {
        return false;
    }

    public void render(Graphics2D g){
        super.render(g);
    }

    public String toString() {
        return "position: " + pos;
    }
}
