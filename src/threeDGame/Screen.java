package threeDGame;

import java.util.Random;

public class Screen extends Render {

	private Render test;
	private Render3D render;

	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		render = new Render3D(width, height);
		test = new Render(256, 256);
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt() * (random.nextInt(10) / 9);
		}
	}

	public void render(Game game) {
		for (int i = 0; i < Display.WIDTH * Display.HEIGHT; i++)
			pixels[i] = 0;
		
		render.floor(game);
		draw(render, 0, 0);
	}

}
