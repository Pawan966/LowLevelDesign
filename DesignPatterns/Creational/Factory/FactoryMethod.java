package Creational.Factory;

// Product Interface
interface ReportDocument {
    void generate(String data);
    void addHeader(String title);
    void addFooter();
    byte[] export();
}

// Concrete Products
class PdfReport implements ReportDocument {
    @Override
    public void generate(String data) {
        System.out.println("Generating PDF with data: " + data);
    }

    @Override
    public void addHeader(String title) {
        System.out.println("Adding PDF header: " + title);
    }

    @Override
    public void addFooter() {
        System.out.println("Adding PDF footer with page numbers");
    }

    @Override
    public byte[] export() {
        System.out.println("Exporting as .pdf");
        return new byte[0];
    }
}

class ExcelReport implements ReportDocument {
    @Override
    public void generate(String data) {
        System.out.println("Generating Excel with data: " + data);
    }

    @Override
    public void addHeader(String title) {
        System.out.println("Adding Excel sheet header: " + title);
    }

    @Override
    public void addFooter() {
        System.out.println("Adding Excel summary row");
    }

    @Override
    public byte[] export() {
        System.out.println("Exporting as .xlsx");
        return new byte[0];
    }
}

// Abstract Creator — defines the TEMPLATE + factory method
abstract class ReportGenerator {

    // 🔑 THIS is the Factory Method — subclasses decide what to create
    protected abstract ReportDocument createReport();

    // Template that uses the factory method
    public byte[] generateReport(String title, String data) {
        ReportDocument report = createReport();  // ← factory method call
        report.addHeader(title);
        report.generate(data);
        report.addFooter();
        return report.export();
    }
}

// Concrete Creators
class PdfReportGenerator extends ReportGenerator {
    @Override
    protected ReportDocument createReport() {
        return new PdfReport();
    }
}

class ExcelReportGenerator extends ReportGenerator {
    @Override
    protected ReportDocument createReport() {
        return new ExcelReport();
    }
}

// Client Code
class AccountingModule {

    public void exportMonthlyReport(String format) {

        ReportGenerator generator = switch (format.toUpperCase()) {
            case "PDF"   -> new PdfReportGenerator();
            case "EXCEL" -> new ExcelReportGenerator();
            default -> throw new IllegalArgumentException("Unsupported: " + format);
        };

        byte[] report = generator.generateReport(
                "Monthly Accounting Report - Nov 2025",
                "Revenue: $1.2M, Expenses: $800K"
        );
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        AccountingModule module = new AccountingModule();
        module.exportMonthlyReport("PDF");
    }
}
