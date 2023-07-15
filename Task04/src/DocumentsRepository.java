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

    private void rebuildKeysForDelete(int index) {
        sizeKeys = sizeKeys - 1;

        for (int i = index; i < sizeKeys; i++) {
            keys[i] = keys[i + 1];
        }

        keys[sizeKeys] = -1;
    }

    private void addKey(int key) {
        keys[sizeKeys] = key;
        sizeKeys = sizeKeys + 1;
    }

    private int generateId() {
        Random random = new Random();
        return random.nextInt(capacity);
    }

    public int saveDocument(Document doc) {

        if (sizeKeys == documents.length) {
            throw new IndexOutOfBoundsException("нету места для документа");
        }

        int id = generateId();

        while (documents[id] != null) {
            id = generateId();
        }

        doc.setId(id);
        addKey(id);
        documents[id] = doc;
        return id;
    }

    public void deleteById(int id) {
        documents[id] = null;
        int indexKey;

        for (int i = 0; i < sizeKeys; i++) {
            if (keys[i] == id) {
                indexKey = i;
                rebuildKeysForDelete(indexKey);
                break;
            }
        }


    }

    public String[] getDocumentsNamesByDate(LocalDate date) {
        int count = 0;

        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].getCreateDate().isEqual(date)) {
                count++;
            }
        }

        String[] temp = new String[count];
        int currentPos = 0;
        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].getCreateDate().isEqual(date)) {
                temp[currentPos] = documents[keys[i]].getName();
                currentPos++;
            }
        }

        return temp;
    }

    public Document[] getDocumentsByStatus(Document.Status status) {
        int count = 0;

        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].getStatus() == status) {
                count++;
            }
        }

        Document[] temp = new Document[count];
        int currentPos = 0;
        for (int i = 0; i < sizeKeys; i++) {
            if (documents[keys[i]].getStatus() == status) {
                temp[currentPos] = documents[keys[i]];
                currentPos++;
            }
        }

        return temp;
    }

    public void updateStatusById(int id) {

        for (int i = 0; i < sizeKeys; i++) {
            if (keys[i] == id) {
                documents[keys[i]].setStatus(Document.Status.APPROVED);
            }
        }
    }


}
