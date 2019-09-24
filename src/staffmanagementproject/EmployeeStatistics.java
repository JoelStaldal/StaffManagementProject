package staffmanagementproject;

import java.time.LocalDate;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.ArrayList;
import java.util.Collections;
import models.Developer;
import models.Employee;
import models.Marketing;
import models.Technician;
import models.WebDesigner;
import static staffmanagementproject.EmployeeManagement.employeeList;
import static utilities.GenderType.*;

public class EmployeeStatistics {

    public static void numberOfEmployees() {
        System.out.println("\nTotal number of employees: " + employeeList.size() + "\n");
        int developers = 0, webDesigners = 0, technicians = 0, marketing = 0;
        for (Employee employee : employeeList) {
            if (employee instanceof Developer) {
                developers++;
            } else if (employee instanceof WebDesigner) {
                webDesigners++;
            } else if (employee instanceof Technician) {
                technicians++;
            } else if (employee instanceof Marketing) {
                marketing++;
            }
        }
        System.out.println("Number of Developers: " + developers);
        System.out.println("Number of Web Designers: " + webDesigners);
        System.out.println("Number of Technician: " + technicians);
        System.out.println("Number of Marketing employees: " + marketing + "\n");
    }

    public static void averageSalary() {
        double averageSalary = 0;
        for (Employee currentEmployee : employeeList) {
            averageSalary += currentEmployee.getSalary();
        }
        double sum = averageSalary / employeeList.size();
        System.out.printf("Average salary: %,.2f\n", sum);
    }

    public static void maxSalary() {
        int max = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getSalary() > employeeList.get(max).getSalary()) {
                max = i;
            }
        }
        System.out.printf(employeeList.get(max).getName() + " %,.2f\n", employeeList.get(max).getSalary());
    }

    public static void minSalary() {
        int min = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getSalary() < employeeList.get(min).getSalary()) {
                min = i;
            }
        }
        System.out.printf(employeeList.get(min).getName() + " %,.2f\n", employeeList.get(min).getSalary());
    }

    public static void sumBonus() {
        double totBonus = 0;
        
        for (Employee currentEmployee : employeeList) {
            System.out.println(String.format("%-20s%,.2f", currentEmployee.getName(), currentEmployee.bonus()));
            totBonus += currentEmployee.bonus();
        }
        System.out.println("-------------");
        System.out.printf("Total Bonus: %,.2f\n", totBonus);
        System.out.println("");
    }

    public static void allSalaries() {
        System.out.println("\nAll salaries in increasing order:\n");
        ArrayList<Employee> sortedList = new ArrayList<>(employeeList);
        Collections.sort(sortedList);
        System.out.println("ID:  Name:                 Salary:");
        for (Employee currentEmployee : sortedList) {
            String name = currentEmployee.getName();
            String[] nameSplit = name.split(" ");
            System.out.println(fixLength(Integer.toString(currentEmployee.getId()), 5) + fixLength(nameSplit[0], 10) + 
                    fixLength(nameSplit[1], 12) + String.format("%,.2f", currentEmployee.getSalary()));
        }
        System.out.println("");
    }

    private static String fixLength(String s, int i) {
        if (s.length() < i) {
            for (int j = s.length(); j < i; j++) {
                s = s + " ";
            }
        }
        return s;
    }

    public static void showAges() {
        double totAge = 0;
        System.out.println("\nName:                Age:");
        for (Employee currentEmployee : employeeList) {
            LocalDate x = currentEmployee.getBirthdate();
            LocalDate y = now();
            long ageInYears = YEARS.between(x, y);
            totAge += ageInYears;
            System.out.println(String.format("%-20s %d",currentEmployee.getName(),ageInYears));
        }
        System.out.printf("\nAverage age at company: %.1f\n", totAge / employeeList.size());
        System.out.println("");
    }

    public static void showGenders() {
        int women = 0, men = 0, unknown = 0;
        for (Employee employee : employeeList) {
            if (employee.getGender() == MALE) {
                men++;
            } else if (employee.getGender() == FEMALE) {
                women++;
            } else if (employee.getGender() == UNKNOWN) {
                unknown++;
            }
        }
        double porcentage = 100.0 / (men + women + unknown);
        System.out.println("\nNumber of men: " + men + " (" + String.format("%.2f", men * porcentage) + "%)");
        System.out.println("Number of women: " + women + " (" + String.format("%.2f", women * porcentage) + "%)\n");
        if (unknown > 0) {
            System.out.println("Number of people with unknown gender: " + unknown + " (" + String.format("%.2f", unknown * porcentage) + "%)");

        }
    }

}
