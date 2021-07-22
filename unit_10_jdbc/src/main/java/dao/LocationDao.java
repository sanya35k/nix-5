package dao;

import models.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {
    private final Connection connection;
    private static final String ALL = "SELECT * FROM locations";

    public LocationDao(Connection connection) {
        this.connection = connection;
    }

    public List<Location> allLocations(){
        List<Location> locations = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL);
            while (resultSet.next()){
                Location location = new Location();
                location.setId(resultSet.getInt(1));
                location.setName(resultSet.getString(2));
                locations.add(location);
            }
        } catch (SQLException exception) { throw new RuntimeException(exception); }
        return locations;
    }
}