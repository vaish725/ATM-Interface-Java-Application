/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ATM {  //coded by Vaishnavi

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            final String USER = "ATM"; //storing name of database in final String USER
            final String PASS = "atm"; //storing password of database in final String PASS
            String DB_URL = "jdbc:derby://localhost:1527/OOPGP"; //storing connection URL in a string
            Class.forName("org.apache.derby.jdbc.ClientDriver"); //load the driver class to establish connection
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //create connection object
                                                                               //and pass connection URL,name & password
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY); //create statement object
            String sql1,sql2;
            sql1 = "SELECT * FROM CARD_INFO WHERE CARD_NO=";
            
            int choice = 0;
            int flag=0;
            
            Transaction t1 = new Transaction();//obj of Transaction class
            Account a1 = new Account();//obj of Account class
            Pin p1 = new Pin();
            
            Scanner scan = new Scanner(System.in);
            
            System.out.println("***********************");
            System.out.println("Welcome!");
            
            int Pin;
            int cBal, sBal;
            while(true){
                System.out.println("Insert the ATM card number"); //user input for 16 digit card number
                String cardNo=scan.next();
                if(cardNo.length()==16){ //if card number is 16 digit
                    sql1=sql1+"'"+cardNo+"'";
                    ResultSet rs = stmt.executeQuery(sql1); //execute the query
                    if(rs.next()){ //check from the database
                        rs.first();
                        Pin=rs.getInt(2);
                        cBal=rs.getInt(5);
                        sBal=rs.getInt(6);
                        a1.setPin(Pin);
                        a1.setCurrentBal(cBal);
                        a1.setSavingsBal(sBal);
                        a1.setConn(conn);
                        System.out.println("Your card number is: "+cardNo);
                        break;
                    }
                    else{ //entered card number does not exist
                        System.out.println("The entered card number does not exist! Please try again...");
                        cardNo = null;
                    }
                } 
                else{ //entered card number is not 16 digit
                    System.out.println("Invalid Card number! Please enter valid 16 digit card number...");
                    cardNo = null;
                }    
            }
            //pin validation here
            while(flag<3){
                System.out.println("Enter your 4-digit PIN: ");
                int inPin = scan.nextInt();
                if(inPin==Pin){
                    System.out.println("Pin entered successfully!");
                    break;
                }
                else{
                    System.out.println("Wrong input! Please try again...");
                    flag++;
                }
            }
            if(flag==3){
                System.out.println("You have exceeded your chances to enter PIN! Please re-insert your Card...");
                return;
            }
            
            t1.setAccount(a1);
            while(choice!=5){ //menu driven program starts here
                System.out.println("Select the operation: \n1.Withdraw\n2.Deposit\n3.Check Balance\n4.Change pin\n5.EXIT");
                choice=scan.nextInt();
                
                switch(choice){
                    case 1: 
                            int t = t1.Withdraw(); //withdraw money
                            conn.commit();
                            if(t==1){//savings account
                                sql1 = "UPDATE CARD_INFO SET SAVINGS_BALANCE="+a1.SavingsBal+"WHERE PIN="+Pin;//update in database
                                int temp = stmt.executeUpdate(sql1);
                                conn.commit();
                            }
                            else if(t==-1){//current account
                                sql1 = "UPDATE CARD_INFO SET CURRENT_BALANCE="+a1.CurrentBal+"WHERE PIN="+Pin;//update in db
                                int temp = stmt.executeUpdate(sql1);
                                conn.commit();
                            }
                            break;
                    case 2:
                            int p = t1.Deposit();//deposit money
                            conn.commit();
                            if(p==1){//savings account
                                sql1 = "UPDATE CARD_INFO SET SAVINGS_BALANCE="+a1.SavingsBal+"WHERE PIN="+Pin;//update in db
                                int temp = stmt.executeUpdate(sql1);
                                conn.commit();
                            }
                            else if(p==-1){//current account
                                sql1 = "UPDATE CARD_INFO SET CURRENT_BALANCE="+a1.CurrentBal+"WHERE PIN="+Pin;//update in db
                                int temp = stmt.executeUpdate(sql1);
                                conn.commit();
                            }
                            break;
                    case 3:
                            t1.showBalance();//show balance of both types of accounts
                            break;
                    case 4:
                            int q = p1.changePin(a1);//change pin
                            sql1 = "UPDATE CARD_INFO SET PIN="+q+"WHERE PIN="+Pin;//update in db
                            int temp = stmt.executeUpdate(sql1);
                            conn.commit();
                            break;
                    case 5:
                            break;//exiting the system                           
                }
            }
        } catch(Exception e){ //to catch any exception if found
            e.printStackTrace();
        
        }
    }
    
}
