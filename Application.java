public class Application {
    private static Spreadsheet[] _spreadsheets = new Spreadsheet[0];
    private static int _numSheets = 0;
    private Spreadsheet _activeSpreadsheet;

    public static void createNewSheet(int rows, int cols) {
        Spreadsheet newSheet = new Spreadsheet(rows, cols);
        addSpreadsheet(newSheet);
    }

    private static void addSpreadsheet(Spreadsheet spreadsheet) {
        if (_numSheets == _spreadsheets.length) {
            // If the array is full, create a new array with increased capacity
            Spreadsheet[] newSpreadsheets = new Spreadsheet[_numSheets + 1];
            System.arraycopy(_spreadsheets, 0, newSpreadsheets, 0, _numSheets);
            _spreadsheets = newSpreadsheets;
        }
        
        _spreadsheets[_numSheets++] = spreadsheet;
    }
    

    public static void main(String[] args ) {
        createNewSheet(5,5);
        createNewSheet(5,5);
        createNewSheet(5,5);
        System.out.println(_numSheets);
        System.out.println(_spreadsheets[2]);

    }
}