
package xxl.core;

import xxl.core.exception.EvaluationException;
import java.io.IOException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.exception.UnavailableFileException;

public class Main {
    public static void main(String[] args) {
        try {
            AbstractDataStructure _dataStructure = new MatrixDataStructure();
            Calculator c1 = new Calculator();
            c1.importFile("sample_spreadsheet.txt");
            c1.saveAs("teste");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}