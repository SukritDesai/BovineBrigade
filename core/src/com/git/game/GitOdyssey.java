package com.git.game;

import com.badlogic.gdx.*;


public class GitOdyssey extends Game implements ApplicationListener {
	SplashScreen splashScreen;

	@Override
	public void create () {
		splashScreen = new SplashScreen();
		setScreen(splashScreen);
	}

	@Override
	public void render () {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		getScreen().resize(width, height);
	}

// Other ApplicationListener methods (pause, resume, dispose) can be left empty

	public void setScreen(Screen screen) {
		if (getScreen() != null) {
			getScreen().dispose();
		}
		screen.show();
		super.setScreen(screen);
	}

	public Screen getScreen() {
		return (super.getScreen());
	}
}
