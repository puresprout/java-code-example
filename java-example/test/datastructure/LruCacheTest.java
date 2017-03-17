package datastructure;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by 1002703 on 2017. 1. 25..
 */
public class LruCacheTest {
    @Test
    public void lruTest() {
        LruCache<String, String> cache = new LruCache<>(3);
        cache.put("1", "one");
        cache.put("2", "two");
        cache.put("3", "three");

        for (Map.Entry e : cache.getAll()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        System.out.println();
        Iterator<Map.Entry<String, String>> iterator = cache.getAll().iterator();
        assertThat(iterator.next().getKey(), is("1"));
        assertThat(iterator.next().getKey(), is("2"));
        assertThat(iterator.next().getKey(), is("3"));

        cache.get("2");
        cache.get("2");
        cache.get("2");

        for (Map.Entry e : cache.getAll()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        System.out.println();
        iterator = cache.getAll().iterator();
        assertThat(iterator.next().getKey(), is("1"));
        assertThat(iterator.next().getKey(), is("3"));
        assertThat(iterator.next().getKey(), is("2"));

        cache.get("1");
        cache.get("1");
        cache.get("1");
        cache.get("1");

        for (Map.Entry e : cache.getAll()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        iterator = cache.getAll().iterator();
        assertThat(iterator.next().getKey(), is("3"));
        assertThat(iterator.next().getKey(), is("2"));
        assertThat(iterator.next().getKey(), is("1"));

        cache.put("4", "four");
        assertThat(cache.get("3"), is(nullValue()));
    }
}
