import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Document firstCertificate = new Certificate("Ko",
                LocalDate.parse("2002-12-25"),
                "OAO", "Igor",
                LocalDate.parse("2003-10-05"));
        Document secondCertificate = new Certificate("Roman",
                LocalDate.parse("2001-10-25"),
                "OIOKN", "Igernrok",
                LocalDate.parse("2003-10-08"));
        Document thirdCertificate = new Certificate("Ko",
                LocalDate.parse("2002-12-25"),
                "OAO", "Igor",
                LocalDate.parse("2003-10-05"));
        Document fourCertificate = new Certificate("Roman",
                LocalDate.parse("2001-10-25"),
                "OIOKN", "Igernrok",
                LocalDate.parse("2003-10-08"));
        Document fiveDoc = new Statement("Rare", LocalDate.parse("2002-12-21"),"Stack");
        DocumentsRegistry registry = new DocumentsRegistry(16);
        registry.putDocument(firstCertificate, "green text");
        registry.putDocument(secondCertificate, "oao the better 2 rarity");
        System.out.println(registry.getDescription(firstCertificate));
        System.out.println(registry.getDescription(secondCertificate));
        registry.putDocument(thirdCertificate, "change firstCertificate description");
        System.out.println(registry.getDescription(thirdCertificate));
        System.out.println(registry.getDescription(firstCertificate));
        registry.putDocument(fourCertificate, "change secondCertificate description");
        System.out.println(registry.getDescription(secondCertificate));
        System.out.println(registry.getDescription(fourCertificate));
        System.out.println(registry.getDescription(fiveDoc));
    }
}