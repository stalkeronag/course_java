import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        String path = "example.txt";
        String firstDocument;
        String secondDocument;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            firstDocument = bufferedReader.readLine();
            secondDocument = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashSet<String> setWordsFirstDocument = new HashSet<>();
        HashSet<String> setWordsSecondDocument = new HashSet<>();
        String[] wordsFirstDocument = firstDocument.split(" ");
        String[] wordsSecondDocument = secondDocument.split(" ");
        Collections.addAll(setWordsFirstDocument, wordsFirstDocument);
        Collections.addAll(setWordsSecondDocument, wordsSecondDocument);
        HashMap<String, Integer> mapFrequenciesFirstWords = new HashMap<>();
        HashMap<String, Integer> mapFrequenciesSecondWords = new HashMap<>();
        HashMap<String, Integer> mapLocateFirstStringWords = new HashMap<>();
        HashMap<String, Integer> mapLocateSecondStringWords = new HashMap<>();

        int position = 0;
        for (String s : wordsFirstDocument) {
            if (setWordsSecondDocument.contains(s)) {
                if (mapFrequenciesFirstWords.containsKey(s)) {
                    int key = mapFrequenciesFirstWords.get(s);
                    key++;
                    mapFrequenciesFirstWords.put(s, key);
                } else {
                    mapFrequenciesFirstWords.put(s, 1);
                    mapLocateFirstStringWords.put(s, position);
                    position++;
                }
            }
        }

        if (position == 0) {
            System.out.println("не найдено общих слов");
            return;
        }

        position = 0;

        for (String s : wordsSecondDocument) {
            if (setWordsFirstDocument.contains(s)) {
                if (mapFrequenciesSecondWords.containsKey(s)) {
                    int key = mapFrequenciesSecondWords.get(s);
                    key++;
                    mapFrequenciesSecondWords.put(s, key);
                } else {
                    mapFrequenciesSecondWords.put(s, 1);
                    mapLocateSecondStringWords.put(s, position);
                    position++;
                }
            }
        }


        int size = position;
        float[] vectorA = new float[size];
        float[] vectorB = new float[size];
        setWordsSecondDocument.retainAll(setWordsFirstDocument);

        int countWordsFirstString = wordsFirstDocument.length;
        int countWordsSecondString = wordsSecondDocument.length;

        for (String interWord : setWordsSecondDocument
        ) {
            int indexA = mapLocateFirstStringWords.get(interWord);
            int indexB = mapLocateSecondStringWords.get(interWord);
            vectorA[indexA] = (float) mapFrequenciesFirstWords.get(interWord) / (float) countWordsFirstString;
            vectorB[indexB] = (float) mapFrequenciesSecondWords.get(interWord) / (float) countWordsSecondString;
        }

        float result = scalarMultiple(vectorA, vectorB);
        System.out.println(result);
    }

    public static float scalarMultiple(float[] A, float[] B) {
        float sumA = 0;
        float sumB = 0;
        float multipleSum = 0;

        for (int i = 0; i < A.length; i++) {
            sumA += (float) Math.pow(A[i], 2);
            sumB += (float) Math.pow(B[i], 2);
            multipleSum += A[i] * B[i];
        }

        float denominator = (float) Math.sqrt(sumA * sumB);
        return multipleSum / denominator;

    }
}