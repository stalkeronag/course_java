public class DocumentsRegistry {

    private class Entity {

        public Entity(Object doc, String descr) {
            document = doc;
            description = descr;
        }


        public Object document;

        public String description;
    }

    private int size;

    private Entity[][] arrayEntity;

    public DocumentsRegistry(int size) {
        this.size = size;
        arrayEntity = new Entity[size][size];
    }

    public void putDocument(Object document, String description) {
        int hashCode = document.hashCode();
        int numberOfString = hashCode & (size - 1);

        for (int i = 0; i < size; i++) {
            if (arrayEntity[numberOfString][i] == null) {
                arrayEntity[numberOfString][i] = new Entity(document, description);
                return;
            }

            Object objectDocument = arrayEntity[numberOfString][i].document;
            int hashCodeCell = objectDocument.hashCode();

            if (hashCodeCell == hashCode) {
                if (objectDocument.equals(document)) {
                    arrayEntity[numberOfString][i] = new Entity(document, description);
                    return;
                }
            }
        }

    }

    public String getDescription(Object document) {
        int hashCode = document.hashCode();
        int numberOfString = hashCode & (size - 1);


        for (int i = 0; i < size; i++) {

            if (arrayEntity[numberOfString][i] == null) {
                return "None";
            } else {
                Object objectDocument = arrayEntity[numberOfString][i].document;
                int hashCodeCell = objectDocument.hashCode();
                if (hashCodeCell == hashCode) {
                    if (objectDocument.equals(document)) {
                        return arrayEntity[numberOfString][i].description;
                    }
                }
            }


        }

        return "None";
    }

}
