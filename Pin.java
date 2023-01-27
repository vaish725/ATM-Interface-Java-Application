/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Pin { //coded by Kimaya
    Scanner sc = new Scanner(System.in);
    public int changePin(Account a){
        int present=0;
        System.out.println("Enter your current pin: ");
        int currentPin = sc.nextInt();
        if(currentPin!= a.pin){
            System.out.println("Invalid PIN! Please try again...");
            return 0;
        }
        while(present==0){
            System.out.println("Enter new pin: ");
            int newPin = sc.nextInt();
            if(newPin == currentPin){
                System.out.println("Please enter a different pin: ");
            }
            else{
                System.out.println("Confirm new pin: ");
                int newPin2 = sc.nextInt();
                if(newPin==newPin2){
                    System.out.println("Pin changed successfully!"); 
                    present=1;
                    return newPin;
                }
                else{
                    System.out.println("Entered pins dont match. Please try again!");
                }
            }  
        }
        return 0;
    }
}
