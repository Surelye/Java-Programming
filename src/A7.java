package task7;

import java.sql.*;

public class A7 {

    private void printDepartments(ResultSet resultSet) throws SQLException {
        System.out.println("> Отделы и их средние зарплаты: ");
        while (resultSet.next()) {
            String departmentName = resultSet.getString(1);
            int averageSalary = resultSet.getInt(2);
            System.out.printf("> Отдел{наименование: %s, средняя зарплата: %d}%n"
                    .formatted(departmentName, averageSalary));
        }
    }

    public void run() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/employees",
                "postgres", "admin"
        );
        Statement statement = connection.createStatement();
        statement.execute("""
                CREATE TABLE departmentSalary (
                    id SERIAL PRIMARY KEY,
                    employeeName VARCHAR(50) NOT NULL,
                    departmentName VARCHAR(20) NOT NULL,
                    salary INTEGER NOT NULL
                );
                    """);
        statement.execute("""
                INSERT INTO departmentSalary
                VALUES(0, 'Kirill', 'IT', 30000),
                    (1, 'Ivan', 'IT', 20000),
                    (2, 'Masha', 'HR', 40000),
                    (3, 'Sonya', 'HR', 35000)
                """);
        ResultSet departmentsWithAverageSalary = statement.executeQuery("""
                SELECT departmentName, AVG(salary)
                FROM departmentSalary
                GROUP BY departmentName
                """
        );
        printDepartments(departmentsWithAverageSalary);
        statement.execute("""
                DROP TABLE departmentSalary;
                """);
    }

    public static void main(String[] args) throws SQLException {
        new A7().run();
    }
}
