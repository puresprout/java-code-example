package concurrency;

public class InfinityLoop {

	private static boolean stopRequested;

	public static void main(String[] args) {
		int i = 0;

		while (!stopRequested) {
			i++;
		}
	}

}
