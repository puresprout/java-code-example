package concurrency;

public class InfinityLoop3 extends Thread {
	private static int[] values; // 값 목록
	private static int value; // 복수개 쓰레드가 공유하는 변수
	private final int threadValue; // 쓰레드를 위한 고정 값

	public InfinityLoop3(int value) {
		threadValue = value;
	}

	public void run() {
		System.out.println(threadValue + " is started.");
		while (true) {
			value = threadValue;
			check(value);
		}
	}

	public void check(int v) {
		for (int i = 0; i < values.length; i++) {
			if (v == values[i]) {
				//System.out.println("same.");
				return;
			}
		}
		System.out.println("not same. " + v);
		System.exit(-1);
	}

	public static void main(String[] args) throws InterruptedException {
		int firstValue = 1000000001; // 10억 1원 
		int secondValue = 0; // 0원
		values = new int[] {firstValue, secondValue};

		for (int v : values) {
			new InfinityLoop3(v).start();
		}
	}
}
