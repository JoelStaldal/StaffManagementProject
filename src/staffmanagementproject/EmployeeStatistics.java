package staffmanagementproject;

import java.time.LocalDate;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
        double sum = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        
        double averageSalary = sum / employeeList.size();
        System.out.printf("\nAverage salary: %,.2f\n", averageSalary);
        System.out.println("");
    }

    public static void maxSalary() {

        System.out.println("");
        double max = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .max().getAsDouble();
        
         employeeList.stream()
                .filter(e -> e.getSalary() == max)
                .map(Employee::getName)
                .forEach(e -> System.out.print(e + " "));

         System.out.printf(" %,.2f\n", max);
         System.out.println("");
                
    }

    public static void minSalary() {
        
        System.out.println("");
        double min = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .min().getAsDouble();

        employeeList.stream()
                .filter(e -> e.getSalary() == min)
                .map(Employee::getName)
                .forEach(e -> System.out.print(e + " "));

        System.out.printf("%,.2f\n", min);
        System.out.println("");

    }

    public static void sumBonus() {
        double totBonus = 0;
        System.out.println("");
        for (Employee currentEmployee : employeeList) {
            System.out.println(String.format("%-20s%,.2f", currentEmployee.getName(), currentEmployee.bonus()));
            totBonus += currentEmployee.bonus();
        }
        System.out.println("-------------");
        System.out.printf("Total Bonus: %,.2f\n", totBonus);
        System.out.println("");
    }

    public static void allSalaries() {
        System.out.println("\nAll salaries in ascending order:\n");
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
        ArrayList<Employee> sortedList = new ArrayList<>(employeeList);
        Collections.sort(sortedList, (o1, o2) -> o2.getBirthdate().compareTo(o1.getBirthdate()));
        double totAge = 0;
        System.out.println("\nName:                Age:");
        for (Employee currentEmployee : sortedList) {
            LocalDate x = currentEmployee.getBirthdate();
            LocalDate y = now();
            long ageInYears = YEARS.between(x, y);
            totAge += ageInYears;
            System.out.println(String.format("%-20s %d",currentEmployee.getName(),ageInYears));
        }
        System.out.printf("\nAverage age at company: %.1f\n", totAge / sortedList.size());
        System.out.println("");
    }

    public static void showGenders() {

        long men = employeeList.stream()
                .filter(e -> e.getGender() == MALE)
                .count();
        
        long women = employeeList.stream()
                .filter(e -> e.getGender() == FEMALE)
                .count();
        
        long unknown = employeeList.stream()
                .filter(e -> e.getGender() == UNKNOWN)
                .count();
        
        double percentage = 100.0 / (men + women + unknown);
        System.out.println("\nNumber of men: " + men + " (" + String.format("%.2f", men * percentage) + "%)");
        System.out.println("Number of women: " + women + " (" + String.format("%.2f", women * percentage) + "%)\n");
        if (unknown > 0){
            System.out.println("Number of people with unknown gender: " + unknown + " (" + String.format("%.2f", unknown * percentage) + "%)\n");
        }

        employeeList.stream()
                .filter(e -> e.getGender() == MALE)
                .map(e -> e.getGender() + " " + e.getName())
                .forEach(System.out::println);
        
        System.out.println("");
        
        employeeList.stream()
                .filter(e -> e.getGender() == FEMALE)
                .map(e -> e.getGender() + " " + e.getName())
                .forEach(System.out::println);
        
        System.out.println("");
        
        employeeList.stream()
                .filter(e -> e.getGender() == UNKNOWN)
                .map(e -> e.getGender() + " " + e.getName())
                .forEach(System.out::println);
    }
    
    public static void sortByName(){
        System.out.println("\nBy name in descending order:\n");
        System.out.println("ID: Name:");
        List<String> sortedByNameList = employeeList
                .stream()
                .sorted((a,b)->b.getName().compareTo(a.getName()))
                .map(e -> e.getId() + "   " + e.getName())
                .collect(Collectors.toList());
                
        sortedByNameList.forEach(System.out::println);
        System.out.println("");
    }
    
    public static void sortByBirthdate(){
        System.out.println("\nBy birthdate in ascending order:\n");
        System.out.println("Birthdate:   Name:");
        List<String> sortedByBirthdate = employeeList
                .stream()
                .sorted((a,b)->a.getBirthdate().compareTo(b.getBirthdate()))
                .map(e -> e.getBirthdate() + "   " + e.getName())
                .collect(Collectors.toList());
        
        sortedByBirthdate.forEach(System.out::println);
        System.out.println("");
    }
}
