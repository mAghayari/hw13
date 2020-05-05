import dto.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UtilityMethods utilityMethods = new UtilityMethods();
        Map<Integer, List<Employee>> MapByEmploymentYear = UtilityMethods.sortByEmploymentYear();
        Map<Integer, Map<String, List<Employee>>> yearSalaryEmployeesMap = new HashMap<>();
        utilityMethods.fillYearSalaryEmployeesMap(MapByEmploymentYear, yearSalaryEmployeesMap);
        utilityMethods.printQuestion1(yearSalaryEmployeesMap);
        utilityMethods.printQuestion4(yearSalaryEmployeesMap);
    }
}