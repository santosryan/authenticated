import java.io.*;

public class FileHandler {
    
    public static String readFile(String name)
            throws Exception, IOException {
        
        // Reads file that is given to the method and returns the read contents
        // as a string.
        if (name == null) {
            throw new Exception("File name cannot be NULL.");
        }
        else {
            File rawHandle = null;
            
            String line = null;
            StringWriter fileContents = null;
            
            FileReader fileHandle = null;
            BufferedReader reader = null;
            
            try {
                rawHandle = new File(name);
                
                if( !rawHandle.exists() ) {
                    throw new IOException("File " + name + " not found.");
                }
                else if ( !rawHandle.canRead() ) {
                    throw new IOException("Cannot read file " + name + ".");
                }
                else {
                    fileHandle = new FileReader(name);
                    reader = new BufferedReader(fileHandle);
                    
                    fileContents = new StringWriter();
                    
                    while( ( line = reader.readLine() ) != null ) {
                        fileContents.append(line);
                        fileContents.append('\n');
                    }
                }
            }
            catch ( IOException ioe ) {
                throw ioe;
            }
            catch ( Exception e ) {
                throw e;
            }
            finally {
                if ( reader != null ) {
                    reader.close();
                }
            }
            
            return ( fileContents == null ) ? null : fileContents.toString();
        }
    } //method readFile
    
} //class FileHandler
