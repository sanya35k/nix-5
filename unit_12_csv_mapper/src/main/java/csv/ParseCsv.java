package csv;

import java.util.Scanner;

public class ParseCsv {
    private static final String SEPARATOR = "--------------------------------------------------------------------------------------------------------";
    public void parseCSV(){
        System.out.println("Enter the path to the csv file for parse. For example,\nunit_12_csv_mapper/file.csv\nor\nunit_12_csv_mapper/file1.csv\n" + SEPARATOR);
        ParserCsv parser;
        Scanner s = new Scanner(System.in);
        parser = new ParserCsv(s.nextLine());
        parser.parse();
        MapperCsv<User> mapper = new MapperCsv<>();
        mapper.create(User.class, parser).forEach(System.out::println);
    }
}