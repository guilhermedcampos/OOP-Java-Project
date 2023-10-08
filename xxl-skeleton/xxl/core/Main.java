package xxl.core;
import xxl.core.exception.EvaluationException;
import java.io.IOException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.OutOfBoundsException;

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
            Calculator c2 = new Calculator();
            c2.setSpreadsheet(spreadsheet);
            
            // You can now work with the parsed spreadsheet as needed.
            // For example, you can print the values or perform computations.

            // Print the value at cell (1,1)
            Cell cell11 = spreadsheet.getCell(1, 1);
            System.out.println(cell11.getContent().value().asString()); // Should print 'Hello'
            
            // Print the value at cell (3,3) which contains the result of the ADD function
            Cell cell33 = spreadsheet.getCell(3, 3);
            System.out.println(cell33.getContent().value().asInt()); // Should print 11 (5 + 6)

            // You can continue testing other functionality.
        } catch (IOException | UnrecognizedEntryException | EvaluationException | OutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
