/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Transaction extends Account{   //Coded by Ishan
    
    Scanner sc = new Scanner(System.in); //get input from console
    Account a = null;
    Connection conn;
    Statement stmt;
    public void setAccount(Account a) throws ClassNotFoundException{
        this.a = a;
        try{ //coded by Vaishnavi (try-catch block)
                final String USER = "ATM"; //storing name of database in final String USER
                final String PASS = "atm"; //storing password of database in final String PASS
                String DB_URL = "jdbc:derby://localhost:1527/OOPGP"; //storing connection URL in a string
                Class.forName("org.apache.derby.jdbc.ClientDriver"); //load the driver class to establish connection
                conn = a.getConn();
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY); //create statement object
        }catch(Exception e){
            e.printStackTrace();
        }
    }  
    //@Override
    public int Withdraw() { //to withdraw money
        int amt;
        int flag = 0;
        int flag1 = 0;
        int flag2 = 0;
        
       
        int accOption; //stores type of account i.e. Savings/Current account
        try{
            while(flag == 0){
                System.out.println("From which account do you wish to Withdraw?\n1. Savings Account\n2. Current Account");
                System.out.print("Enter you choice: ");
                accOption = sc.nextInt(); //get the account type from user
                
                    
                if(accOption == 1 ){ //if Savings account then-
                    System.out.println("Enter the amount to withdraw: ");
                    amt = sc.nextInt(); //get the withdrawal amount from user
                    while(flag1 == 0){
                        if(amt > a.SavingsBal){ //if withdrawal amount is more than balance
                            System.out.println("Insufficient Balance");
                        }
                        else{
                            if(a.SavingsBal - amt <= 1000){ //if minimum balance of the account in maintained
                                System.out.println("Minimum Balance of the account i.e Rs 1000 is not fullfilled! Please try again...");
                                break;
                            }
                            else{ //withdrawal procedure in savings account
                                a.SavingsBal = a.SavingsBal - amt;
                                System.out.println("Amount Withdrawn Successful");
                                System.out.println("The Remaining Balance: "+a.SavingsBal); //display remaining balance
                                flag = 1;
                                flag1 = 1;
                                return 1;
                                
                            }
                        }
                    }
                }
            
                else if(accOption == 2 ){ //if Current account then-
                    System.out.println("Enter the amount to withdraw: ");
                    amt = sc.nextInt(); //get the withdrawal amount from user
                    while(flag2 == 0){
                        if(amt > a.CurrentBal){ //if withdrawal amount is more than balance
                            System.out.println("Insufficient Balance");
                        }
                        else{ 
                            if(a.CurrentBal - amt <= 1000){ //if minimum balance of the account in maintained
                               System.out.println("Minimum Balance of the account i.e Rs 1000 is not fullfilled! Please try again...");
                               break;
                            }
                            else{ //withdrawal procedure in current account
                                a.CurrentBal = a.CurrentBal - amt;
                                System.out.println("Amount Withdrawn Successful");
                                System.out.println("The Remaining Balance: "+a.CurrentBal);
                                flag = 1;
                                flag2 = 1;
                                return -1;
                            }
                        }
                    }
                }
                else{ //if not savings/current account then-
                    System.out.println("Wrong Option");
                }
            }
        } 
        catch (Exception e) { //to catch any exception found
            System.out.println(e);
        }
        return 0;
    }
    
    
   // @Override
    public int Deposit() {
        int depo;
        int accOption;
        int flag = 0;
        int flag1 = 0;
        try {
            while(flag == 0){
                System.out.println("Where do you want to Deposit?\n1. Savings Account\n2. Current Account");
                System.out.print("Enter your Choice: ");
                accOption = sc.nextInt(); //get the account type from user
                System.out.println("Enter the amount to Deposited: ");
                depo = sc.nextInt(); //get the deposit amount from user
                if(depo > 25000){ //cannot deposit more than Rs 25000 at a time
                    System.out.println("Can't Deposit more than 25,000 at a time! Please try again...");
                }
                else{
                    while(flag1 == 0){
                        if(accOption == 1){ //if savings account then-
                            a.SavingsBal = a.SavingsBal + depo; //deposit procedure in savings account
                            System.out.println("Amount Deposited Successfully");
                            System.out.println("The Remaining Balance: "+a.SavingsBal);
                            flag = 1;
                            flag1 = 1;
                            return 1;
                        }
                        else if(accOption == 2 ){ //if current account then-
                            a.CurrentBal = a.CurrentBal + depo; //deposit procedure in current account
                            System.out.println("Amount DEposited Successfully");
                            System.out.println("The Remaining Balance: "+a.CurrentBal);
                            flag = 1;
                            flag1 = 1;
                            return -1;
                        }
                        else{ //if not savings/current account then-
                            System.out.println("Wrong Option");
                        }
                    }
                }
            }
        }
        catch (Exception e) { //to catch any exception found
            System.out.println(e);
        }
        return 0;
    }
    
    public void showBalance(){
        int accOption;
        try{
            System.out.println("Which balance you want to know?\n1. Savings Account\n2. Current Account");
            System.out.print("Enter you choice: ");
            accOption = sc.nextInt(); //get type of account from user
            if(accOption == 1 ){ //if savings account then-
                System.out.println("The Balance of Savings Account is : "+a.SavingsBal); //display balance
            }
            else if(accOption == 2 ){ //if current account then-
                    System.out.println("The Balance of Current Account is: "+a.CurrentBal); //display balance
                }
            else{ //if not savings/current account-
                System.out.println("Wrong Option");
            }
        } catch (Exception e) { //to catch any exception found
            System.out.println(e);
        }
    }
}
    
