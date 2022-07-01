package my.lwj9.tank;

public class Main {

	final static int FPS = 60;

	public static void main(String[] args) throws InterruptedException {

		TankFrame tf = new TankFrame();

		// initialize tank
		for (int i = 0; i < 5; i++) {
			tf.tanks.add(new Tank(50 + i * 80, 100, Dir.DOWN, Group.BAD, tf));
		}

		tf.setVisible(true);
		while (true) {
			Thread.sleep(1000 / FPS);
			tf.repaint();
		}
	}
}
