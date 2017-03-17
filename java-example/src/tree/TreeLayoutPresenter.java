package tree;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by sunghyun on 2016. 12. 20..
 * <p>
 * TODO implementing.
 */
public class TreeLayoutPresenter<E> {
    private TreeSet<E> treeSet;

    public TreeLayoutPresenter(TreeSet<E> treeSet) {
        this.treeSet = treeSet;
    }

    public void printPosition() {
        Iterator<E> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();

            System.out.println(e);
        }
    }
}
