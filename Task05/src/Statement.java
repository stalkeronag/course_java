import java.time.LocalDate;

public class Statement extends AbstractDocument {

    private String organizationSource;

    public Statement(String name, LocalDate createDate, String organization) {
        super(name, createDate);
        this.organizationSource = organization;
    }


    @Override
    public void printDocument() {
        System.out.printf("Имя выписки: %s;\nИмя организации выдавшая выписку: %s;\nВремя выдачи: %s;\n\n", name, organizationSource, createDate.toString());
    }

    @Override
    public String getNameDocument() {
        return name;
    }

    @Override
    public LocalDate getDateDocument() {
        return createDate;
    }

    @Override
    public boolean validateDocument() {

        if (organizationSource == null || organizationSource.isEmpty())
            return false;

        char firstSymbol = organizationSource.charAt(0);
        return organizationSource.length() > 3 && Character.isUpperCase(firstSymbol);
    }
}
