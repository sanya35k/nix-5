import controller.Controller;

public class Run {
    private static String name;
    private static String password;
    private static Long userId;

    public static void main(String[] args) {
       Controller controller = new Controller();
       controller.run(userId, name, password);
    }

    private static void init(String[] args) {
        name = args[0];
        password = args[1];
        userId = Long.parseLong(args[2]);
    }
}