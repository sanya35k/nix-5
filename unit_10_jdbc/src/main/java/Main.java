import connection.ConnectionWithMySql;

public class Main {
    public static void main(String[] args) {
        ConnectionWithMySql connectionWithMySql = new ConnectionWithMySql();
        connectionWithMySql.run();
    }
}