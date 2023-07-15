import java.time.LocalDate;

public abstract class AbstractDocument implements Document {

    protected String name;

    protected LocalDate createDate;

    public AbstractDocument(String name, LocalDate createDate){
        this.name = name;
        this.createDate = createDate;
    }

    public abstract void printDocument();

    public abstract boolean validateDocument();
}
