import java.time.LocalDate;

enum Status {
    CREATED,
    APPROVED
}

public class Document {
    private int id;

    private Status status;

    private LocalDate createDate;

    private String text;

    private String name;

    public int GetId() {
        return id;
    }

    public void SetId(int value) {
        id = value;
    }

    public Status GetStatus() {
        return status;
    }

    public void SetStatus(Status value) {
        status = value;
    }

    public LocalDate GetCreateDate() {
        return createDate;
    }

    public void SetCreateDate(LocalDate value) {
        createDate = value;
    }

    public String GetText() {
        return text;
    }

    public void SetText(String value) {
        text = value;
    }


    public String GetName() {
        return name;
    }

    public void SetName(String value) {
        name = value;
    }
}
