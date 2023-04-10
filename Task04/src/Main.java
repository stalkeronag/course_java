public class Main {
    public static void main(String[] args) {
        DocumentsRepository repository = new DocumentsRepository();
        DocumentsService service = new DocumentsService(repository);
        service.Start();
    }
}