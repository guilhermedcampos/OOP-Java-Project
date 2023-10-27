package xxl.core;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a user in the system with a unique username and a collection of
 * spreadsheets.
 */
public class User {
    private String _name;
    private Set<Spreadsheet> _spreadsheets;

    public User(String name) {
        _name = name;
        _spreadsheets = new HashSet<>();
    }

    public String getUsername() {
        return _name;
    }

    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(_name, user._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name);
    }
}