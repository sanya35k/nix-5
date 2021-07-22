package dao;

import models.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SolutionDao {
    private final Connection connection;
    private static final String INSERT = "INSERT INTO solutions (problem_id, cost) VALUES (?, ?)";

    public SolutionDao(Connection connection) {
        this.connection = connection;
    }

    public void add(List<Solution> solutions){
        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
            for (Solution solution : solutions) {
                statement.setInt(1, solution.getProblem_id());
                statement.setInt(2, solution.getCost());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException exception) { throw new RuntimeException(exception); }
    }
}