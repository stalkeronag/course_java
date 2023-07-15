import java.time.LocalDate;

public class Certificate extends AbstractDocument {

    private String organizationDestination;

    private String nameSubscriber;

    private LocalDate endActionDate;

    public Certificate(String name, LocalDate createDate, String organization, String nameSub, LocalDate endDate) {
        super(name, createDate);
        organizationDestination = organization;
        nameSubscriber = nameSub;
        endActionDate = endDate;
    }

    @Override
    public void printDocument() {
        System.out.printf("Дата создания документа: %s;\nИмя документа %s;\n", createDate, name);
        System.out.printf("Имя организации куда направлена справка: %s;\n", organizationDestination);
        System.out.printf("Имя подписавщего:%s \n", nameSubscriber);
        System.out.printf("Действие справки заканчивается: %s\n\n", endActionDate.toString());
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
        return validateDate() && validateNameOrganization() && validateFullNameSub();
    }

    private boolean validateNameOrganization() {

        if (organizationDestination == null || organizationDestination.isEmpty())
            return false;

        char firstSymbol = organizationDestination.charAt(0);
        return organizationDestination.length() > 3 && Character.isUpperCase(firstSymbol);
    }

    private boolean validateDate() {
        return endActionDate.isAfter(createDate);
    }

    private boolean validateFullNameSub() {

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
            if (!(checkFirstSymbol(s) && checksContentString(s))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkFirstSymbol(String str) {
        char symbol = str.charAt(0);
        return Character.isUpperCase(symbol);
    }

    private boolean checksContentString(String str) {
        for (int i = 1; i < str.length(); i++) {
            char symbol = str.charAt(i);

            if (!(Character.isAlphabetic(symbol) && Character.isLowerCase(symbol))) {
                return false;
            }
        }
        return true;
    }
}
