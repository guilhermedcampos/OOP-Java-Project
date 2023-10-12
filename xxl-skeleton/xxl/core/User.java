package xxl.core;
import java.util.*;

/**
 * Represents a user in the system with a unique username and a collection of spreadsheets.
 */
public class User {
    private String _name;
    private Set<Spreadsheet> _spreadsheets;

    /**
     * Constructs a new User with the specified username and an empty collection of spreadsheets.
     *
     * @param name the unique username of the user.
     */
    public User(String name) {
        this._name = name;
        this._spreadsheets = new HashSet<>();
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the unique username of the user.
     */
    public String getUsername() {
        return _name;
    }

    /**
     * Adds a spreadsheet to the user's collection.
     *
     * @param spreadsheet the spreadsheet to be added to the user's collection.
     */
    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }

    /**
     * Compares this user to another object for equality based on their usernames.
     *
     * @param o the object to compare to this user.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(_name, user._name);
    }

    /**
     * Computes a hash code for the user based on their username.
     *
     * @return the hash code value for this user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(_name);
    }
}
