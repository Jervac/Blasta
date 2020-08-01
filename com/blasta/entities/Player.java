package com.blasta.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.blasta.core.Blasta;
import com.blasta.entities.bullets.Pellet;
import com.blasta.entities.bullets.Bullet;

import java.util.ArrayList;

public class Player extends Renderable{

    boolean canShoot = true;

    public float shootDelay = 0.9f;

    public Vector2 shootSpeed = new Vector2(0, 800);

    public Player(Vector2 pos, Vector2 size, float speed) {
        super(pos, size, speed);
    }


    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public void render() {
        Blasta.sr.setColor(Color.BLUE);
        Blasta.sr.begin(ShapeRenderer.ShapeType.Filled);
        Blasta.sr.rect(pos.x - size.x/2, pos.y, size.x, size.y);
        Blasta.sr.end();

        for(Bullet p: bullets)
            p.render();
    }


    public void update() {
        input();
        Blasta.cam.position.set(Blasta.cam.position.x + (pos.x - Blasta.cam.position.x) * 0.09f, 200, 0);



        for(Bullet p: bullets)
            p.update();


        //fucking hackish colision
        if(getPos().x <= (16 * 6) - 400 -150)
            pos.x = ((16 * 6) - 400 -150) + size.x/2;

        if(getPos().x + size.x >= (16 * 6) + 400 +220)
            pos.x = ((16 * 6) + 400 +220) - size.x/2;

    }

    private void input() {
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pos.x -= speed * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pos.x += speed * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && canShoot || Gdx.input.isKeyPressed(Input.Keys.UP) && canShoot) {
            canShoot = false;
            bullets.add(new Pellet(new Vector2(pos.x, pos.y), new Vector2(5, 15), shootSpeed, 1, true));
            Timer.schedule(new Timer.Task() {
                public void run() {
                    canShoot = true;
                }
            }, shootDelay);
        }
    }


    //position of player where it is rendered. cam wont move so i did this
    @Override
    public Vector2 getPos() {
        return new Vector2(pos.x - size.x/2, pos.y);
    }

    public void dispose() {
        for(Bullet p: bullets)
            p.dispose();
    }

    public ArrayList<Bullet> getBullets() {return bullets;}

}