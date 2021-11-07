
/*
Online Java - IDE, Code Editor, Compiler

Online Java is a quick and easy tool that helps you to build, compile, test your programs online.
*/
import java.util.*;

public class Main
{
    public static void main(String[] args) {
        
        List<List<Integer>> userMap = new ArrayList<List<Integer>>();
        User users = new User(123, 90876);
        User users1 = new User(123, 9087);
        
        RegisterUser registerUser = new RegisterUser();
        registerUser.register(users, userMap);
        
        List<Symptoms> covidSymptom = new ArrayList<Symptoms>();
        SelfAssesment selfAssesment = new SelfAssesment(covidSymptom, false, false);
       
        Risk risk = new Risk();
        risk.riskCalculation(selfAssesment);
        
        
        Admin admin = new Admin();
        admin.register(users1, userMap);
        
        Zones zones = new Zones();
        zones.markZones(123, userMap);
    }
}


class User {
    Integer pincode;
    Integer mobile;
    
    User(Integer pincode, Integer mobile){
        this.pincode = pincode;
        this.mobile = mobile;
    }
}

enum Symptoms {
    COUGH, SORE_THROAT, FEVER;
}

enum Color {
    GREEN, ORANGE, RED;
}

class RegisterUser {
    
    void register(User user, List<List<Integer>> users) {
        List<Integer> userList = new ArrayList();
        userList.add(user.mobile);
        userList.add(user.pincode);
       
        if(users.contains(userList)) {
            System.out.println("User already registered.");
        } else{
            users.add(userList);
            System.out.println("Registered new User: " + user.mobile);
        }
    }
}

class Admin extends RegisterUser {
    
    void recovered(List<List<Integer>> users, Integer mobile) {
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).contains(mobile)) {
                users.remove(i);
                 System.out.println("User has recovered: " + mobile);
            }
        }
    }
    
    void enterResult(List<List<Integer>> users, User person, Integer result) {
        List<Integer> temp = new ArrayList();
        temp.add(person.mobile);
        temp.add(person.pincode);
        int idx = users.indexOf(temp);
        users.get(idx).add(result);
    }
}

class SelfAssesment {
    List<Symptoms> symptom;
    boolean travelHistory;
    boolean contactWithCovidPositive;
    
    SelfAssesment(List<Symptoms> symptom, boolean travelHistory, boolean contactWithCovidPositive){
        this.symptom = symptom;
        this.travelHistory = travelHistory;
        this.contactWithCovidPositive = contactWithCovidPositive;
    }
}

class Risk {
    void riskCalculation(SelfAssesment selfAssesment ) {
        if(selfAssesment.symptom.size() == 0 && !selfAssesment.travelHistory && !selfAssesment.contactWithCovidPositive){
            System.out.println("Risk is 5%");
        } else {
            System.out.println("Cannot calculate risk invalid parameters.");
        }
    }
}

class Zones {
    
    Map<Integer, Color> zoneColor = new HashMap();
    void markZones(Integer pincode, List<List<Integer>> users) {
        zoneColor.put(pincode, Color.GREEN);
        int count = 0;
        for(int i = 0; i < users.size(); i++) {
            List<Integer> temp = users.get(i);
            if(temp.contains(pincode)){
                count++;
            }
        }
         System.out.println("No. of positive cases: " + count);
         System.out.println("Zone colour is: ");
        if(count > 0 && count <= 5) {
            zoneColor.put(pincode, Color.ORANGE);
            System.out.println(Color.ORANGE);
        } else if(count > 5) {
            zoneColor.put(pincode, Color.RED);
            System.out.println(Color.RED);
        } else {
            System.out.println(Color.GREEN);
        }
    }
}



