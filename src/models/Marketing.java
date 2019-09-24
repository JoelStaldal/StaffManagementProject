
package models;

import java.util.ArrayList;
import utilities.GenderType;

public class Marketing extends Employee {
    
    private final ArrayList<String> ListOfCustomers = new ArrayList<>();
    private static int budget = 100_000;

    public Marketing(double salary, String name, String birthdate, GenderType gender) {
        super(salary, name, birthdate, gender);
    }
    
    @Override
    public double bonus(){
        double bonus = employeeBonusBase + 300 * ListOfCustomers.size();
        return bonus;
    }

    public ArrayList<String> getListOfCustomers() {
        return ListOfCustomers;
    }
    public static int getBudget() {
        return budget;
    }

    public static void setBudget(int budget) {
        if (budget < 0) {
            System.out.println("Not enough budget");
        } else {
            Marketing.budget = budget;
        }

    }

   

    @Override
    public void printExtraInfo() {
        System.out.println("                List of customers:");
        for (String ListOfCustomer : ListOfCustomers) {
            System.out.println("                " + ListOfCustomer);
        }
    }


    
    
    
}
