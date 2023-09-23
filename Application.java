public class Application {
    private Spreadsheet[] _spreadsheets;
    private int _numSheets = 0;
    private Spreadsheet _activeSpreadsheet;

    public void createNewSheet(int rows, int cols) {
        SpreadSheet newSheet = new SpreadSheet(rows, cols);
        addSpreadsheet(newSheet);
    }

    private void addSpreadsheet(SpreadSheet spreadsheet) {
        if (_numSheets == _spreadsheets.length) {
            // If the array is full, create a new array with increased capacity
            SpreadSheet[] newSpreadsheets = new SpreadSheet[_numSheets + 1];
            System.arraycopy(_spreadsheets, 0, newSpreadsheets, 0, _numSheets);
            _spreadsheets = newSpreadsheets;
        }
        
        _spreadsheets[_numSheets++] = spreadsheet;
    }
    
}