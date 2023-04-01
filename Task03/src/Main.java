import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int sizeArray = 1000000;
        int maxValueNumber = 100;
        int[] array = new int[sizeArray];
        int[] countArray = new int[maxValueNumber + 1];

        for (int i = 0; i < sizeArray; i++) {
            array[i] = (int) Math.abs(random.nextGaussian() * 5);
            countArray[array[i]] += 1;
        }

        int sampleSize = 10;
        int max;
        int indexMax;
        int[] indexArray = new int[sampleSize];
        int[] frequencyArray = new int[sampleSize];
        int[] heightArray = new int[sampleSize];
        int maxHeight = 10;

        for (int j = 0; j < sampleSize; j++) {
            max = countArray[0];
            indexMax = 0;

            for (int i = 0; i < maxValueNumber + 1; i++) {
                if (countArray[i] > max) {
                    max = countArray[i];
                    indexMax = i;
                }
            }

            countArray[indexMax] = -1;
            indexArray[j] = indexMax;
            frequencyArray[j] = max;
            heightArray[j] = (maxHeight * frequencyArray[j]) / frequencyArray[0];
        }
        System.out.println(frequencyArray[0]);
        int sizeBlock = 7;

        for (int i = 0; i < maxHeight; i++) {
            int currentHeight = maxHeight - i;

            for (int j = 0; j < sampleSize; j++) {
                if (currentHeight <= heightArray[j]) {
                    System.out.print("#      ");
                } else {
                    if (currentHeight == heightArray[j] + 1) {
                        int lengthNumber = 0;
                        int temp = frequencyArray[j];

                        do {
                            lengthNumber += 1;
                            temp = temp / 10;
                        } while (temp > 0);

                        System.out.print(frequencyArray[j]);

                        for (int k = 0; k < (sizeBlock - lengthNumber); k++) {
                            System.out.print(" ");
                        }

                    } else {
                        break;
                    }
                }

            }
            System.out.println();
        }

        for (int i = 0; i < sampleSize; i++) {
            int lengthNumber = 0;
            int temp = indexArray[i];

            do {
                lengthNumber += 1;
                temp = temp / 10;
            } while (temp > 0);

            System.out.print(indexArray[i]);

            for (int k = 0; k < (sizeBlock - lengthNumber); k++) {
                System.out.print(" ");
            }

        }

    }
}
