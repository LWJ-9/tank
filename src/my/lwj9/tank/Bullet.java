package my.lwj9.tank;

//import java.awt.Color;
import java.awt.Graphics;
import static my.lwj9.tank.Main.FPS;
import java.awt.Rectangle;

public class Bullet {
	private static final int SPEED = 600 / FPS;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private int x, y;
	private Dir dir;

	protected boolean living = true;
	TankFrame tf = null;
	private Group group = Group.BAD;

	public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		if (!living) {
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
			living = false;
	}

	public void collideWith(Tank tank) {
		if (this.group == tank.getGroup())
			return;
		Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
		Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		if (rect1.intersects(rect2)) {
			tank.die();
			this.die();
			int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
			int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
			tf.explodes.add(new Explode(eX, eY, tf));
		}
	}

	private void die() {
		this.living = false;
	}
}
