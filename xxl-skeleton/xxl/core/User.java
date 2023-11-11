package xxl.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a user in the system with a unique username and a collection of
 * spreadsheets.
 */
public class User implements java.io.Serializable {

    /** 
     * The unique username of the user. 
     */
    private final String _name;

    /** 
     * The list of spreadsheets associated with the user. 
     */
    private List<Spreadsheet> _spreadsheets;

    /**
     * Constructs a new User with the specified username.
     *
     * @param name the unique username of the user.
     */
    public User(String name) {
        _name = name;
        _spreadsheets = new ArrayList<>();
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return _name;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user.
     */
    public final String getName() {
        return _name;
    }

    /**
     * Adds a spreadsheet to the user's collection.
     *
     * @param spreadsheet the spreadsheet to be added.
     */
    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }

    /**
     * Gets an unmodifiable list of spreadsheets associated with the user.
     *
     * @return an unmodifiable list of spreadsheets.
     */
    public List<Spreadsheet> getSpreadsheets() {
        return Collections.unmodifiableList(_spreadsheets);
    }
}