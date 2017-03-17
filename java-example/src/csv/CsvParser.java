package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 컬럼값에 trim까지 적용했을 때 빈문자열이 되면 targetClass의 해당 필드는 null이 된다.
 * <p>
 * Created by puresprout on 2017. 1. 24..
 */
public class CsvParser<T> {
    public List<T> parse(Class<T> targetClass, String fileName) {
        List<T> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHead = true;
            Map<String, Integer> headerColumnMap = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                if (isHead) {
                    for (int i = 0; i < tokens.length; i++) {
                        String key = tokens[i].trim();
                        headerColumnMap.put(key, i);
                        //System.out.println(key + " " + headerColumnMap.get(key));
                    }

                    isHead = false;
                    continue;
                }

                T t = newObject(targetClass, headerColumnMap, tokens);
                list.add(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 자동으로 stream 닫힘

        return list;
    }

    private T newObject(Class<T> targetClass, Map<String, Integer> headerColumnMap, String[] tokens) {
        try {
            Class clazz = Class.forName(targetClass.getName());
            T obj = (T) clazz.newInstance();

            Field[] fields = clazz.getFields();
            mapFields(headerColumnMap, tokens, obj, fields);

            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    //  , ,  -> tokens: {" ", " ", " "}
    //  , , -> tokens: {" ", " "}
    // ,, -> tokens: {}
    // , -> tokens: {}
    // n -> tokens: {"n"}
    // ,p -> tokens: {"", "p"}
    // ,,p -> tokens: {"", "", "p"}
    private void mapFields(Map<String, Integer> headerColumnMap, String[] tokens, T obj, Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            Integer index = headerColumnMap.get(field.getName());

            if (index != null) {
                try {
                    String value = tokens[index].trim();
                    if ("".equals(value)) {
                        value = null;
                    }
                    field.set(obj, value);
                    //System.out.println(field.getName() + " " + value);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }
}
