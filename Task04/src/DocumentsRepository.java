import java.time.LocalDate;
import java.util.Random;

public class DocumentsRepository {

    private final int[] keys;

    private int sizeKeys;

    private final int capacity;

    private final Document[] documents;

    public DocumentsRepository() {
        capacity = 10000;
        documents = new Document[capacity];
        keys = new int[capacity];
        sizeKeys = 0;
    }

    private void RebuildKeysForDelete(int index) {
        sizeKeys = sizeKeys - 1;

        for (int i = index; i < sizeKeys; i++) {
            keys[i] = keys[i + 1];
        }

        keys[sizeKeys] = -1;
    }

    private void AddKey(int key) {
        keys[sizeKeys] = key;
        sizeKeys = sizeKeys + 1;
    }

    private int GenerateId() {
        Random random = new Random();
        return random.nextInt(capacity);
    }

    public int SaveDocument(Document doc) {

        if (sizeKeys == documents.length) {
            throw new IndexOutOfBoundsException("нету места для документа");
        }

        int id = GenerateId();

        while (documents[id] != null) {
            id = GenerateId();
        }

        doc.SetId(id);
        AddKey(id);
        documents[id] = doc;
        return id;
    }

    public void DeleteById(int id) {
        documents[id] = null;
        int indexKey;

        for (int i = 0; i < sizeKeys; i++) {
            if (keys[i] == id) {
                indexKey = i;
                RebuildKeysForDelete(indexKey);
                break;
            }
        }


    }

    public String[] GetDocumentsNamesByDate(LocalDate date) {
        int count = 0;

        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].GetCreateDate().isEqual(date)) {
                count++;
            }
        }

        String[] temp = new String[count];
        int currentPos = 0;
        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].GetCreateDate().isEqual(date)) {
                temp[currentPos] = documents[keys[i]].GetName();
                currentPos++;
            }
        }

        return temp;
    }

    public Document[] GetDocumentsByStatus(Status status) {
        int count = 0;

        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].GetStatus() == status) {
                count++;
            }
        }

        Document[] temp = new Document[count];
        int currentPos = 0;
        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].GetStatus() == status) {
                temp[currentPos] = documents[keys[i]];
                currentPos++;
            }
        }

        return temp;
    }

    public void UpdateStatusById(int id) {

        for (int i = 0; i < sizeKeys; i++) {
            if (keys[i] == id) {
                documents[keys[i]].SetStatus(Status.APPROVED);
            }
        }
    }


}
