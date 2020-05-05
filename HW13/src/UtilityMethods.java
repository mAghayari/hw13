import dao.EmployeeDao;
import dto.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UtilityMethods {

    public static Map<Integer, List<Employee>> sortByEmploymentYear() {
        List<Employee> employees;
        EmployeeDao employeeDao = new EmployeeDao();
        employees = employeeDao.getLastFiveYearsHiredEmployees();
        Comparator<Employee> compareYearThenBySalary = Comparator
                .comparing(Employee::getEmploymentYear)
                .thenComparing(Employee::getIntSalaryRange);
        return employees.stream().sorted(compareYearThenBySalary).collect(Collectors.groupingBy(Employee::getEmploymentYear, HashMap::new, toList()));
    }

    public void printQuestion1(Map<Integer, Map<String, List<Employee>>> yearSalaryEmployeesMap) {
        System.out.println("Question 1:\n");
        System.out.printf("%-24s%-21s%-24s%-18s\n", "EmploymentYear", "SalaryRange", "EmployeeNumber", "FullName");
        System.out.println("----------------------------------------------------------------------------------------");
        yearSalaryEmployeesMap.keySet().forEach(i -> {
            System.out.printf(" %7d\n", i);
            System.out.println("________________________________________________________________________________________");
            yearSalaryEmployeesMap.get(i).keySet().stream().sorted(Comparator.comparingInt(String::length)).forEach(j -> {
                System.out.printf("%27s%-8s\n", " ", j);
                yearSalaryEmployeesMap.get(i).get(j).forEach(System.out::println);
                System.out.println("........................................................................................");
            });
            System.out.println("****************************************************************************************");
        });
        System.out.println("\n");
    }


    public void printQuestion4(Map<Integer, Map<String, List<Employee>>> yearSalaryEmployeesMap) {
        System.out.println("Question 4:\n");
        System.out.printf("%-24s%-21s%-24s%-25s%-32s\n", "EmploymentYear", "SalaryRange", "EmployeeNumber", "FullName", "last 5 Years Salary");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        yearSalaryEmployeesMap.keySet().forEach(i -> {
            System.out.printf(" %7d\n", i);
            System.out.println("_________________________________________________________________________________________________________________");
            yearSalaryEmployeesMap.get(i).keySet().stream().sorted(Comparator.comparingInt(String::length)).forEach(j -> {
                System.out.printf("%27s%-8s\n", " ", j);
                yearSalaryEmployeesMap.get(i).get(j).forEach(Employee::employeeToString);
                System.out.println(".................................................................................................................");
            });
            System.out.println("*****************************************************************************************************************");
        });
    }
    public void fillYearSalaryEmployeesMap(Map<Integer, List<Employee>> mapByEmploymentYear, Map<Integer, Map<String, List<Employee>>> yearSalaryEmployeesMap) {
        for (int i : mapByEmploymentYear.keySet()) {
            yearSalaryEmployeesMap.put(i, mapByEmploymentYear.get(i).stream().collect(Collectors.groupingBy(Employee::getSalaryRange, HashMap::new, toList())));
        }
    }
}