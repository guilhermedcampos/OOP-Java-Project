package xxl.core;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator c1 = new Calculator();
            c1.importFile("sample_spreadsheet.txt");
            c1.saveAs("teste");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
