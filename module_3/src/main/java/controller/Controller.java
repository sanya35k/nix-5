package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.hibernate.CreateNewOperation;
import service.jdbc.ExportToCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Controller {
    private static final String APP = "**************************************PERSONAL FINANCE MANAGEMENT***************************************";

    private static final String EXCEPTION = "You entered invalid characters!";
    private static final String LOGGER_EXCEPTION = "Entered invalid characters!";

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void run(Long userID, String username, String password) {
        LOGGER_INFO.info("Start app");
        System.out.print(APP +
                "\n1.Create new operation\n" +
                "2.Export operations to csv\n" +
                "Exit(0)\n" +
                "Select an action: ");
        try {
            int operation = Integer.parseInt(bufferedReader.readLine());
            switch (operation){
                case 1:
                    createOperation(userID, username, password);
                    break;
                case 2:
                    exportCSV(username,password);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println(EXCEPTION);
            }
        } catch (IOException e) {
            LOGGER_ERROR.error(LOGGER_EXCEPTION);
            System.out.println(EXCEPTION);
        }
    }

    private void createOperation(Long userId, String username, String password) {
        try {
            LOGGER_INFO.info("Start create");
            System.out.println("Enter balance id");
            Long balanceId = Long.parseLong(bufferedReader.readLine());
            System.out.println("Enter operation`s amount(positive number for income or negative number for consumption):");
            Long amount = Long.parseLong(bufferedReader.readLine());
            if (amount == 0) {
                throw new RuntimeException("Failed! Amount = 0!");
            }
            System.out.println("Enter description:");
            String description = bufferedReader.readLine();
            Instant timestamp = Instant.now();
            CreateNewOperation createNewOperation = new CreateNewOperation();
            createNewOperation.addOperation(userId, username, password, amount, description, timestamp, balanceId);
            LOGGER_INFO.info("Finish create");
        } catch (IOException e) {
            LOGGER_ERROR.error("Create failed");
            System.out.println(EXCEPTION);
        }
    }

    private void exportCSV(String username, String password) {
        try {
            LOGGER_INFO.info("Start export to CSV");
            System.out.println("Enter balance id");
            Long balanceId = Long.parseLong(bufferedReader.readLine());
            System.out.println("Enter from date. For example, 01/12/21 59:59");
            String dateFrom = bufferedReader.readLine();
            Date dateF = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateFrom);
            Instant from = dateF.toInstant();
            System.out.println("Enter to date. For example, 02/12/21 59:59");
            String dateTo = bufferedReader.readLine();
            Date dateT = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateTo);
            Instant to = dateT.toInstant();
            ExportToCSV exportToCSV = new ExportToCSV();
            exportToCSV.exportToCSV(username, password, balanceId,from,to);
            LOGGER_INFO.info("Finish export to CSV");
        } catch (IOException | ParseException e) {
            LOGGER_ERROR.error("Failed export to CSV");
            System.out.println("Failed export to CSV!");
        }
    }
}