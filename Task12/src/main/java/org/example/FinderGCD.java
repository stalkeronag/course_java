package org.example;

public class FinderGCD {
    public int findGcd(int a, int b) {

        if (a == 0 || b == 0) {
            if (a != b){
                throw new IllegalArgumentException("no correct number");
            }
        }

        while (a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return a + b;
    }
}
