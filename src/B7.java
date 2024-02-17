package task7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class B7 {

    public void run() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employees",
                "postgres", "admin"
        );
        Statement statement = connection.createStatement();
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

    public static void main(String[] args) throws SQLException {
        new B7().run();
    }
}
