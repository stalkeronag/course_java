import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate createDate = LocalDate.parse("2001-10-21");
        LocalDate endDate = LocalDate.parse("2002-01-12");

        Document firstDoc = new Certificate("Some Text", createDate, "Konstan", "Kosta.Bar.Yuh.", endDate);
        Document secondDoc = new Certificate("Payload", createDate, "Remedy", "Vladimir.Cent.Rec.", endDate);
        Document thirdDoc = new Statement("Rare", createDate,"Stack");


        DocumentsProcessor processor = new DocumentsProcessor();

        Document[] documents = new Document[] {
                firstDoc,
                secondDoc,
                thirdDoc
        };

        processor.AddInQueue(documents);
        processor.Process(new DocumentsPrintStrategy());

        processor.AddInQueue(documents);
        processor.Process(new DocumentsValidationStrategy());

        //неверные документы

        LocalDate wrongEndDate = LocalDate.parse("1999-02-10");
        Document fourDoc = new Certificate("some Text", createDate, "konstan", "Kosta.Bar.Yuh.", endDate);
        Document fiveDoc = new Certificate("Payload", createDate, "Remedy", "vladimir.Cent.Rec", endDate);
        Document sixDoc = new Certificate("Time wrong",createDate,"Rust","Garik.Var.Alert",wrongEndDate);
        Document sevenDoc = new Statement("error", createDate,"slack");

        Document[] secondExampleDocuments = new Document[] {
                fourDoc,
                fiveDoc,
                sixDoc,
                sevenDoc
        };

        processor.AddInQueue(secondExampleDocuments);
        processor.Process(new DocumentsPrintStrategy());
        processor.AddInQueue(secondExampleDocuments);
        processor.Process(new DocumentsValidationStrategy());

    }



}