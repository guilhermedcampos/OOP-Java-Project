package xxl.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a user in the system with a unique username and a collection of
 * spreadsheets.
 */
public class User implements java.io.Serializable {
    private final String _name;
    private List<Spreadsheet> _spreadsheets;

    public User(String name) {
        _name = name;
        _spreadsheets = new ArrayList<>();
    }

    public String getUsername() {
        return _name;
    }

    public final String getName() {
        return _name;
    }

    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }

    public List<Spreadsheet> getSpreadsheets() {
        return Collections.unmodifiableList(_spreadsheets);
    }
}