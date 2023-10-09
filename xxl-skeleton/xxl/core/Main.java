package xxl.core;
import xxl.core.exception.EvaluationException;
import java.io.IOException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.OutOfBoundsException;
import xxl.core.exception.UnavailableFileException;

public class Main{
    public static void main(String[] args) {
        /*
        try {
            testSpreadsheet();
        } catch (EvaluationException e) {
            e.printStackTrace();
        }
    }

    private static void testSpreadsheet() throws EvaluationException {
        Spreadsheet s1 = new Spreadsheet(10, 10);
        Calculator c1 = new Calculator();
        c1.setSpreadsheet(s1);

        Reference r23 = new Reference(2, 3);

        Content l1 = new LiteralInteger(2);
        Content l2 = new LiteralInteger(3);
        s1.insert(2, 3, l1);
        System.out.println(s1.getCell(2, 3));
        System.out.println(s1.getCell(2, 3).getContent().value().asInt());
        System.out.println(r23.value().toString());
        System.out.println(new Add(l1,l2).compute().value());
        System.out.println(new Sub(l1,l2).compute().value());
        */
        try {
            Parser parser = new Parser();
            Spreadsheet spreadsheet = parser.parseFile("sample_spreadsheet.txt");

            //calculator.importFile("sample_spreadsheet.txt"); // Provide the correct file path or name
        } catch (IOException | UnrecognizedEntryException | EvaluationException | OutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
