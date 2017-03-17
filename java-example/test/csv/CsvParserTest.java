package csv;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by 1002703 on 2017. 1. 24..
 */
public class CsvParserTest {
    @Test
    public void parse() {
        String fileName = getClass().getClassLoader().getResource("test.csv").getFile().toString();

        CsvParser parser = new CsvParser();
        List<Address> addresses = parser.parse(Address.class, fileName);

        assertThat(addresses.size(), is(10));

        Address address = addresses.get(0);
        assertThat(address.name, is("Park Sung Hyun"));
        assertThat(address.phone, is("010-2629-0741"));
        assertThat(address.address, is("Seongnam-city"));

        address = addresses.get(1);
        assertThat(address.name, is("Hong Kil Dong"));
        assertThat(address.phone, is("010-1234-5678"));
        assertThat(address.address, is("Heaven"));

        address = addresses.get(2);
        assertThat(address.name, is(nullValue()));
        assertThat(address.phone, is(nullValue()));
        assertThat(address.address, is(nullValue()));

        address = addresses.get(3);
        assertThat(address.name, is(nullValue()));
        assertThat(address.phone, is(nullValue()));
        assertThat(address.address, is(nullValue()));

        address = addresses.get(4);
        assertThat(address.name, is(nullValue()));
        assertThat(address.phone, is(nullValue()));
        assertThat(address.address, is(nullValue()));

        address = addresses.get(5);
        assertThat(address.name, is("n"));
        assertThat(address.phone, is(nullValue()));
        assertThat(address.address, is(nullValue()));

        address = addresses.get(6);
        assertThat(address.name, is(nullValue()));
        assertThat(address.phone, is("p"));
        assertThat(address.address, is(nullValue()));

        address = addresses.get(7);
        assertThat(address.name, is(nullValue()));
        assertThat(address.phone, is(nullValue()));
        assertThat(address.address, is("a"));
    }

    public static class Address {
        public String name;
        public String phone;
        public String address;
    }
}
