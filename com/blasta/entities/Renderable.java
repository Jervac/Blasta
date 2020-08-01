package com.blasta.entities;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public abstract class Renderable {

    public Renderable(Vector2 pos, Vector2 size, float speed) {
        this.pos = pos;
        this.size = size;
        this.speed = speed;
    }

    public float speed;

    public Vector2 pos = null;

    public Vector2 size = null;

    public boolean alive = true;

    public abstract void render();

    public abstract void update();

    public abstract void dispose();

    public Vector2 getPos() {return pos;}

    public void setPos(Vector2 pos) {this.pos = pos;}

    public Rectangle getBounds() {return new Rectangle((int)pos.x, (int)pos.y, (int)size.x, (int)size.y);}

    public boolean isAlive() {return alive;}

    public void setAlive(boolean alive) {this.alive = alive;}

}