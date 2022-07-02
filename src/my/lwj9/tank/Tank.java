package my.lwj9.tank;

// import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import static my.lwj9.tank.Main.FPS;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 300 / FPS;

	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private Random random = new Random();
	private boolean moving = true;
	private TankFrame tf = null;
	private boolean living = true;
	private Group group = Group.BAD;

	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.y = y;
		this.dir = dir;
		this.x = x;
		this.group = group;
		this.tf = tf;
		this.moving = group == Group.BAD ? true : false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
		if (this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();
		if (this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();
	}

	public void paint(Graphics g) {
		if (!living) {
			tf.tanks.remove(this);
		}
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

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void fire() {
		int bX = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
		int bY = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
		tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
	}

	public void die() {
		this.living = false;
	}

}
