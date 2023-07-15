import java.time.LocalDate;

public interface Document {
    public String getNameDocument();

    public LocalDate getDateDocument();

    public boolean validateDocument();

    public void printDocument();
}
