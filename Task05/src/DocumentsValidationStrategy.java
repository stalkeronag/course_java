public class DocumentsValidationStrategy implements DocumentsProcessStrategy {

    @Override
    public void Process(Document[] documents) {
        for (Document document : documents) {
            boolean resultValidation = document.ValidateDocument();
            if (resultValidation) {
                System.out.printf("document: %s валидный \n", document.GetNameDocument());
            } else {
                System.out.printf("document: %s невалидный \n", document.GetNameDocument());
            }
        }
    }
}
