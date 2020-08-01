package com.blasta.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blasta.screens.Play;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Blasta extends Game {

	public static Play play;

	public static OrthographicCamera cam;
	public static Viewport vp;
	public static SpriteBatch g;
	public static ShapeRenderer sr;

	public void create () {
		/**Util**/
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();

		vp = new ScreenViewport(cam);
		vp.apply();

		cam.position.set(vp.getWorldWidth()/2, vp.getWorldHeight()/2, 0);
		cam.zoom = 2.5f;

		g = new SpriteBatch();
		sr = new ShapeRenderer();

		/**Screens**/
		play = new Play();
		play.init();

		setScreen(play);

	}

	public void render() {
		update();
		this.getScreen().render(Gdx.graphics.getDeltaTime());
	}

	private void update() {
		cam.update();
		Gdx.gl.glClearColor(18 / 255, 16 / 255, 14 / 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		g.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);

		Vector3 mouse_unprojected = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(mouse_unprojected);
	}

	public void resize(int width, int height) {
		vp.update(width, height);
		this.getScreen().resize(width, height);
	}

	public void dispose() {
		this.getScreen().dispose();
	}
}