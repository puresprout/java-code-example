package concurrency;

public class InfinityLoop2 extends Thread {
	private static long[] values; // 값 목록
	private static long value; // 복수개 쓰레드가 공유하는 변수
	private final long threadValue; // 쓰레드를 위한 고정 값

	public InfinityLoop2(long value) {
		threadValue = value;
	}

	public void run() {
		System.out.println(threadValue + " is started.");
		while (true) {
			value = threadValue;
			check(value);
		}
	}

	public void check(long v) {
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
		long firstValue = 1000000000000000001L; // 100갱 1원
		long secondValue = 0L; // 0원
		values = new long[] {firstValue, secondValue};

		for (long v : values) {
			new InfinityLoop2(v).start();
		}
	}
}
