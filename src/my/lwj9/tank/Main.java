package my.lwj9.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		TankFrame f = new TankFrame();
		f.setVisible(true);
		while (true) {
			Thread.sleep(16);
			f.repaint();
		}
	}
}
