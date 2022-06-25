package my.lwj9.tank;

import java.awt.Color;
import java.awt.Graphics;
import static my.lwj9.tank.Main.FPS;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 300 / FPS;

	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private boolean moving = false;
	private TankFrame tf = null;

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public Tank(int x, int y, Dir dir, TankFrame tf) {
		super();
		this.y = y;
		this.dir = dir;
		this.x = x;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		switch (dir) {
			case LEFT:
				g.drawImage(ResourceMgr.tankL, x, y, null);
				break;

			case UP:
				g.drawImage(ResourceMgr.tankU, x, y, null);
				break;

			case RIGHT:
				g.drawImage(ResourceMgr.tankR, x, y, null);
				break;

			case DOWN:
				g.drawImage(ResourceMgr.tankD, x, y, null);
				break;

			default:
				break;
		}
		move();
	}

	private void move() {
		if (!moving)
			return;
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
	}

	public void fire() {
		int bX = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
		int bY = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
	}
}
