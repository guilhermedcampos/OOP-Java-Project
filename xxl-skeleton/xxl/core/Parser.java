package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.OutOfBoundsException;

/**
 * A class for parsing spreadsheet data from a file.
 */
public class Parser {

    private Spreadsheet _spreadsheet;

    /**
     * Constructs a new parser.
     */
    public Parser() {

    }

    /**
     * Constructs a new parser with a specified spreadsheet.
     *
     * @param spreadsheet the spreadsheet to parse data into.
     */
    public Parser(Spreadsheet spreadsheet) {
        _spreadsheet = spreadsheet;
    }

    /**
     * Parses a file and populates the spreadsheet with data.
     *
     * @param filename the name of the file to parse.
     * @return the populated spreadsheet.
     * @throws IOException                if there is an IO error.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     * @throws OutOfBoundsException       if the operation exceeds valid bounds.
     */
    Spreadsheet parseFile(String filename)
            throws IOException, UnrecognizedEntryException, OutOfBoundsException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            parseDimensions(reader);

            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }

        return _spreadsheet;
    }

    /**
     * Parses the dimensions of the spreadsheet from the input.
     *
     * @param reader the BufferedReader for reading input.
     * @throws IOException                if there is an IO error.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     */
    private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException {
        int rows = -1;
        int columns = -1;

        for (int i = 0; i < 2; i++) {
            String[] dimension = reader.readLine().split("=");
            if (dimension[0].equals("linhas"))
                rows = Integer.parseInt(dimension[1]);
            else if (dimension[0].equals("colunas"))
                columns = Integer.parseInt(dimension[1]);
            else
                throw new UnrecognizedEntryException("Formato inválido: " + dimension[0]);
        }

        if (rows <= 0 || columns <= 0)
            throw new UnrecognizedEntryException("Dimensões inválidas para a folha");
        _spreadsheet = new Spreadsheet(rows, columns);
        Calculator.setSpreadsheet(_spreadsheet);
    }

    /**
     * Parses a line of data from the input.
     *
     * @param line the line to parse.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     * @throws OutOfBoundsException       if the operation exceeds valid bounds.
     */
    private void parseLine(String line) throws UnrecognizedEntryException, OutOfBoundsException {
        String[] components = line.split("\\|");

        if (components.length == 1)
            return;

        if (components.length == 2) {
            String[] address = components[0].split(";");
            Content content = parseContent(components[1]);
            _spreadsheet.insert(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
        } else
            throw new UnrecognizedEntryException("Wrong format in line: " + line);
    }

    // parse the beginning of an expression
    Content parseContent(String contentSpecification)
            throws UnrecognizedEntryException {
        char c = contentSpecification.charAt(0);

        if (c == '=')
            return parseContentExpression(contentSpecification.substring(1));
        else
            return parseLiteral(contentSpecification);
    }

    /**
     * Parses a literal content from the input.
     *
     * @param literalExpression the literal expression to parse.
     * @return the parsed literal content.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     */
    private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
        if (literalExpression.charAt(0) == '\'') {
            // Remove the leading single quote (') to get the string value
            String stringValue = literalExpression.substring(1);
            return new LiteralString(stringValue);
        } else {
            try {
                int val = Integer.parseInt(literalExpression);
                return new LiteralInteger(val);
            } catch (NumberFormatException nfe) {
                throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
            }
        }
    }

    // contentSpecification is what comes after '='
    private Content parseContentExpression(String contentSpecification)
            throws UnrecognizedEntryException {
        if (contentSpecification.contains("("))
            return parseFunction(contentSpecification);
        // It is a reference
        String[] address = contentSpecification.split(";");
        return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
    }

    /**
     * Parses a content expression from the input.
     *
     * @param functionSpecification the function specification to parse.
     * @return the parsed content expression.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     */
    private Content parseFunction(String functionSpecification)
            throws UnrecognizedEntryException {
        String[] components = functionSpecification.split("[()]");
        if (components[1].contains(","))
            return parseBinaryFunction(components[0], components[1]);
        return parseIntervalFunction(components[0], components[1]);
    }

    /**
     * Parses a binary function from the input.
     *
     * @param functionName the name of the binary function.
     * @param args         the function arguments.
     * @return the parsed binary function.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     */
    private Content parseBinaryFunction(String functionName, String args)
            throws UnrecognizedEntryException {
        String[] arguments = args.split(",");
        Content arg0 = parseArgumentExpression(arguments[0]);
        Content arg1 = parseArgumentExpression(arguments[1]);

        Content result;

        switch (functionName) {
            case "ADD":
                result = new Add(arg0, arg1);
                break;
            case "SUB":
                result = new Sub(arg0, arg1);
                break;
            case "MUL":
                result = new Mul(arg0, arg1);
                break;
            case "DIV":
                result = new Div(arg0, arg1);
                break;
            default:
                throw new UnrecognizedEntryException("Função inválida: " + functionName);
        }
        return result;
    }

    /**
     * Parses an argument expression from the input.
     *
     * @param argExpression the argument expression to parse.
     * @return the parsed argument expression.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     */
    private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
        if (argExpression.contains(";") && argExpression.charAt(0) != '\'') {
            String[] address = argExpression.split(";");
            return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
            // pode ser diferente do anterior em parseContentExpression
        } else
            return parseLiteral(argExpression);
    }

    /**
     * Parses an interval function from the input.
     *
     * @param functionName     the name of the interval function.
     * @param rangeDescription the description of the range.
     * @return the parsed interval function.
     * @throws UnrecognizedEntryException if there is an unrecognized entry.
     */
    private Content parseIntervalFunction(String functionName, String rangeDescription)
            throws UnrecognizedEntryException {
        switch (functionName) {
            case "CONCAT":
                // return new Concat(range);
                return null;
            case "COASLECE":
                // return new Coaslece(range);
                return null;
            case "PRODUCT":
                // return new Product(range);
                return null;
            case "AVERAGE":
                // return new Average(range);
                return null;
            default:
                throw new UnrecognizedEntryException("Função inválida: " + functionName);
        }
    }
}
