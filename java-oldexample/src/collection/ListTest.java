package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List l = new ArrayList();

		System.out.println(l.size());

		l.add(null);
		l.add(null);
		l.add("last");

		System.out.println(l.size());

		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
	}

}
