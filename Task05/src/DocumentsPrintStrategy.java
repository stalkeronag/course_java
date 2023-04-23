public class DocumentsPrintStrategy implements DocumentsProcessStrategy {
    @Override
    public void Process(Document[] documents) {
        for (Document document : documents) {
            document.PrintDocument();
        }
    }
}
