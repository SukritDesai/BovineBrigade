package com.git.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * The main class that launches the game
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Sukrit Desai
 * @version 05.29.23
 * Time Spent: 10 minutes
 */
public class DesktopLauncher {

	/**
	 * The main method that launches the game
	 *
	 * @param arg the command line arguments
	 */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Git Odyssey");
		config.setWindowedMode(1280, 720);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setWindowedMode(1280, 720);
		new Lwjgl3Application(new GitOdyssey(), config);
	}
}
