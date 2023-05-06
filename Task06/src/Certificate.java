import java.time.LocalDate;

public class Certificate extends AbstractDocument {

    public String organizationDestination;

    public String nameSubscriber;

    public LocalDate endActionDate;

    public Certificate(String name, LocalDate createDate, String organization, String nameSub, LocalDate endDate) {
        super(name, createDate);
        organizationDestination = organization;
        nameSubscriber = nameSub;
        endActionDate = endDate;
    }

    @Override
    public void PrintDocument() {
        System.out.printf("Дата создания документа: %s;\nИмя документа %s;\n", createDate, name);
        System.out.printf("Имя организации куда направлена справка: %s;\n", organizationDestination);
        System.out.printf("Имя подписавщего:%s \n", nameSubscriber);
        System.out.printf("Действие справки заканчивается: %s\n\n", endActionDate.toString());
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
        return ValidateDate() && ValidateNameOrganization() && ValidateFullNameSub();
    }

    private boolean ValidateNameOrganization() {

        if (organizationDestination == null || organizationDestination.isEmpty())
            return false;

        char firstSymbol = organizationDestination.charAt(0);
        return organizationDestination.length() > 3 && Character.isUpperCase(firstSymbol);
    }

    private boolean ValidateDate() {
        return endActionDate.isAfter(createDate);
    }

    private boolean ValidateFullNameSub() {

        if (nameSubscriber == null || nameSubscriber.isEmpty())
            return false;

        char lastSymbol = nameSubscriber.charAt(nameSubscriber.length() - 1);

        if (lastSymbol != '.') {
            return false;
        }

        String[] names = nameSubscriber.split("\\.");

        if (names.length != 3) {
            return false;
        }

        for (String s : names) {
            if (!(CheckFirstSymbol(s) && ChecksContentString(s))) {
                return false;
            }
        }
        return true;
    }

    private boolean CheckFirstSymbol(String str) {
        char symbol = str.charAt(0);
        return Character.isUpperCase(symbol);
    }

    private boolean ChecksContentString(String str) {
        for (int i = 1; i < str.length(); i++) {
            char symbol = str.charAt(i);

            if (!(Character.isAlphabetic(symbol) && Character.isLowerCase(symbol))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 31 ^ 5 * this.createDate.hashCode() +
                31 ^ 4 * this.name.hashCode() +
                31 ^ 3 * this.endActionDate.hashCode() +
                31 ^ 2 * this.nameSubscriber.hashCode() +
                31 * this.organizationDestination.hashCode();
        return Math.abs(hashCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj.getClass() == Certificate.class) {
            Certificate that = (Certificate) obj;
            return this.organizationDestination.equals(that.organizationDestination)
                    && this.nameSubscriber.equals(that.nameSubscriber)
                    && this.endActionDate.isEqual(that.endActionDate)
                    && this.createDate.isEqual(that.createDate)
                    && this.name.equals(that.name);
        }

        return false;
    }


}
