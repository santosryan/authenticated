
public class Accounts {

	//instance variables
    private String var_username;
    private String var_hashpass;
    private String var_password;
    private String var_role;

    // empty Constructor
    public Accounts() {}

    // constructor with all three variables
    public Accounts(String username, String hashPassword, String password, String role){
        this.var_username = username;
        this.var_hashpass = hashPassword;
        this.var_password = password;
        this.var_role = role;
    }

    // setters (mutators)
    public void setUsername(String username) { this.var_username = username; }

    public void setHashPass(String hashPassword) { this.var_hashpass = hashPassword; }

    public void setPassword(String password) { this.var_password = password; }
    
    public void setRole(String role) { this.var_role = role; }

    // getters (accessors)
    public String getUsername() { return this.var_username; }

    public String getHashPass() { return this.var_hashpass; }

    public String getPassword() { return this.var_password; }
    
    public String getRole() { return this.var_role; }
}
