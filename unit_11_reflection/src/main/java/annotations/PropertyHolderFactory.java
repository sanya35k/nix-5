package annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyHolderFactory {
    public <T> T create(Class<T> clazz){
        T object;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
            ReadFile readFile = new ReadFile();
            Properties properties = readFile.readFile("app.properties");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                    String props = properties.getProperty(propertyKey.value());
                    field.setAccessible(true);
                    scopeObject(object, field, props);
                }
            }
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void scopeObject(Object object, Field field, String props) {
        Class<?> type = field.getType();
        try {
            if (type == String.class) field.set(object, props);
            else if (type.isEnum()) field.set(object, Enum.valueOf((Class<Enum>) type, props));
            else if (type == byte.class || type == Byte.TYPE) field.set(object, Byte.parseByte(props));
            else if (type == int.class || type == Integer.TYPE) field.set(object, Integer.parseInt(props));
            else if (type == double.class || type == Double.TYPE) field.set(object, Double.parseDouble(props));
            else if (type == short.class || type == Short.TYPE) field.set(object, Short.parseShort(props));
            else if (type == long.class || type == Long.TYPE) field.set(object, Long.parseLong(props));
            else if (type == float.class || type == Float.TYPE) field.set(object, Float.parseFloat(props));
            else if (type == boolean.class || type == Boolean.TYPE) field.set(object, Boolean.parseBoolean(props));
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}