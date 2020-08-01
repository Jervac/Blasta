package com.blasta.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.blasta.core.Blasta;
import com.blasta.entities.Barrier;
import com.blasta.entities.Renderable;
import com.blasta.entities.bullets.Bullet;
import com.blasta.entities.bullets.Pellet;

import java.util.ArrayList;
import java.util.Random;

public class Invader  extends Renderable{

    public Invader(Vector2 pos, Vector2 size, float speed) {
        super(pos, size, speed);
    }

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    int health = 3;

    public boolean right = true;

    boolean shot = false;

    Random rand = new Random();

    public void update() {

        if(!shot && this.isAlive()) {
            shot = true;
            Timer.schedule(new Timer.Task() {
                public void run() {
                    shot = false;
                    bullets.add(new Pellet(new Vector2(pos.x, pos.y), new Vector2(10, 10), new Vector2(0, -300), 1, false));
                }
            }, rand.nextInt(30 - 6) + 6);
        }

        for(Bullet b: bullets)
            b.update();

        if(alive) {
            if(pos.y <= -370) {
                System.out.println("Your pwned like a noob");
                Gdx.app.exit();
            }

            if (right)
                pos.x += speed * Gdx.graphics.getDeltaTime();

            if (!right)
                pos.x -= speed * Gdx.graphics.getDeltaTime();



            if (pos.x >= 620 && right) {
                Blasta.play.moveDown();
            }

            if (pos.x <= -380 && !right) {
                Blasta.play.moveDown();
            }

            if (health <= 0)
                alive = false;

            for (Barrier b : Blasta.play.getBarriers()) {
                if (b.isAlive()) {
                    if (this.getBounds().intersects(b.getBounds())) {
                        b.setAlive(false);
                        this.setAlive(false);
                    }
                }
            }

            for(Bullet b: Blasta.play.getPlayer().getBullets()) {
                if(b.alive == true && b.getBounds().intersects(this.getBounds())) {
                    this.alive = false;
                    b.alive = false;
                }
            }
        }
    }

    public void render() {
        if(this.isAlive()) {
            for (Bullet b : bullets)
                b.render();
        }

        if(alive) {
            Blasta.sr.setColor(Color.GREEN);
            Blasta.sr.begin(ShapeRenderer.ShapeType.Filled);
            Blasta.sr.rect(pos.x, pos.y, size.x, size.y);
            Blasta.sr.end();
        }
    }

    public void dispose() {
    }

}