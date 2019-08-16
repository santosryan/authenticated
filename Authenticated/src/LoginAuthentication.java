import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginAuthentication {
	
	static int index;
	
	public static String getUser(ArrayList<Accounts> accountList) {
		
		// Asks user to input a username, then checks if it is a valid username
        // from the credentials file, exits after 3 failed attempts
        Scanner scnr = new Scanner(System.in);
        int count = 0;
        String username;
        
        while( count < 3 ) {

            System.out.print("Username: ");
            username = scnr.nextLine();
            
            if ( checkUsername(accountList, username ) ) {
                break;
            }
            else {
                System.out.println("Error: Invalid username. Please try again.");
                System.out.println();
                count++;
            }
        }
        if ( count == 3 ) {
            System.out.println("Too many failed attempts. Please press Enter to exit.");
            scnr.nextLine();
            System.exit(0);
        }
        
        return username = null;
		
	}
	
	public static String getPassword(ArrayList<Accounts> accountList, String username ) 
            throws FileNotFoundException, NoSuchAlgorithmException {
        
        // Asks user to input a password, then checks if it is a valid password
        // for the specified username from the credentials file
        Scanner scnr = new Scanner(System.in);
        String password = null;
        int count = 0;
        
        while( count < 3 ) {
            
            System.out.print("Password: ");
            password = scnr.nextLine();
            
            if ( checkPassword(accountList, username, password) ) {
                return password;
            }
            else{
                System.out.println("Error: Invalid password. Please try again.");
                System.out.println();
                count++;
            }
        }
        // Displays message for too many failed attempts and exits program
        if ( count == 3 ) {
            System.out.println("Too many failed attempts. Please press Enter to exit.");
            scnr.nextLine();
            System.exit(0);
        }
        
        return password;
        
    }//method getPassword

	public static boolean checkUsername(ArrayList<Accounts> accountList, String username) {
		// Iterates through the arraylist to find an account with the
		// specified username
		for (Accounts account : accountList) {
			if(account.getUsername().equals(username)){
				return true;
			}
		}
		
		return false;
		
	}
	
	public static boolean checkPassword(ArrayList<Accounts> accountList, String username, String password) 
			throws NoSuchAlgorithmException {
		
		String temp_hashPass;
		
		// Iterates through the arraylist to find an account with the validated username
		// Then it will convert the given password and convert it to a hashed password
		// and validate that the created hashpass is the same as the one for the account
		for (Accounts account : accountList) {
				
				temp_hashPass = convertPassword(password);
				
				if (account.getHashPass().equals(temp_hashPass)) {
					index = accountList.indexOf(account);
					return true;
				}
		}
		
		return false;
	}
	
	// Method to convert the given password to a hash password 
    public static String convertPassword(String password ) throws NoSuchAlgorithmException {
    	
    	String hashPassword = null;
        
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
	        
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
	    
		}
		
	    //converts hashed password to string
	    hashPassword = sb.toString();
	    
	    return hashPassword;
    }//method convertPassword
    
    // Gets role for account
    public static String getRole(ArrayList<Accounts> accountList) 
    		throws NoSuchAlgorithmException{
    	
    	return accountList.get(index).getRole();
    	
    }// method getRole
}
