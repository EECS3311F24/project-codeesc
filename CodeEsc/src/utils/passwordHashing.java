package utils;
import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.BCrypt.*;

/**
 * This is a utility class used within the CodeEsc project for password
 * hashing and hash-checking to add extra security for user-made accounts within
 * the project.
 */
public class passwordHashing {
	//This is the amount of Salt Rounds used within the hashing algorithm for the passwords.
    private static int saltR = 10;
    
    /**
     * This method takes in a password as a parameter and then returns a hashed version
     * as a String using the BCrypt hashing algorithm.
     * 
     * @param pass   the password to be hashed
     * @return the hashed password
     */
    public static String hashPassword(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt(saltR));
    }
    
    /**
     * This method compares a normal password and a hashed password and then returns
     * whether the hashed password matches the normal password.
     * 
     * @param pass     the password to compare against the hashed password
     * @param hashed   the hashed password to compare against the password
     * @return true if the normal password and hashed password match and false otherwise
     */
    public static boolean passwordsMatch(String pass, String hashed) {
        return BCrypt.checkpw(pass, hashed);
    }
}
