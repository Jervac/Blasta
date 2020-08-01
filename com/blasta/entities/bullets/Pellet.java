package com.blasta.entities.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.blasta.core.Blasta;
import com.blasta.entities.Barrier;
import com.blasta.entities.Brick;

public class Pellet extends Bullet{

    public Pellet(Vector2 pos, Vector2 size, Vector2 speed, int damage, boolean isPlayer) {
        super(pos, size, speed, damage, isPlayer);

    }

    public void render() {
        Timer.schedule(new Timer.Task() {
            public void run() {
                alive = false;
            }
        }, 10);


        if(alive) {
            Blasta.sr.setColor(Color.GREEN);
            Blasta.sr.begin(ShapeRenderer.ShapeType.Filled);
            Blasta.sr.rect(pos.x, pos.y, size.x, size.y);
            Blasta.sr.end();
        }
    }

    //to fix collision problem with breaking 2 blocks, get collision from precise enter of shot. for enemy and play do full bounds
    public void update() {
        if(alive) {
            for (Barrier b : Blasta.play.getBarriers()) {
                for (Brick brick : b.getBricks()) {
                    if (brick.isAlive()) {
                        if (this.getBounds().intersects(brick.getBounds())) {
                            brick.setHp(brick.getHp() - 1);
                            alive = false;
                        }
                    }
                }
            }
        }

        pos.x += speed.x * Gdx.graphics.getDeltaTime();
        pos.y += speed.y * Gdx.graphics.getDeltaTime();

    }

    public void dispose() {


    }
}