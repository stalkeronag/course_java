import java.util.ArrayList;
import java.util.Arrays;

public class DocumentsProcessor {

    private ArrayList<Document> docs;

    public DocumentsProcessor() {
        docs = new ArrayList<>();
    }

    public void AddInQueue(Document[] documents) {
        docs.addAll(Arrays.asList(documents));
    }

    public void Process(DocumentsProcessStrategy strategy) {
        Document[] documents = new Document[docs.size()];
        documents = docs.toArray(documents);
        strategy.Process(documents);
        docs.clear();
    }

}
