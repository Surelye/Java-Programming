package task7;

import java.sql.*;

public class C7 {

    private void processTables(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("""
                SELECT employeeName, departmentName, location
                FROM departmentEmployee as t1, departmentLocation as t2
                WHERE t1.departmentId = t2.departmentId;
                """);
        while (resultSet.next()) {
            String employeeName = resultSet.getString(1);
            String departmentName = resultSet.getString(2);
            String departmentLocation = resultSet.getString(3);
            System.out.printf("> Данные о сотруднике{имя: %s, отдел: %s, расположение: %s}%n"
                    .formatted(employeeName, departmentName, departmentLocation));
        }
    }

    private void dropTables(Statement statement) throws SQLException {
        statement.execute("""
                DROP TABLE departmentEmployee;
                """);
        statement.execute("""
                DROP TABLE departmentLocation;
                """);
    }

    private void initDepartmentEmployeeTable(Statement statement) throws SQLException {
        statement.execute("""
                CREATE TABLE departmentEmployee (
                    id SERIAL PRIMARY KEY,
                    employeeName VARCHAR(50) NOT NULL,
                    departmentId SMALLINT NOT NULL
                );
                """);
        statement.execute("""
                INSERT INTO departmentEmployee
                VALUES(0, 'Kirill', 1),
                    (1, 'Ivan', 1),
                    (2, 'Masha', 2);
                """);
    }

    private void initDepartmentLocationTable(Statement statement) throws SQLException {
        statement.execute("""
                CREATE TABLE departmentLocation (
                    departmentId SMALLINT,
                    departmentName VARCHAR(20),
                    location VARCHAR(50)
                );
                """);
        statement.execute("""
                INSERT INTO departmentLocation
                VALUES(1, 'IT', 'Saratov'),
                    (1, 'IT', 'California'),
                    (2, 'HR', 'Moscow');
                """);
    }

    public void run() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employees",
                "postgres", "admin"
        );
        Statement statement = connection.createStatement();
        initDepartmentEmployeeTable(statement);
        initDepartmentLocationTable(statement);
        processTables(statement);
        dropTables(statement);
    }

    public static void main(String[] args) throws SQLException {
        new C7().run();
    }
}
