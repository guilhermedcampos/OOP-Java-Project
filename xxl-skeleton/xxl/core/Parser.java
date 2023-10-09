package xxl.core;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;

import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.exception.EvaluationException;
import xxl.core.exception.OutOfBoundsException;

public class Parser {

  private Spreadsheet _spreadsheet;
  private AbstractDataStructure _dataStructure;

  public Parser() {

  }

  public Parser(AbstractDataStructure dataStructure) {
       _dataStructure = dataStructure;
  }

  public Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException, EvaluationException, OutOfBoundsException /* More Exceptions? */ {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);

      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }

    return _spreadsheet;
  }

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

    _spreadsheet = new Spreadsheet(rows, columns, _dataStructure);
  }

  private void parseLine(String line) throws UnrecognizedEntryException, EvaluationException, OutOfBoundsException /*, more exceptions? */{
    String[] components = line.split("\\|");

    if (components.length == 1) // do nothing
      return;
    
    if (components.length == 2) {
      String[] address = components[0].split(";");
      Content content = parseContent(components[1]);
      _spreadsheet.insert(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  Content parseContent(String contentSpecification) throws UnrecognizedEntryException, EvaluationException, OutOfBoundsException {
    char c = contentSpecification.charAt(0);

    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

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
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException, EvaluationException, OutOfBoundsException /*more exceptions */ {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException, EvaluationException, OutOfBoundsException /*more exceptions */ {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);
    return parseIntervalFunction(components[0], components[1]);
  }

    private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException, EvaluationException, OutOfBoundsException {
        String[] arguments = args.split(",");
        Content arg0 = parseArgumentExpression(arguments[0]);
        Content arg1 = parseArgumentExpression(arguments[1]);
        
        Content result;
        
        switch (functionName) {
            case "ADD":
                result = new Add(arg0, arg1).compute();
                break;
                /*
            case "SUB":
                result = new Sub(arg0, arg1);
                break;
            case "MUL":
                result = new Mul(arg0, arg1);
                break;
            case "DIV":
                result = new Div(arg0, arg1);
                break;
                */
            default:
                throw new UnrecognizedEntryException("Função inválida: " + functionName);
        }
        return result;
    }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
      // pode ser diferente do anterior em parseContentExpression
    } else
      return parseLiteral(argExpression);
  }
  
  
  private Content parseIntervalFunction(String functionName, String rangeDescription) throws UnrecognizedEntryException {
      //Range range = Range.buildRange(rangeDescription);

      switch (functionName) {
          case "CONCAT":
              //return new Concat(range).compute();
              return null; // Just return a Content placeholder for now
          case "COASLECE":
              //return new Coaslece(range).compute();
              return null; // Just return a Content placeholder for now
          case "PRODUCT":
              //return new Product(range).compute();
              return null; // Just return a Content placeholder for now
          case "AVERAGE":
              //return new Average(range).compute();
              return null; // Just return a Content placeholder for now
          default:
              throw new UnrecognizedEntryException("Função inválida: " + functionName);
      }
  }

}
