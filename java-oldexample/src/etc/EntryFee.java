package etc;

import java.math.BigDecimal;

public class EntryFee {

	public static void main(String[] args) {
		//final int totalFee = 45000;
		final int totalFee = 65000;
		
		for (int i = 1; i <= 30; i++) {
			int entryFee = totalFee / i;
			BigDecimal v = new BigDecimal(entryFee).setScale(-3, BigDecimal.ROUND_CEILING);

			System.out.println(totalFee + " / " + i + " = " + entryFee + " 백단위 올림 " + v.intValue());
		}
	}

}
