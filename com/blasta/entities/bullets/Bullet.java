package com.blasta.entities.bullets;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public abstract class Bullet {

    public Bullet(Vector2 pos, Vector2 size, Vector2 speed, int damage, boolean isPlayer) {
        this.pos = pos;
        this.size = size;
        this.speed = speed;
        this.damage = damage;
        this.isPlayer = isPlayer;
    }

    Vector2 pos;

    Vector2 size;

    Vector2 speed;

    public int damage;

    public boolean isPlayer;


    public boolean alive = true;

    //screen shake affect amount variable


    public abstract void update();

    public abstract void render();

    public abstract void dispose();

    public Rectangle getBounds() {return new Rectangle((int)pos.x, (int)pos.y, (int)size.x, (int)size.y);}

}