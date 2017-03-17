package tree;

import org.junit.Test;

import java.util.TreeSet;

/**
 * Created by sunghyun on 2016. 12. 20..
 * <p>
 * TODO implementing.
 */
public class TreeLayoutPresenterTest {
    @Test
    public void printPosition() throws Exception {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("p");
        treeSet.add("m");
        treeSet.add("h");
        treeSet.add("s");
        treeSet.add("u");
        treeSet.add("k");
        treeSet.add("q");
        treeSet.add("a");
        treeSet.add("n");
        treeSet.add("g");
        treeSet.add("c");
        treeSet.add("e");

        TreeLayoutPresenter<String> presenter = new TreeLayoutPresenter<>(treeSet);
        presenter.printPosition();
    }
}