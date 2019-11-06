package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import utilities.GenderType;

public abstract class Employee implements Comparable<Employee> {

    private final int id;
    private double salary = 20_000;
    private String name;
    private LocalDate birthdate;
    private GenderType gender;
    protected final double employeeBonusBase;

    private static int idGenerator = 1;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Employee(double salary, String name, String birthdate, GenderType gender) {
        this.id = idGenerator++;
        setSalary(salary);
        this.name = name;
        this.birthdate = LocalDate.parse(birthdate, FORMATTER); 
        this.gender = gender;
        this.employeeBonusBase = 1000;
    }

    abstract public double bonus();
    
    abstract public void printExtraInfo();

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = LocalDate.parse(birthdate, FORMATTER);
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public final void setSalary(double salary) {
            if (salary < 20000) {
                System.out.println("");
                System.out.println("Too low salary, salary is set by default to " + String.format("%,.2f", this.salary));
            } else if (salary > 100000) {
                System.out.println("");
                System.out.println("Too high salary, salary is set by default to " + String.format("%,.2f", this.salary));
            } else {
                this.salary = salary;
            }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + "   " + String.format("%-20s ", name) + birthdate + "    "
                + gender.toString().charAt(0) + String.format("%-7s ", gender.toString().substring(1).toLowerCase()) + String.format(" %,.2f", salary) + "    "
                 + this.getClass().getSimpleName().replaceAll("([^_])([A-Z])", "$1 $2") + "\n";
    }
    
    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.salary, o.salary);
    }

}
