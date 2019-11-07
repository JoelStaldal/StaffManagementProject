package staffmanagementproject;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import models.Developer;
import models.Employee;
import models.Marketing;
import models.Technician;
import models.WebDesigner;
import static staffmanagementproject.StaffManagementProject.sc;
import utilities.DeveloperLevel;
import utilities.GenderType;

public class EmployeeManagement {
    
    private static String birthdate;
    private static GenderType gender;
    private static DeveloperLevel level;
    private static double salary;
    private static String name;
    
    
    static ArrayList<Employee> employeeList = new ArrayList<>();

    public static void loadDb() {

        Developer developer1 = new Developer("Java", DeveloperLevel.SENIOR, 34155, "Joel Ståldal", "1989-03-10", GenderType.MALE);
        Developer developer2 = new Developer("Java", DeveloperLevel.JUNIOR, 32248, "Sandra Isaksson", "1983-01-29", GenderType.FEMALE);
        Marketing marketing1 = new Marketing(41700, "Lena Andersson", "1977-12-24", GenderType.FEMALE);
        Marketing marketing2 = new Marketing(21700, "Maja Karlsson", "1996-09-09", GenderType.FEMALE);
        Technician technician1 = new Technician(2, 43300, "Olle Pettersson", "1952-05-01", GenderType.MALE);
        Technician technician2 = new Technician(5, 33700, "Eva Berg", "1966-08-02", GenderType.FEMALE);
        WebDesigner webdesigner1 = new WebDesigner(29500, "Kalle Svensson", "2001-04-03", GenderType.MALE);
        WebDesigner webdesigner2 = new WebDesigner(21500, "Ivan Pålsson", "1999-03-29", GenderType.MALE);

        employeeList.add(developer1);
        employeeList.add(developer2);
        employeeList.add(marketing1);
        employeeList.add(marketing2);
        employeeList.add(technician1);
        employeeList.add(technician2);
        employeeList.add(webdesigner1);
        employeeList.add(webdesigner2);

        webdesigner1.getWebsites().add("google.com");
        webdesigner1.getWebsites().add("facebook.com");
        webdesigner1.getWebsites().add("iths.se");
        webdesigner1.getWebsites().add("amazon.com");

        webdesigner2.getWebsites().add("google.com");
        webdesigner2.getWebsites().add("yahoo.com");
        webdesigner2.getWebsites().add("discordapp.com");
        webdesigner2.getWebsites().add("webhallen.com");

        marketing1.getListOfCustomers().add("Google");
        marketing1.getListOfCustomers().add("Tele2");
        marketing1.getListOfCustomers().add("Spotify");
        marketing1.getListOfCustomers().add("Klarna");

        marketing2.getListOfCustomers().add("Iths");
        marketing2.getListOfCustomers().add("Telia");
        marketing2.getListOfCustomers().add("Facebook");
        marketing2.getListOfCustomers().add("Blocket");

    }

    public static void addEmployee() {
        System.out.println("Enter employee data");
        
        boolean loop = true;
        while (loop) {
            System.out.print("Enter full name: ");
            name = sc.nextLine();
        if (name.matches(".*\\d+.*")) { //contains digits
            System.out.println("Name can't contain digits, try again");    
        } else {
            loop = false;
        }    
        }
        loop = true;
        while (loop) {
            System.out.print("Birthdate (YYYY-MM-DD): ");
            birthdate = sc.nextLine();
            try {
                LocalDate birthdateTryFormat = LocalDate.parse(birthdate, Employee.FORMATTER);
                loop = false;
            } catch (DateTimeParseException e) {
                System.out.println("Not a valid date, try again");
            } 
        }
        
        loop = true;
        while (loop) {
            System.out.print("Enter gender (Male/Female/Unknown): ");
            try {
                gender = GenderType.valueOf(sc.nextLine().toUpperCase());
                loop = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid gender, try again");
            }
        }
        
        loop = true;
        while (loop) {
            System.out.print("Enter salary: ");
            
            try {
                salary = (Double.parseDouble(sc.nextLine().replace(" ", "")));
                loop = false;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid salary, try again");
            }
        }
        
        

        System.out.println("Choose job position: ");

        System.out.println("1. Developer");
        System.out.println("2. Web Designer");
        System.out.println("3. Technician");
        System.out.println("4. Marketing");

        int jobChoice = tryInput();
        //sc.nextLine();

        switch (jobChoice) {
            case 1:
                System.out.print("Programming language: ");
                String language = sc.nextLine();
                
                loop = true;
                while (loop) {
                    System.out.print("Developer level (Junior/Senior): ");
                    try {
                        level = DeveloperLevel.valueOf(sc.nextLine().toUpperCase());
                        loop = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Not a valid level, try again");
                    }
                }
                
                
                employeeList.add(new Developer(language, level, salary, name, birthdate, gender));
                break;
            case 2:
                employeeList.add(new WebDesigner(salary, name, birthdate, gender));
                updateWebsites(employeeList.get(employeeList.size() - 1));
                break;
            case 3:
                System.out.print("Number of servers: ");
                int nServers = tryInput();
                //sc.nextLine();
                employeeList.add(new Technician(nServers, salary, name, birthdate, gender));
                break;
            case 4:
                employeeList.add(new Marketing(salary, name, birthdate, gender));
                updateCustomers(employeeList.get(employeeList.size() - 1));
                break;
            default:
                System.out.println("Not a valid input, try again");
                break;
        }
        if (jobChoice > 0 && jobChoice < 5) {
            System.out.println("\nEmployee is added successfully to system");
            header();
            System.out.println(employeeList.get(employeeList.size() - 1));
        }
    }

    public static void removeEmployeeById() {
        System.out.print("Enter ID: ");
        int id = tryInput();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                System.out.println(employeeList.get(i).getName() + " is removed from system");
                employeeList.remove(i);
                return;
            }
        }
        System.out.println("Employee not found, try again");
    }

    public static void removeEmployeeByName() {
        System.out.print("Enter full name: ");
        String name = sc.nextLine();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println("Employee ID: " + employeeList.get(i).getId() + " Name: " + employeeList.get(i).getName() + " is removed from system");
                employeeList.remove(i);
                return;
            }
        }
        System.out.println("Employee not found, try again");
    }

    public static void updateNameOfEmployeeById(int id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                System.out.println("Current name: " + employeeList.get(i).getName());
                
                String updateName = null;
                boolean loop = true;
                while (loop) {
                    System.out.print("Update name: ");
                    updateName = sc.nextLine();
                    if (updateName.matches(".*\\d+.*")) { //contains digits
                        System.out.println("Name can't contain digits, try again");
                    } else {
                        loop = false;
                    }
                }
                employeeList.get(i).setName(updateName);
                System.out.println("Name is updated: " + employeeList.get(i).getName());
                return;
            }
        }
        System.out.println("Employee not found, try again");
    }

    public static void updateSalaryOfEmployeeById(int id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                System.out.println("Current salary: " + String.format("%,.2f", employeeList.get(i).getSalary()));
                
                boolean loop = true;
                while (loop) {
                    System.out.print("New salary: ");
                    try {
                        employeeList.get(i).setSalary((Double.parseDouble(sc.nextLine().replace(" ", ""))));
                        loop = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid salary, try again");
                    }    
                }
                System.out.println("Current salary: " + String.format("%,.2f", employeeList.get(i).getSalary()));
                return;
            }
        }
        System.out.println("Employee not found, try again");
    }

    public static void updateBirthdateOfEmployeeById(int id) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                System.out.println(employeeList.get(i).getName() + " Registered birthdate: " + employeeList.get(i).getBirthdate());
                System.out.println("Date format: YYYY-MM-DD");
                String newDate;
                boolean loop = true;
                while (loop) {
                    System.out.print("New date: ");
                    newDate = sc.nextLine();
                    try {
                        employeeList.get(i).setBirthdate(newDate);
                        loop = false;
                    } catch (DateTimeParseException e) {
                        System.out.println("Not a valid date, try agian");
                    }
                }
                System.out.println("Birthdate is updated: " + employeeList.get(i).getBirthdate());
                return;

            }
        }
        System.out.println("Employee not found, try again");
    }

    static void updateGenderOfEmployeeById(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                System.out.println("Current gender: " + employee.getGender());
                System.out.println("What is the new gender? (Male/Female/Unknown)");
                
                GenderType newGender = null;
                boolean loop = true;
                while (loop) {
                    try {
                        newGender = GenderType.valueOf(sc.nextLine().toUpperCase());
                        loop = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Not a valid gender, try again");    
                    }    
                }
                employee.setGender(newGender);
                System.out.println("Gender is updated: " + employee.getGender());
                return;
            }
        }
        System.out.println("Employee not found, try again");
    }

    public static Employee showOneEmployeeById(int id) {
        header();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                System.out.println(employeeList.get(i));
                employeeList.get(i).printExtraInfo();
                return employeeList.get(i);
            }
        }
        System.out.println("Employee not found, try again");
        return null;
    }

    public static void showAllEmployees() {
        header();
        employeeList.forEach(System.out::println);
    }

    public static void updateWebsites(Employee employee) {
        String website;
        ArrayList<String> websites = ((WebDesigner) employee).getWebsites();
        if (websites.size() > 0) {
            System.out.println("Current websites for this employee:");
            for (String s : websites) {
                System.out.println(s);
            }
        }

        System.out.println("Write webistes to add or (if existing) to remove them (Enter 0 to Exit)");
        website = sc.nextLine().toLowerCase().trim();
        while (!(website.equals("0") || website.equals(""))) {
            if (websites.contains(website)) {
                websites.remove(website);
                System.out.println("Website " + website + " removed");
            } else {
                System.out.println("Website " + website + " added");
                websites.add(website);
            }
            website = sc.nextLine().toLowerCase().trim();
        }

    }

    public static void updateCustomers(Employee employee) {
        String customer;
        ArrayList<String> customers = ((Marketing) employee).getListOfCustomers();
        if (customers.size() > 0) {
            System.out.println("Current customers for this employee:");
            for (String s : customers) {
                System.out.println(s);
            }
        }
        System.out.println("Write customer names to add or (if existing) to remove them (Enter 0 to Exit)");
        customer = sc.nextLine().trim();
        while (!(customer.equals("0") || customer.equals(""))) {
            if (customers.contains(customer)) {
                customers.remove(customer);
                System.out.println("Customer " + customer + " removed");
            } else {
                System.out.println("Customer " + customer + " added");
                customers.add(customer);
            }
            customer = sc.nextLine().trim();
        }

    }

    public static void updateBudget(Employee employee) {
        System.out.println("Enter customer: ");
        String customer = sc.nextLine();
        if (customer.equals("")) {
            return;
        }
        ArrayList customers = ((Marketing) employee).getListOfCustomers();

        if (customers.contains(customer)) {
            System.out.println("Current budget: " + String.format("%,d", Marketing.getBudget()));
            System.out.println("How much budget has been spent for the customer?");
            
            boolean loop = true;
            while (loop) {
                try {
                Marketing.setBudget(Marketing.getBudget() - Math.abs(Integer.parseInt(sc.nextLine().replace(" ", ""))));
                loop = false;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid input, try again");
            }
            }
            System.out.println("Current budget: " + String.format("%,d", Marketing.getBudget()));
            sc.nextLine();
        } else {
            System.out.println("Customer not found");
        }

    }

    public static void updateDeveloperProgLanguage(Employee employee) {
        System.out.println("Current programming language: " + ((Developer) employee).getProgrammingLanguage());
        System.out.print("Enter new programming language: ");
        String input2 = sc.nextLine();
        ((Developer) employee).setProgrammingLanguage(input2);
        System.out.println("Programming language updated: " + ((Developer) employee).getProgrammingLanguage());
    }

    public static void updateTechnicianNServers(Employee employee) {
        System.out.println("Current responsible of " + ((Technician) employee).getnServers() + " servers");
        System.out.print("Enter new number of servers: ");
        int choice = tryInput();
        //sc.nextLine();
        ((Technician) employee).setnServers(choice);
        System.out.println("Server responsibility updated to " + ((Technician) employee).getnServers());
    }

    public static void updateDeveloperLevel(Employee employee) {
        System.out.println("Current developer level: " + ((Developer) employee).getDeveloperLevel());
        System.out.println("Enter new developer level (Junior/Senior):");
        
        DeveloperLevel newLevel = null;
        boolean loop = true;
        while (loop) {
            try {
                newLevel = DeveloperLevel.valueOf(sc.nextLine().toUpperCase());
                loop = false;    
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid level, try again");
            }
        }
        ((Developer) employee).setDeveloperLevel(newLevel);
        System.out.println("Developer level updated: " + ((Developer) employee).getDeveloperLevel());
    }

    public static int tryInput() {
        int choice = 0;
        boolean loop = true;
        while (loop) {

            try {
                choice = sc.nextInt();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.print("Not a valid input, try agian: ");
            } finally {
                sc.nextLine();
            }
        }
        return choice;
    }
    
    public static void header(){
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("ID: Name:                Birthdate:    Gender:   Salary:      Job title:");
        System.out.println("------------------------------------------------------------------------");
    }

}
