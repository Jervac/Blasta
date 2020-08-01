package com.blasta.entities;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Barrier extends Renderable{

    public Barrier(Vector2 pos, Vector2 size, float speed) {
        super(pos, size, speed);

        for(int i=0; i<size.x; ++i) {
            for(int j=0; j<size.y ; ++j) {
                bricks.add(new Brick(new Vector2(pos.x + (i * 16),pos.y + (j * 16)), new Vector2(16, 16), 1));
            }
        }
        bricks.add(new Brick(new Vector2(pos.x, pos.y - 16), new Vector2(16, 16), 1));
        bricks.add(new Brick(new Vector2(pos.x + ((size.x - 1) * 16), pos.y - 16), new Vector2(16, 16), 1));
    }

    ArrayList<Brick> bricks = new ArrayList<Brick>();

    public void update() {
        if(alive) {
            for (Brick b : bricks)
                b.update();
        } else {
            for (Brick b : bricks)
                b.alive = false;
        }
    }

    public void render() {
        if(alive) {
            for (Brick b : bricks)
                b.render();
        }
    }

    public void dispose() {
        for(Brick b: bricks)
            b.dispose();
    }

    public ArrayList<Brick> getBricks() {return bricks;}

    @Override
    public Rectangle getBounds() {return new Rectangle((int)pos.x, (int)pos.y, (int)size.x * 16, (int)size.y * 16);}

}