package task7;

import java.sql.*;

public class A7 {

    private void createTable(Statement statement) throws SQLException {
        statement.execute("""
                CREATE TABLE employeesAge (
                    id SERIAL PRIMARY KEY,
                    employeeName VARCHAR(50) NOT NULL,
                    age SMALLINT NOT NULL
                );
                    """);
        statement.execute("""
                INSERT INTO employeesAge
                VALUES(0, 'Kirill', 18),
                    (1, 'Sasha', 20),
                    (2, 'Katya', 25),
                    (3, 'Sonya', 19),
                    (4, 'Artur', 23),
                    (5, 'Misha', 19),
                    (6, 'Vanya', 24);
                """);
    }

    private ResultSet filterOlderThan20(Statement statement) throws SQLException {
        return statement.executeQuery("""
                SELECT *
                FROM employeesAge
                WHERE age > 20
                """
        );
    }

    private void printResult(ResultSet resultSet) throws SQLException {
        int countRows = 0;
        System.out.println("> Сотрудники старше 20 лет: ");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            System.out.printf("> Сотрудник{id: %d, имя: %s, возраст: %s}%n", id, name, age);
            ++countRows;
        }
        if (countRows == 0) {
            System.out.println("> Сотрудников старше 20 лет нет.");
        }
    }

    private void dropEmployeesAgeTable(Statement statement) throws SQLException {
        statement.execute("""
                DROP TABLE employeesAge;
                """);
    }

    public void run() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employees",
                "postgres", "admin"
        );
        Statement statement = connection.createStatement();
        createTable(statement);
        ResultSet olderThan20 = filterOlderThan20(statement);
        printResult(olderThan20);
        dropEmployeesAgeTable(statement);
    }

    public static void main(String[] args) throws SQLException {
        new A7().run();
    }
}
