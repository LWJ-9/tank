package my.lwj9.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1360366976926371951L;
	int x = 200, y = 200;
	Dir dir = Dir.DOWN;
	private static final int SPEED = 10;

	public TankFrame() {
		super();
		setSize(800, 600);
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

	@Override
	public void paint(Graphics g) {

		g.fillRect(x, y, 50, 50);
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
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			// System.out.println(dir);
			if (bL)
				dir = Dir.LEFT;
			if (bR)
				dir = Dir.RIGHT;
			if (bU)
				dir = Dir.UP;
			if (bD)
				dir = Dir.DOWN;

		}
	}
}