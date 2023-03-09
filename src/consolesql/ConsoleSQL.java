package consolesql;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import consolesql.DBConnection;

public class ConsoleSQL {

    Scanner myScan = new Scanner(System.in);
    DBConnection db = new DBConnection();
    PreparedStatement pst;
    ResultSet rs;
    Connection con = db.connectDB();
    
    public static void main(String[] args) {
        ConsoleSQL myObj = new ConsoleSQL();
        myObj.greet();
        
    }
 
    public void greet(){
        
        System.out.print("Do you want to start this program? press Y if yes or press any key(except Y) to continue: ");
        String s = myScan.next();
        if(!s.equalsIgnoreCase("y")){
            return;
        }home();
        
    }
    
    public void home(){
    
        System.out.println(" Press A to Add data \n Press U to Update data \n Press D to delete data \n Press V to View data");
        String s = myScan.next();
        if(s.equalsIgnoreCase("a")){
            addData();
        }else if(s.equalsIgnoreCase("u")){
            updateData();
        }else if(s.equalsIgnoreCase("d")){
            deleteData();
        }else if(s.equalsIgnoreCase("v")){
            viewData();
        }return;
        
    }
    
    public void addData(){
    
        System.out.println("ADD DATA: \n");
        System.out.print("Enter name: ");
        String name = myScan.next();
        System.out.print("Enter Age: ");
        String age = myScan.next();
        System.out.print("Enter Gender: ");
        String gender = myScan.next();
        
        System.out.println("Press Y to continue or Press any key to discard: ");
        String s = myScan.next();
        if(!s.equalsIgnoreCase("y")){
            System.out.println("Data not Inserted \n \n \n");
            home();
        }
            
            try {
                pst = con.prepareStatement("INSERT INTO s_info (name, age, gender) VALUES (?, ?, ?)");
                pst.setString(1, name);
                pst.setString(2, age);
                pst.setString(3, gender);
                
                int k = pst.executeUpdate();
                if(k == 1){
                    System.out.println("Data Inserted \n \n \n");
                    home();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ConsoleSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        
    }
    
    public void viewData(){
    
        try {
            pst = con.prepareStatement("SELECT * FROM s_info");
            rs = pst.executeQuery();
            
            
            while(rs.next()){
                String a = rs.getString("name");
                String b = rs.getString("age");
                String c = rs.getString("gender");
                String d = rs.getString("id");
                    
                System.out.println("Student ID: "+d+":\n" +"Name: " +a+ "\n"+
                                   "Age: "+b+"\n"+
                                   "Gender: "+c
                                   +"\n--------------------");
            }
            
            System.out.println("Press B to return to Homepage or Press any key(Except B) to exit program");
                String r = myScan.next();
                if(r.equalsIgnoreCase("b")){
                    home();
                }else if(r.equalsIgnoreCase("x")){
                    return;
                }else{
                    return;
                }
        } catch (SQLException ex) {
            Logger.getLogger(ConsoleSQL.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
    }
    
    public void updateData(){
    
        int i = 0;
        
        try {
            System.out.println("Enter Student ID: ");
            String inputId = myScan.next();
            
            pst = con.prepareStatement("SELECT * FROM s_info WHERE id = ?");
            pst.setString(1, inputId);
            rs = pst.executeQuery();
            
            if(rs.next()){
                String a1 = rs.getString("name");
                String b1 = rs.getString("age");
                String c1 = rs.getString("gender");

                System.out.println("Current Data: "
                        + "\nName: " +a1
                        + "\nAge: " +b1
                        + "\nGender: " +c1);
                i = 1;
            }else{
                i = -1;
            }
            
            String a2 = null;
            String b2 = null;
            String c2 = null;
            
        if(i==-1){
            System.out.println("Student ID not recognized");
        }else{
        
            System.out.println("Update Name: ");
            a2 = myScan.next();
            System.out.println("Update Age: ");
            b2 = myScan.next();
            System.out.println("Update Gender: ");
            c2 = myScan.next();
        
        }
        
        try {
            pst = con.prepareStatement("UPDATE s_info SET name = ?, age = ?, gender = ? WHERE id = ?");
            pst.setString(1, a2);
            pst.setString(2, b2);
            pst.setString(3, c2);
            pst.setString(4, inputId);
            
            int k = pst.executeUpdate();
            if(k==1){
                System.out.println("Data successfully updated");
                home();
            }else{
                System.out.println("There were errors updating the data");
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ConsoleSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsoleSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void deleteData(){
    
        try {
            System.out.println("Enter Student ID: ");
            String inputId = myScan.next();
            
            pst = con.prepareStatement("DELETE FROM s_info WHERE id = ?");
            pst.setString(1, inputId);
            
            int k = pst.executeUpdate();
            if(k==1){
                System.out.println("Data has been deleted");
                home();
            }else{
                System.out.println("Data not deleted");
                home();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsoleSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
    
  
