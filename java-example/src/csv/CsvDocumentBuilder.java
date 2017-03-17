package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunghyun on 2017. 2. 4..
 */
public class CsvDocumentBuilder {
    public static CsvDocumentBuilder newInstance() {
        // 환경변수나 옵션에 따라 현 객체의 동작 방식을 바꾸는 설정 로직을 넣을 수 있다.
        return new CsvDocumentBuilder();
    }

    protected CsvDocumentBuilder() {
    }

    public CsvDocument parse(File file) {
        CsvDocument document = new CsvDocument();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHead = true;

            List<CsvDocument.Row> rows = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                if (isHead) {
                    isHead = false;

                    List<String> columnNames = new ArrayList<>();
                    for (int i = 0; i < tokens.length; i++) {
                        columnNames.add(tokens[i].trim());
                    }

                    document.setColumnNames(columnNames);

                    continue;
                }

                rows.add(newRow(document, tokens));
            }

            document.setRows(rows);

            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private CsvDocument.Row newRow(CsvDocument document, String[] tokens) {
        CsvDocument.Row row = document.new Row();

        List<String> values = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            values.add(tokens[i].trim());
        }

        // 헤더 컬럼개수보다 행의 컬럼이 모자라면 모자른 수만큼 빈값으로 채운다.
        for (int i = tokens.length; i < document.getColumnCount(); i++) {
            values.add("");
        }

        row.setColumnValues(values);

        return row;
    }
}
