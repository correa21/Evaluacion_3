package com.iteso.game.tiles;

import com.iteso.game.math.AABB;
import com.iteso.game.tiles.blocks.Block;

import java.awt.Graphics2D;

public abstract class TileMap {

    public abstract Block[] getBlocks();
    public abstract void render(Graphics2D g, AABB cam);
}
