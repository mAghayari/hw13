package dto;

public class Employee {
    private int employeeNumber;
    private String firstName;
    private String lastName;
    private int salary;
    private int employmentYear;
    private int lastFiveYearsSalary;

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getEmploymentYear() {
        return employmentYear;
    }

    public void setHiringYear(int enteringYear) {
        this.employmentYear = enteringYear;
    }

    public int getLastFiveYearsSalary() {
        int tempSalary = salary;
        lastFiveYearsSalary = tempSalary * 12;
        for (int i = 0; i < 5; i++) {
            tempSalary = (tempSalary * 8) / 10;
            lastFiveYearsSalary += tempSalary * 12;
        }
        return lastFiveYearsSalary;
    }

    public void setLastFiveYearsSalary(int lastFiveYearsSalary) {
        this.lastFiveYearsSalary = lastFiveYearsSalary;
    }

    public String getSalaryRange() {
        int range = getIntSalaryRange();
        return range == 1 ? "1-5" : range == 2 ? "5-10" : "10-upper";
    }

    public int getIntSalaryRange() {
        double tempSalary = (double) salary / 1000000;
        return (tempSalary >= 1 && tempSalary <= 5) ? 1 : (tempSalary > 5 && tempSalary <= 10) ? 2 : 3;
    }

    @Override
    public String toString() {
        return String.format("%56s%13s%-30s", employeeNumber, " ", (firstName + " " + lastName));
    }

    public void employeeToString() {
        System.out.println(toString() + String.format("%,-25d", getLastFiveYearsSalary()));
    }
}