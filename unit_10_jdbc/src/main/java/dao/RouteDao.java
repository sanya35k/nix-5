package dao;

import models.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {
    private final Connection connection;
    private static final String ALL = "SELECT * FROM routes";

    public RouteDao(Connection connection) {
        this.connection = connection;
    }

    public List<Route> allRoutes(){
        List<Route> routes = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL);
            while (resultSet.next()){
                Route route = new Route();
                route.setId(resultSet.getInt(1));
                route.setFrom_id(resultSet.getInt(2));
                route.setTo_id(resultSet.getInt(3));
                route.setCost(resultSet.getInt(4));
                routes.add(route);
            }
        } catch (SQLException exception) { throw new RuntimeException(exception); }
        return routes;
    }
}