import java.time.LocalDate;

public class Statement extends AbstractDocument {

    private String organizationSource;

    public Statement(String name, LocalDate createDate, String organization) {
        super(name, createDate);
        this.organizationSource = organization;
    }


    @Override
    public void PrintDocument() {
        System.out.printf("Имя выписки: %s;\nИмя организации выдавшая выписку: %s;\nВремя выдачи: %s;\n\n", name, organizationSource, createDate.toString());
    }

    @Override
    public String GetNameDocument() {
        return name;
    }

    @Override
    public LocalDate GetDateDocument() {
        return createDate;
    }

    @Override
    public boolean ValidateDocument() {

        if (organizationSource == null || organizationSource.isEmpty())
            return false;

        char firstSymbol = organizationSource.charAt(0);
        return organizationSource.length() > 3 && Character.isUpperCase(firstSymbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj.getClass() == Statement.class) {
            Statement that = (Statement) obj;
            return this.organizationSource.equals(that.organizationSource)
                    && this.createDate.isEqual(that.createDate)
                    && this.name.equals(that.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 31 ^ 3 * this.name.hashCode() +
                31 ^ 2 * this.createDate.hashCode() +
                31 * this.organizationSource.hashCode();
        return Math.abs(hashCode);
    }
}

