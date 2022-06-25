package my.lwj9.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 10;
	private boolean moving = false;
	private TankFrame tf = null;

	public boolean isMmoving() {
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
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);
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
		tf.bullets.add(new Bullet(this.x, this.y, this.dir));
	}
}
