package com.blasta.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.blasta.core.Blasta;
import com.blasta.entities.Barrier;
import com.blasta.entities.Player;
import com.blasta.entities.enemies.Invader;

import java.util.ArrayList;

public class Play implements Screen{

    ArrayList<Barrier> barriers = new ArrayList<Barrier>();
    ArrayList<Invader> invaders = new ArrayList<Invader>();

    Player player;

    public void init() {
        barriers.add(new Barrier(new Vector2((16 * 6) + 000, -270), new Vector2(6, 2), 3));
        barriers.add(new Barrier(new Vector2((16 * 6) + 200, -270), new Vector2(6, 2), 3));
        barriers.add(new Barrier(new Vector2((16 * 6) + 400, -270), new Vector2(6, 2), 3));
        barriers.add(new Barrier(new Vector2((16 * 6) - 200, -270), new Vector2(6, 2), 3));
        barriers.add(new Barrier(new Vector2((16 * 6) - 400, -270), new Vector2(6, 2), 3));

        player = new Player(new Vector2(((16 * 6) + 000) + (16 * 4)/2 + 16, -320), new Vector2(16 * 4, 20), 300);

        for(int i = 0; i < 10; ++i) {
            for(int j=0; j<4; ++j) {
                invaders.add(new Invader(new Vector2(100 * i, 400 + (100 * j)), new Vector2(50, 50), 60));
            }
        }
    }

    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.out.println("Exiting game...");
            Gdx.app.exit();
        }

        for(Barrier b: barriers)
            b.update();

        player.update();

        for(Invader i: invaders)
            i.update();
    }

    public void render(float delta) {
        update();

        Blasta.sr.begin(ShapeRenderer.ShapeType.Filled);
        Blasta.sr.rect((16 * 6) + 400 + 100, -300, 50, Gdx.graphics.getHeight());
        Blasta.sr.end();

        for(Invader i: invaders)
            i.render();

        for(Barrier b: barriers)
            b.render();

        player.render();
    }

    public void pause() {}

    public void resume() {}

    public void hide() {}

    public void show() {}

    public void resize(int width, int height) {}

    public void dispose() {
        player.dispose();
        for(Barrier b: barriers)
            b.dispose();
        for(Invader i: invaders)
            i.dispose();
    }

    public ArrayList<Barrier> getBarriers() {return barriers;}

    public Player getPlayer() {return player;}

    public void moveDown() {
        for(Invader i: invaders) {
            if(i.right)
                i.right = false;
            else if(!i.right)
                i.right = true;

            i.pos.y -= 30;
        }
    }

}