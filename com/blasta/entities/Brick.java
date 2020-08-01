package com.blasta.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.blasta.core.Blasta;

public class Brick extends Renderable{

    public Brick(Vector2 pos, Vector2 size, float speed) {
        super(pos, size, speed);
    }

    int hp = 3;

    public void update() {
        if(hp == 0)
            alive = false;
    }

    public void render() {
        if(alive) {
            if (hp == 3)
                Blasta.sr.setColor(Color.GREEN);
            if (hp == 2)
                Blasta.sr.setColor(Color.ORANGE);
            if (hp == 1)
                Blasta.sr.setColor(Color.RED);
            Blasta.sr.begin(ShapeRenderer.ShapeType.Filled);
            Blasta.sr.rect(pos.x, pos.y, size.x, size.y);
            Blasta.sr.end();
        }
    }

    public void dispose() {

    }

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}
}