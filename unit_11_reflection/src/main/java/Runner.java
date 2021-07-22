import annotations.AppProperties;
import annotations.PropertyHolderFactory;

public class Runner {
    public static void main(String[] args) {
        PropertyHolderFactory propertyHolderFactory = new PropertyHolderFactory();
        AppProperties appProperties = propertyHolderFactory.create(AppProperties.class);
        System.out.println(appProperties);
    }
}