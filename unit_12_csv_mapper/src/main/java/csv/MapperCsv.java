package csv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MapperCsv<T> {
    public List<T> create(Class<T> o, ParserCsv parser) {
        ArrayList<T> res = new ArrayList<>();
        try {
            for (int i = 1; i <= parser.getSize(); i++) {
                T temp = (T) o.getDeclaredConstructor().newInstance();
                Field[] fields = o.getDeclaredFields();
                for (Field f :
                        fields) {
                    PropertyKey header = f.getAnnotation(PropertyKey.class);
                    if (header != null) {
                        String headerString = header.value();
                        scopeObject(f, parser.get(i, headerString), temp);
                    }
                }
                res.add(temp);
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void scopeObject(Field field, String value, T o) {
        Class fieldsType = field.getType();
        try {
            if (fieldsType.isAssignableFrom(byte.class)) {
                field.set(o, Byte.parseByte(value));
            } else if (fieldsType.isAssignableFrom(int.class)) {
                field.set(o, Integer.parseInt(value));
            } else if (fieldsType.isAssignableFrom(double.class)) {
                field.set(o, Double.parseDouble(value));
            } else if (fieldsType.isAssignableFrom(short.class)) {
                field.set(o, Float.parseFloat(value));
            } else if (fieldsType.isAssignableFrom(long.class)) {
                field.set(o, Long.parseLong(value));
            } else if (fieldsType.isAssignableFrom(char.class)) {
                field.set(o, value.charAt(0));
            } else {
                field.set(o, value);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Field not found!" + field.getName());
        }
    }
}