package csv;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by sunghyun on 2017. 2. 4..
 */
public class CsvDocumentBuilderTest {
    private CsvDocument document;

    @Before
    public void setUp() {
        CsvDocumentBuilder builder = CsvDocumentBuilder.newInstance();
        File file = new File(getClass().getClassLoader().getResource("test.csv").getFile().toString());
        document = builder.parse(file);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getRowsBy() {
        List<CsvDocument.Row> rows = document.getRowsBy(0, "Park Sung Hyun");
        assertThat(rows.size(), is(2));
        assertThat(rows.get(0).getColumnValue(0), is("Park Sung Hyun"));
        assertThat(rows.get(0).getColumnValue(1), is("010-2629-0741"));
        assertThat(rows.get(0).getColumnValue(2), is("Seongnam-city"));

        List<CsvDocument.Row> allRows = document.getRows();
        assertThat(allRows.get(0).getColumnValue(0), is("Park Sung Hyun"));

        rows.get(0).getColumnValues().set(0, "New Person");
        assertThat(rows.get(0).getColumnValues().get(0), not("Park Sung Hyun"));

        assertThat(allRows.get(0).getColumnValues().get(0), not("Park Sung Hyun"));

        rows = document.getRowsBy(0, "Park Sung Hyun");
        assertThat(rows.size(), is(1));
    }

    @Test
    public void getRowsBy2() {
        List<CsvDocument.Row> rows = document.getRowsBy("Seongnam-city");
        assertThat(rows.size(), is(1));
        assertThat(rows.get(0).getColumnValue(rows.get(0).getColumnIndex("name")), is("Park Sung Hyun"));
        assertThat(rows.get(0).getColumnValue(document.getColumnIndex("phone")), is("010-2629-0741"));
        assertThat(rows.get(0).getColumnValue(2), is("Seongnam-city"));
    }

    @Test
    public void getRowsBy3() {
        List<CsvDocument.Row> rows = document.getRowsBy("Park Sung Hyun");
        assertThat(rows.size(), is(2));
    }

    @Test
    public void getRowsBy4() {
        List<CsvDocument.Row> rows = document.getRowsBy("same");
        assertThat(rows.size(), is(1));
    }

    @Test
    public void getRows() {
        List<CsvDocument.Row> rows = document.getRows();
        assertThat(rows.get(4).getColumnValue(0), is(""));
        assertThat(rows.get(4).getColumnValue(1), is(""));
        assertThat(rows.get(4).getColumnValue(2), is(""));

        try {
            assertThat(rows.get(4).getColumnValue(3), is(""));
            fail("IndexOutOfBoundsException이 발생해야 함.");
        } catch (IndexOutOfBoundsException e) {
            // 예외 발생 확인
        }
    }

    @Test
    public void documentColumnName() {
        assertThat(document, hasColumnName("name"));
        assertThat(document, hasColumnName("phone"));
        assertThat(document, hasColumnName("address"));
    }

    public Matcher<CsvDocument> hasColumnName(String columnName) {
        return new TypeSafeMatcher<CsvDocument>() {
            @Override
            protected boolean matchesSafely(CsvDocument csvDocument) {
                return csvDocument.getColumnNames().contains(columnName);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
