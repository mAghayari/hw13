package dao;

import dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EmployeeDao {
    public List<Employee> getLastFiveYearsHiredEmployees() {
        int year = getYear();
        Connection connection = Connector.getConnection();
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT* FROM `employee` WHERE employmentYear BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (year - 5));
            preparedStatement.setInt(2, (year));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employees.add(getEmployee(resultSet));
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Employee getEmployee(ResultSet resultSet) {
        Employee employee = new Employee();
        try {
            employee.setEmployeeNumber(resultSet.getInt("employeeNumber"));
            employee.setFirstName(resultSet.getString("firstName"));
            employee.setLastName(resultSet.getString("lastName"));
            employee.setSalary(resultSet.getInt("salary"));
            employee.setHiringYear(resultSet.getInt("employmentYear"));
            employee.setLastFiveYearsSalary(resultSet.getInt("lastFiveYearsSalary"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public  int getYear(){
        Date now = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran"));
        cal.setTime(now);
        return cal.get(Calendar.YEAR);
    }
}