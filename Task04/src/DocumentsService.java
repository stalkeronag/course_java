import java.time.LocalDate;
import java.util.Scanner;


public class DocumentsService {

    private DocumentsRepository repository;

    private Scanner scanner;

    public DocumentsService(DocumentsRepository repository) {
        scanner = new Scanner(System.in);
        this.repository = repository;
    }


    public void Start() {
        int command = 0;
        boolean isActive = true;
        while (isActive) {
            ShowHelp();
            command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case (1):
                    ShowNamesByDate();
                    break;
                case (2):
                    AddDocument();
                    break;
                case (3):
                    DeleteById();
                    break;
                case (4):
                    ApproveById();
                    break;
                case (5):
                    ShowApprovedDocs();
                    break;
                case (6):
                    isActive = false;
                    break;
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
        doc.SetStatus(Status.CREATED);
        doc.SetName(name);
        doc.SetText(text);
        doc.SetCreateDate(LocalDate.parse(date));
        int id = repository.SaveDocument(doc);
        System.out.println(String.format("id документа %d", id));

    }


    private void ShowNamesByDate() {
        System.out.println("Введите дату");
        String stringDate = scanner.next();
        scanner.nextLine();
        LocalDate date = LocalDate.parse(stringDate);
        String[] names = repository.GetDocumentsNamesByDate(date);
        System.out.println("Имена документов");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }

    private void DeleteById(){
        System.out.println("введите id");
        int id = scanner.nextInt();
        scanner.nextLine();
        repository.DeleteById(id);
    }

    private void ShowApprovedDocs() {
        Document[] docs = repository.GetDocumentsByStatus(Status.APPROVED);
        String name;
        String text;
        LocalDate createDate;
        int id;
        for (int i = 0; i < docs.length; i++) {
            name = docs[i].GetName();
            text = docs[i].GetText();
            id = docs[i].GetId();
            createDate = docs[i].GetCreateDate();
            System.out.println(String.format("name: %s text:%s createDate: %s id: %d", name, text, createDate, id));
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
