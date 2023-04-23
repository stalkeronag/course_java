import java.time.LocalDate;

public interface Document {
    public String GetNameDocument();

    public LocalDate GetDateDocument();

    public boolean ValidateDocument();

    public void PrintDocument();
}
