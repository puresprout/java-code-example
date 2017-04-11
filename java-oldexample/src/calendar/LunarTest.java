package calendar;

public class LunarTest {

	public static void main(String[] args) {
		LunarCheck l = new LunarCheck();
		// 양력 날짜를 설정
		long cnt = l.countSolarDay(2008, 2, 1);
		// 음력날짜로 변환
		String lunar = l.countToDateForLunar(cnt);
		System.out.println("양력 2008/2/1 -> 음력 " + lunar);

		// 양력으로 설정하고, month는 1부터 시작
		LunarCalendar c = new LunarCalendar(2007, 2, 18);
		System.out.println("양력 2007/2/18 -> 음력 " + c.get(c.LUNAR_YEAR) + "/"
				+ c.get(c.LUNAR_MONTH) + "/" + c.get(c.LUNAR_DATE));
	}

}
