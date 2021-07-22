package annotations;

public class AppProperties {
    @PropertyKey("name")
    private String name;
    @PropertyKey("lastName")
    private String lastName;
    @PropertyKey("age")
    private int age;
    @PropertyKey("gender")
    private String gender;
    @PropertyKey("connections.limit")
    private int limit;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}' +
                "\nconnections.limit=" + limit;
    }
}