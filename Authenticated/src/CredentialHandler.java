
import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class CredentialHandler {
    
    public static String convertPassword( String password ) 
            throws NoSuchAlgorithmException {
        
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
    
    public static boolean checkUsername( String username ) 
            throws FileNotFoundException {
    
        // Checks if the username inputted matches a username in credentials file
        File file = new File("credentials.txt");
        
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitString = line.split("\\s+");
                if( splitString[0].equals( username ) ) { 
                    return true;
                }
            }
        } 
        catch(FileNotFoundException e) { 
            throw e;
        }
        return false;
    } //method checkUsername
    
    public static boolean checkPassword( String username, String hashPassword )
            throws FileNotFoundException {
        
        // Checks if hashpassword matches hashed password in credentials file
        // for the given user
        File file = new File("credentials.txt");
        
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitString = line.split("\\s+");
                if ( splitString[0].equals( username ) ) { 
                    if ( line.contains ( hashPassword ) ) {
                        return true;
                    }
                }
            }
        } 
        catch(FileNotFoundException e) { 
            throw e;
        }
        return false;
    } // method checkPassword
    
    public static String getRole( String username, String hashPassword )
            throws FileNotFoundException {
        
        // Gets the role of the user by matching up username and password
        // in the credentials file, then returns the role from the file
        File file = new File("credentials.txt");
        
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitString = line.split("\\s+");
                if( splitString[0].equals( username ) ) {
                    if ( line.contains ( hashPassword ) ) {
                                return splitString[splitString.length -1];
                    }
                }
            }
        } 
        catch(FileNotFoundException e) { 
            throw e;
        }
        return null;
    }// method getRole
    
    public static String getUser() 
            throws FileNotFoundException {
        
        // Asks user to input a username, then checks if it is a valid username
        // from the credentials file, exits after 3 failed attempts
        Scanner scnr = new Scanner(System.in);
        int count = 0;
        String username = null;
        
        while( count < 3 ) {

            System.out.println("Username: ");
            username = scnr.nextLine();
            
            if ( checkUsername( username ) ) {
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
        return username;
        
    }//method getUser
    
    public static String getPassword( String username ) 
            throws FileNotFoundException, NoSuchAlgorithmException {
        
        // Asks user to input a password, then checks if it is a valid password
        // for the specified username from the credentials file
        Scanner scnr = new Scanner(System.in);
        String password;
        String hashPassword = null;
        int count = 0;
        
        while( count < 3 ) {
            
            System.out.println("Password: ");
            password = scnr.nextLine();
            hashPassword = convertPassword( password );
            
            if ( checkPassword( username, hashPassword) ) {
                return hashPassword;
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
        return hashPassword;
    }//method getPassword
    
}//class CredentialHandler
