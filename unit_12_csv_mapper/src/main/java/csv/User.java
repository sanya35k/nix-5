package csv;

public class User {
    @PropertyKey("id")
    int id;
    @PropertyKey("name")
    String name;
    @PropertyKey("lastName")
    String lastName;
    @PropertyKey("age")
    int age;
    @PropertyKey("gender")
    String gender;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}