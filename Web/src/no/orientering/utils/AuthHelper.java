package no.orientering.utils;

import no.orientering.DAO.jdbc.UserDAO;
import no.orientering.models.User;

import com.sun.jersey.core.util.Base64;


/**
 * An helper class that shows how authentication clould be done.
 * 
 * @author Lars-Petter Helland
 * @author Atle Geitung
 */
public final class AuthHelper {

    /**
     * Hides the constructor for this class.
     */
    private AuthHelper() {
    }

    /**
     * Return true if the header contains the correct information about the user.
     * 
     * @param authHeader the header
     * @return true if authenticated
     */
    public static User AuthorizeFromHeader(final String authHeader) {
    	UserDAO users = new UserDAO();
        try {
            final String[] userPass = userPassFromAuthHeader(authHeader);

            return users.LogIn(userPass[0], userPass[1]);

        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * Utility method for authentication. It gets the password from the header.
     * 
     * @param authHeader header
     * @return password
     * @throws Base64DecodingException if the decoding failed
     */
    private static String[] userPassFromAuthHeader(final String authHeader){

        if (authHeader == null || !authHeader.startsWith("Basic")) {
            return null;
        }

        String header = authHeader;
        header = authHeader.split(" ")[1]; // Extract the credentials-part
        final String credentials = new String(Base64.decode(header));

        final String[] userPass = credentials.split(":");
        if (userPass.length != 2) {
            return null;
        }

        return userPass;
    }
}

