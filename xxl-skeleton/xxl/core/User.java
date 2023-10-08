package xxl.core;
import java.util.*;

public class User {
    private String _name;
    private Set<Spreadsheet> _spreadsheets;

    public User(String name) {
        this._name = name;
        this._spreadsheets = new HashSet<>();
    }

    // Getter for username
    public String getUsername() {
        return _name;
    }

    // Add a spreadsheet to the user's collection
    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }

    // Equals method to compare two users by username
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(_name, user._name);
    }

    // Hash code method based on username
    @Override
    public int hashCode() {
        return Objects.hash(_name);
    }

}