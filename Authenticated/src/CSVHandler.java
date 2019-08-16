import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVHandler {
	
	private static final String COMMA_DELIMITER = ",";

	public static ArrayList<Accounts> readFromCSV(String fileName) throws Exception {
        ArrayList<Accounts> accountList = new ArrayList<>();

        if (fileName == null) {
            throw new Exception("File name cannot be NULL.");
        }
        else {
            File rawHandle = null;
            FileReader fileHandle;
            BufferedReader br = null;
            
            try {
                rawHandle = new File(fileName);
                
                if( !rawHandle.exists() ) {
                    throw new IOException("File " + fileName + " not found.");
                }
                else if ( !rawHandle.canRead() ) {
                    throw new IOException("Cannot read file " + fileName + ".");
                }
                else {
                	
                	// create an instance of BufferedReader
                    fileHandle = new FileReader(fileName);
                    br = new BufferedReader(fileHandle);
                    
			        // read the first line from the text file
			        String line = br.readLine();
			
			            // loop until all lines are read
			            while (line != null) {
			
			                // Loads string array with values from every line using a comma as the delimiter
			                String[] accountValues = line.split(COMMA_DELIMITER);
			
			                Accounts account = createAccount(accountValues);
			
			                // adds account to the arraylist
			                accountList.add(account);
			
			                // read next line before looping, and if end of file reached, line would be null
			                line = br.readLine();
			            }
			
			        }
                }
                catch (IOException ioe) {
			            ioe.printStackTrace();
			        }
                catch ( Exception e ) {
                    throw e;
                }
                finally {
                    if ( br != null ) {
                        br.close();
                    }
                }
            }

        return accountList;
    }
	
    private static Accounts createAccount(String[] metadata) {
        String username = metadata[0];
        String hashPass = metadata[1];
        String password = metadata[2];
        String role = metadata[3];

        // create and return book of this metadata
        return new Accounts(username, hashPass, password, role);
    }

}
