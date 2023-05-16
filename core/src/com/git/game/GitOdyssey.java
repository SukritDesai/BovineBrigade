package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GitOdyssey extends Game {

	static public Skin gameSkin;

	public void create () {
		gameSkin = new Skin(Gdx.files.internal("uiskin.json"));
		this.setScreen(new TitleScreen(this));
	}

	public void render () {
		super.render();
	}


	public void dispose () {
	}
}