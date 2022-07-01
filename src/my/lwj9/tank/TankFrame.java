package my.lwj9.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
import java.awt.Image;
import java.awt.Color;

public class TankFrame extends Frame {

	private static final long serialVersionUID = 1360366976926371951L;
	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

	public TankFrame() {
		super();
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");

		this.addKeyListener(new MykeyListener());

		addWindowListener(
				new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
	}

	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("bullets quantity: " + bullets.size(), 10, 60);
		g.setColor(c);

		myTank.paint(g);

		// ! java.util.ConcurrentModificationException
		// for (Bullet b : bullets) {
		// b.paint(g);
		// }

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}

		// for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
		// Bullet b = it.next();
		// if (!b.live) {
		// it.remove();
		// continue;
		// }
		// b.paint(g);
		// }

		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
	}

	class MykeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = true;
					break;
				case KeyEvent.VK_UP:
					bU = true;
					break;
				case KeyEvent.VK_RIGHT:
					bR = true;
					break;
				case KeyEvent.VK_DOWN:
					bD = true;
					break;
				case KeyEvent.VK_CONTROL:
					myTank.fire();
					break;
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;
				case KeyEvent.VK_CONTROL:
					myTank.fire();
					break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if (!bL && !bU && !bR && !bD)
				myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				// System.out.println(dir);
				if (bL)
					myTank.setDir(Dir.LEFT);
				if (bR)
					myTank.setDir(Dir.RIGHT);
				if (bU)
					myTank.setDir(Dir.UP);
				if (bD)
					myTank.setDir(Dir.DOWN);
			}

		}
	}
}