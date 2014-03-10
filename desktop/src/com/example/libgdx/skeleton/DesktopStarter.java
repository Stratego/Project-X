package com.example.libgdx.skeleton;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopStarter {
	private static final short FULLSCREEN = 0;
	private static final short GALAXY_S3 = 1;
	private static final short NEXUS_ONE = 2;
	private static final short EVO3D = 3;
	private static final short BIG_WINDOW = 4;
	private static final short SMALL_PHONE = 5;

	// choose size here
	private static final short DEBUG_SIZE = 1;

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.useGL20 = true;
		if (DEBUG_SIZE == FULLSCREEN) {
			cfg.width = 1920;
			cfg.height = 1080;
			cfg.fullscreen = true;
		} else if (DEBUG_SIZE == GALAXY_S3) {
			cfg.width = 1280;
			cfg.height = 720;
			cfg.fullscreen = false;
		} else if (DEBUG_SIZE == EVO3D) {
			cfg.width = 960;
			cfg.height = 540;
			cfg.fullscreen = false;
		} else if (DEBUG_SIZE == NEXUS_ONE) {
			cfg.width = 800;
			cfg.height = 480;
			cfg.fullscreen = false;
		} else if (DEBUG_SIZE == SMALL_PHONE) {
			cfg.width = 480;
			cfg.height = 320;
			cfg.fullscreen = false;
		} else if (DEBUG_SIZE == BIG_WINDOW) {
			cfg.width = 1440;
			cfg.height = 900;
			cfg.fullscreen = false;
		}
		cfg.resizable = false;
		cfg.vSyncEnabled = false;
		new LwjglApplication(new SkeletonMain(), cfg);
	}
}