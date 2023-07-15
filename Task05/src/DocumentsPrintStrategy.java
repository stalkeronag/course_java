public class DocumentsPrintStrategy implements DocumentsProcessStrategy {
    @Override
    public void process(Document[] documents) {
        for (Document document : documents) {
            document.printDocument();
        }
    }
}
