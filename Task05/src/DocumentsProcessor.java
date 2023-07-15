import java.util.ArrayList;
import java.util.Arrays;

public class DocumentsProcessor {

    private ArrayList<Document> docs;

    public DocumentsProcessor() {
        docs = new ArrayList<>();
    }

    public void addInQueue(Document[] documents) {
        docs.addAll(Arrays.asList(documents));
    }

    public void process(DocumentsProcessStrategy strategy) {
        Document[] documents = new Document[docs.size()];
        documents = docs.toArray(documents);
        strategy.process(documents);
        docs.clear();
    }

}
