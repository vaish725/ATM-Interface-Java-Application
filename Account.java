/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;
import java.sql.*;
/**
 *
 * @author HP
 */
public class Account { //coded by Ishan
    public String Name; //name
    public long accountNumber; //account number
    public int pin; //pin
    public int SavingsBal;// balance of savings account
    public int CurrentBal; //balance of current account
    private Connection conn = null;
    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the accountNumber
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * @return the SavingsBal
     */
    public int getSavingsBal() {
        return SavingsBal;
    }

    /**
     * @param SavingsBal the SavingsBal to set
     */
    public void setSavingsBal(int SavingsBal) {
        this.SavingsBal = SavingsBal;
    }

    /**
     * @return the CurrentBal
     */
    public int getCurrentBal() {
        return CurrentBal;
    }

    /**
     * @param CurrentBal the CurrentBal to set
     */
    public void setCurrentBal(int CurrentBal) {
        this.CurrentBal = CurrentBal;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
}
