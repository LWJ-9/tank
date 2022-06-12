package my.lwj9.tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		TankFrame f = new TankFrame();
		f.setVisible(true);
		System.out.println(0 == 9);
		while (true) {
			Thread.sleep(50);
			f.repaint();
		}
	}
}