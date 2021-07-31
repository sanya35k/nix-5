package service.jdbc;

import com.opencsv.CSVWriter;
import dto.Dto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ExportToCSV {
    public void exportToCSV(String username, String password, Long balanceId, Instant from, Instant to) {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        List<Dto> operations = new ArrayList<>();
        String[] header = new String[]{"Id", "Balance id", "Amount", "Description", "Timestamp"};
        List<String[]> csvData = new ArrayList<>();
        csvData.add(header);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             CSVWriter writer = new CSVWriter(new FileWriter("operations.csv",true))) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operation WHERE operation.balance_id = ? AND operation.timestamp BETWEEN ? AND ?");

            statement.setLong(1, balanceId);
            statement.setTimestamp(2, Timestamp.from(from));
            statement.setTimestamp(3, Timestamp.from(to));

            ResultSet result = statement.executeQuery();

            while (result.next()){
                Dto operation = new Dto();
                operation.setId(result.getLong(1));
                operation.setBalanceId(result.getLong(2));
                operation.setAmount(result.getLong(3));
                operation.setDescription(result.getString(4));
                operation.setTimestamp(result.getTimestamp(5).toInstant());
                operations.add(operation);
            }

            for(Dto o : operations){
                csvData.add(toStringArray(o));
            }
            writer.writeAll(csvData);
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Exception! There is no possibility of exporting to a csv file!");
        }
    }

    private Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("mysql.properties")){
            properties.load(in);
        } catch (IOException e){throw new UncheckedIOException(e);}
        return properties;
    }

    private String[] toStringArray(Dto operation){
        String[] strings = new String[5];
        strings[0] = String.valueOf(operation.getId());
        strings[1] = String.valueOf(operation.getAmount());
        strings[2] = String.valueOf(operation.getDescription());
        strings[3] = operation.getTimestamp().toString();
        strings[4] = String.valueOf(operation.getBalanceId());
        return strings;
    }
}