/*
    Assignment Number: 7-1
    Assignment Title: Final Project
    Program Author: Ryan Santos
    Due Date: October 22, 2017
*/

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class Authenticated {
    
    public static int getMenu() {
        
        // Creates a short menu screen and displays it to the user
        int userInput = 0;
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Welcome!");
        System.out.println("Please select whether to login or exit.");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println();
        userInput = scnr.nextInt();
        System.out.println();
        
        return userInput;
    } //method menu
    
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, Exception {
    
        
        Scanner scnr = new Scanner(System.in);
        ArrayList<Accounts> accountList;
        int input;
        String username;
        String password;
        String role;
        String roleFileName;
        String csvFileName = "credentials.csv";
        boolean menuSelection = false;
        
        accountList = CSVHandler.readFromCSV(csvFileName);
        
        while (!menuSelection) {
            input = getMenu();
            
            switch( input ) {
                case 1:
                    username = LoginAuthentication.getUser(accountList);
                    password = LoginAuthentication.getPassword(accountList, username );
                    role = LoginAuthentication.getRole(accountList);
                    roleFileName = role + ".txt";
                    System.out.println();
                    System.out.println( FileHandler.readFile( roleFileName ) );
                    System.out.println("Once finished, please press Enter to logout.");
                    scnr.nextLine();
                    System.out.println("Goodbye!");
                    
                    menuSelection = true;
                    break;
                case 2:
                    System.exit(0);
                default: 
                    System.out.println("Error: Invalid input. Please try again.");
                    System.out.println();
                    break;
            }
        }
        
    }//main
    
}// class Authenticated
