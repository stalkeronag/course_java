import java.time.LocalDate;
import java.util.Scanner;


public class DocumentsService {

    private final DocumentsRepository repository;

    private final Scanner scanner;

    public DocumentsService(DocumentsRepository repository) {
        scanner = new Scanner(System.in);
        this.repository = repository;
    }


    public void start() {
        int command;
        boolean isActive = true;
        while (isActive) {
            showHelp();
            command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case (1) -> showNamesByDate();
                case (2) -> addDocument();
                case (3) -> deleteById();
                case (4) -> approveById();
                case (5) -> ShowApprovedDocs();
                case (6) -> isActive = false;
            }
        }
    }

    private void approveById() {
        System.out.println("введите id документа");
        int id = scanner.nextInt();
        scanner.nextLine();
        repository.updateStatusById(id);
    }

    private void addDocument() {
        System.out.println("введите название документа");
        String name = scanner.nextLine();
        System.out.println("введите текст документа");
        String text = scanner.nextLine();
        System.out.println("введите дату документа YYYY-MM-DD");
        String date = scanner.nextLine();
        Document doc = new Document();
        doc.setStatus(Document.Status.CREATED);
        doc.setName(name);
        doc.setText(text);
        doc.setCreateDate(LocalDate.parse(date));
        int id = repository.saveDocument(doc);
        System.out.printf("id документа %d%n", id);

    }


    private void showNamesByDate() {
        System.out.println("Введите дату");
        String stringDate = scanner.next();
        scanner.nextLine();
        LocalDate date = LocalDate.parse(stringDate);
        String[] names = repository.getDocumentsNamesByDate(date);
        System.out.println("Имена документов");
        for (String name : names) {
            System.out.println(name);
        }
    }

    private void deleteById() {
        System.out.println("введите id");
        int id = scanner.nextInt();
        scanner.nextLine();
        repository.deleteById(id);
    }

    private void ShowApprovedDocs() {
        Document[] docs = repository.getDocumentsByStatus(Document.Status.APPROVED);
        String name;
        String text;
        LocalDate createDate;
        int id;
        for (Document doc : docs) {
            name = doc.getName();
            text = doc.getText();
            id = doc.getId();
            createDate = doc.getCreateDate();
            System.out.printf("name: %s text:%s createDate: %s id: %d%n", name, text, createDate, id);
        }
    }

    private void showHelp() {
        System.out.println("1. Вывести название документов конкретной даты");
        System.out.println("2. Добавить документ в хранилище");
        System.out.println("3. Удалить документ по идентификатору");
        System.out.println("4. Подтвердить статус документа по идентификатору");
        System.out.println("5. Вывести все подтвержденные документы");
        System.out.println("6. Завершить работу");
    }


}
