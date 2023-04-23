import java.time.LocalDate;
import java.util.Scanner;


public class DocumentsService {

    private final DocumentsRepository repository;

    private final Scanner scanner;

    public DocumentsService(DocumentsRepository repository) {
        scanner = new Scanner(System.in);
        this.repository = repository;
    }


    public void Start() {
        int command;
        boolean isActive = true;
        while (isActive) {
            ShowHelp();
            command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case (1) -> ShowNamesByDate();
                case (2) -> AddDocument();
                case (3) -> DeleteById();
                case (4) -> ApproveById();
                case (5) -> ShowApprovedDocs();
                case (6) -> isActive = false;
            }
        }
    }

    private void ApproveById() {
        System.out.println("введите id документа");
        int id = scanner.nextInt();
        scanner.nextLine();
        repository.UpdateStatusById(id);
    }

    private void AddDocument() {
        System.out.println("введите название документа");
        String name = scanner.nextLine();
        System.out.println("введите текст документа");
        String text = scanner.nextLine();
        System.out.println("введите дату документа YYYY-MM-DD");
        String date = scanner.nextLine();
        Document doc = new Document();
        doc.SetStatus(Document.Status.CREATED);
        doc.SetName(name);
        doc.SetText(text);
        doc.SetCreateDate(LocalDate.parse(date));
        int id = repository.SaveDocument(doc);
        System.out.printf("id документа %d%n", id);

    }


    private void ShowNamesByDate() {
        System.out.println("Введите дату");
        String stringDate = scanner.next();
        scanner.nextLine();
        LocalDate date = LocalDate.parse(stringDate);
        String[] names = repository.GetDocumentsNamesByDate(date);
        System.out.println("Имена документов");
        for (String name : names) {
            System.out.println(name);
        }
    }

    private void DeleteById() {
        System.out.println("введите id");
        int id = scanner.nextInt();
        scanner.nextLine();
        repository.DeleteById(id);
    }

    private void ShowApprovedDocs() {
        Document[] docs = repository.GetDocumentsByStatus(Document.Status.APPROVED);
        String name;
        String text;
        LocalDate createDate;
        int id;
        for (Document doc : docs) {
            name = doc.GetName();
            text = doc.GetText();
            id = doc.GetId();
            createDate = doc.GetCreateDate();
            System.out.printf("name: %s text:%s createDate: %s id: %d%n", name, text, createDate, id);
        }
    }

    private void ShowHelp() {
        System.out.println("1. Вывести название документов конкретной даты");
        System.out.println("2. Добавить документ в хранилище");
        System.out.println("3. Удалить документ по идентификатору");
        System.out.println("4. Подтвердить статус документа по идентификатору");
        System.out.println("5. Вывести все подтвержденные документы");
        System.out.println("6. Завершить работу");
    }


}
