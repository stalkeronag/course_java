import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int digitSum = 0;
        int number;
        int minValue = -1;
        while ((number = scanner.nextInt()) != -1) {
            int temp = number;
            digitSum = 0;
            do {
                digitSum = digitSum + temp % 10;
                temp = temp / 10;
            } while (temp != 0);
            if (digitSum % 2 == 0) {
                if (number < minValue || minValue == -1) {
                    minValue = number;
                }
            }
        }
        System.out.println(minValue);
    }
}
