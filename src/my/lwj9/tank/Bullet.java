package my.lwj9.tank;

import java.awt.Color;
import java.awt.Graphics;
import static my.lwj9.tank.Main.FPS;

public class Bullet {
	private static final int SPEED = 600 / FPS;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private int x, y;
	private Dir dir;

	protected boolean live = true;
	TankFrame tf = null;

	public Bullet(int x, int y, Dir dir, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		if (!live) {
			tf.bullets.remove(this);
		}
		switch (dir) {
			case LEFT:
				g.drawImage(ResourceMgr.bulletL, x, y, null);
				break;

			case UP:
				g.drawImage(ResourceMgr.bulletU, x, y, null);
				break;

			case RIGHT:
				g.drawImage(ResourceMgr.bulletR, x, y, null);
				break;

			case DOWN:
				g.drawImage(ResourceMgr.bulletD, x, y, null);
				break;
		}
		move();
	}

	private void move() {

		switch (dir) {
			case LEFT:
				x -= SPEED;
				break;
			case UP:
				y -= SPEED;
				break;
			case RIGHT:
				x += SPEED;
				break;
			case DOWN:
				y += SPEED;
				break;
		}
		if (x < 0 || y < 10 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
			live = false;
	}
}
