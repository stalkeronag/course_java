public class DocumentsValidationStrategy implements DocumentsProcessStrategy {

    @Override
    public void process(Document[] documents) {
        for (Document document : documents) {
            boolean resultValidation = document.validateDocument();
            if (resultValidation) {
                System.out.printf("document: %s валидный \n", document.getNameDocument());
            } else {
                System.out.printf("document: %s невалидный \n", document.getNameDocument());
            }
        }
    }
}
