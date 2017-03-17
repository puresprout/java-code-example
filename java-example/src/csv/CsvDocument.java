package csv;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sunghyun on 2017. 2. 4..
 */
public class CsvDocument {
    private List<String> columnNames;
    private List<Row> rows;

    void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public int getColumnIndex(String columnName) {
        return columnNames.indexOf(columnName);
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Row> getRowsBy(String columnValue) {
        int size = rows.get(0).getColumnValues().size();

        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Row> list = getRowsBy(i, columnValue);

            list.forEach(row -> {
                if (!rows.contains(row)) rows.add(row);
            });
        }

        return rows;
    }

    public List<Row> getRowsBy(int columnIndex, String columnValue) {
        return rows.stream()
                .filter(row -> row.getColumnValues().size() <= columnIndex ? false : row.getColumnValues().get(columnIndex).equals(columnValue))
                .collect(Collectors.toList());
    }

    class Row {
        private List<String> columnValues;

        void setColumnValues(List<String> columnValues) {
            this.columnValues = columnValues;
        }

        @Deprecated
        public List<String> getColumnValues() {
            return columnValues;
        }

        public String getColumnValue(int columnIndex) {
            return columnValues.get(columnIndex);
        }

        public int getColumnIndex(String columnName) {
            return columnNames.indexOf(columnName);
        }
    }
}
