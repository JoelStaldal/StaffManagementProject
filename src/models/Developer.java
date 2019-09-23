
package models;

import utilities.DeveloperLevel;
import static utilities.DeveloperLevel.*;
import utilities.GenderType;

public class Developer extends Employee {
    
    private String programmingLanguage;
    private DeveloperLevel developerLevel;

    public Developer(String programmingLanguage, DeveloperLevel level, double salary, String name, String birthdate, GenderType gender) {
        super(salary, name, birthdate, gender);
        this.programmingLanguage = programmingLanguage;
        this.developerLevel = level;
    }
    @Override
    public double bonus(){
        double bonus;
        if(this.developerLevel == JUNIOR){
            bonus = employeeBonusBase * 1.5;
        } else {
            bonus = employeeBonusBase * 3;
        }
        return bonus;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public void setDeveloperLevel(DeveloperLevel developerLevel) {
        this.developerLevel = developerLevel;
    }

    @Override
    public String toString() {
        return super.toString() + "                Job Title: Developer\n";
    }

    @Override
    public void printExtraInfo() {
        System.out.println("                Developer level: " + developerLevel + " Programming language: " + programmingLanguage);
    }
    
    
}
