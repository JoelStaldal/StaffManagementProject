
package models;

import java.util.ArrayList;
import utilities.GenderType;

public class WebDesigner extends Employee{
    
    private final ArrayList<String> websites = new ArrayList<>();

    public WebDesigner(double salary, String name, String birthdate, GenderType gender) {
        super(salary, name, birthdate, gender);
    }
    
    @Override
    public double bonus(){
        double bonus = employeeBonusBase + websites.size() * 100;
        return bonus;
    }

    public ArrayList<String> getWebsites() {
        return websites;
    }

    

    @Override
    public void printExtraInfo() {
        System.out.println("                List of websites:");
        for (String website : websites) {
            System.out.println("                " + website);
        }
    }


    
    
    
    
}
