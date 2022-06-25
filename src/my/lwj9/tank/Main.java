package my.lwj9.tank;

public class Main {

	final static int FPS = 60;

	public static void main(String[] args) throws InterruptedException {

		TankFrame f = new TankFrame();
		f.setVisible(true);
		while (true) {
			Thread.sleep(1000 / FPS);
			f.repaint();
		}
	}
}
