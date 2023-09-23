public class Application {
    private static Spreadsheet[] _spreadsheets = new Spreadsheet[0];
    private static int _numSheets = 0;
    private static Spreadsheet _activeSpreadsheet;

    public static void createNewSheet(int rows, int cols) {
        Spreadsheet newSheet = new Spreadsheet(rows, cols);
        addSpreadsheet(newSheet);
    }

    public static void setActive (Spreadsheet spreadsheet) {
        _activeSpreadsheet = spreadsheet;  
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
        

        setActive(_spreadsheets[0]); 
        System.out.println(_activeSpreadsheet);

        Cell b1 = new Cell(1,2);
        Cell c1 = new Cell(1,3);
        Range r1 = new Range(b1,c1);


        _activeSpreadsheet.insertContent(r1,"oi");
        System.out.println(_activeSpreadsheet.getCells());
        

    }
}