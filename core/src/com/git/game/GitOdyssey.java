/**
 * Name: Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Main game class
 */

package com.git.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GitOdyssey extends Game implements ApplicationListener {
	SplashScreen splashScreen;
	public static Skin gameSkin;

	@Override
	public void create () {
		splashScreen = new SplashScreen();
		gameSkin = new Skin(Gdx.files.internal("Skin/uiskin.json"));
		setScreen(new LearningLevel(this));
	}

	public void render() {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		getScreen().resize(width, height);
	}

	public Screen getScreen() {
		return (super.getScreen());
	}


	public void dispose () {}
}