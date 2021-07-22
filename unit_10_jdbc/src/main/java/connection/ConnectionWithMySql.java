package connection;

import dao.LocationDao;
import dao.ProblemDao;
import dao.RouteDao;
import dao.SolutionDao;
import models.Problem;
import models.Route;
import models.Solution;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionWithMySql {
    public void run(){
        Properties properties = loadProperties();
        String url = properties.getProperty("url");

        try(Connection connection = DriverManager.getConnection(url, properties)){
            LocationDao locationDao = new LocationDao(connection);
            ProblemDao problemDao = new ProblemDao(connection);
            RouteDao routeDao = new RouteDao(connection);
            SolutionDao solutionDao = new SolutionDao(connection);

            int cities = locationDao.allLocations().size();

            List<Problem> problems = problemDao.allProblems();

            int[][] matrix = new int[cities][cities];
            List<Route> routes = routeDao.allRoutes();
            int i, j;
            for(Route r : routes){
                i = r.getFrom_id()-1;
                j = r.getTo_id()-1;
                matrix[i][j] = r.getCost();
            }

            List<Solution> solutions = new ArrayList<>();
            ConnectionWithMySql distances = new ConnectionWithMySql();
            int[][] results = distances.distance(cities, matrix);
            for (Problem p : problems){
                int id = p.getId();
                int distance = results[p.getFromId()-1][p.getToId()-1];
                Solution solution = new Solution();
                solution.setProblem_id(id);
                solution.setCost(distance);
                solutions.add(solution);
            }
            solutionDao.add(solutions);
        } catch (SQLException exception) { throw new RuntimeException(exception); }
    }

    private Properties loadProperties() {
        Properties props = new Properties();

        try(InputStream input = ConnectionWithMySql.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException exception) { throw new UncheckedIOException(exception); }
        return props;
    }

    public int[][] distance(int numOfCities, int[][] routes){
        int max = 200000;
        int[][] distance = new int[numOfCities][numOfCities];

        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                if (routes[i][j] == 0 && i != j)
                    distance[i][j] = max;
                else
                    distance[i][j] = routes[i][j];
            }
        }

        for (int i = 0; i < numOfCities; i++) {
            for (int j = 0; j < numOfCities; j++) {
                for (int k = 0; k < numOfCities; k++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        return distance;
    }
}