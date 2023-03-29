public class Main {
    public static void main(String[] args) {
        int number = 12345; // гарантируется -10000 <= number <= 99999
        int opposite;
        opposite = (-1) * number;
        System.out.println("Противоположное значение <" +
                number + "> = " + opposite);
    }
}
