public class Main {
    public static void main(String[] args) {
        int number = 12345; // гарантируется 10000 <= number <= 99999
        int lastDigit = 5;
        double average;
        int digitsSum = 0;
        boolean withLastDigit;
        int temp = number;
        digitsSum = digitsSum + temp % 10;
        temp = temp / 10;
        digitsSum = digitsSum + temp % 10;
        temp = temp / 10;
        digitsSum = digitsSum + temp % 10;
        temp = temp / 10;
        digitsSum = digitsSum + temp % 10;
        temp = temp / 10;
        digitsSum = digitsSum + temp % 10;
        average = digitsSum / 5;
        withLastDigit = number % 10 == lastDigit;
        System.out.println("The average value of the digits of a number <" +
                number + "> = " + average);
        System.out.println("The sum of the digits of a number <" +
                number + "> = " + digitsSum);
        System.out.println("Number <" +
                number + "> ends with a number <" +
                lastDigit + "> ? Answer = " + withLastDigit);
    }
}