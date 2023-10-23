package ccc.com.andrew.y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class J3 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2023").resolve("junior").resolve("j3");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "j3.*.in"));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path, -1)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    @Override
    public ArrayList<String> run(TestCase tc) {
        String[] firstRow = tc.In[0];
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        // System.out.println("First row : " + Arrays.toString(firstRow));
        // System.out.println("Second row : " + Arrays.toString(secondRow));
        int[] availCount = new int[5];
        int N = Integer.parseInt(firstRow[0]);
        for (int person = 1; person <= N; person++) {
            for (int day = 0; day < availCount.length; day++) {
                if (tc.In[person][0].charAt(day) == 'Y') {
                    availCount[day]++;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        ArrayList<String> maxIdx = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            if (availCount[i] > max) {
                max = availCount[i];
                maxIdx.clear();
                maxIdx.add(Integer.toString(i + 1));
            } else if (availCount[i] == max) {
                maxIdx.add(Integer.toString(i + 1));
            }
        }

        return new ArrayList<String>(Arrays.asList(String.join(",", maxIdx)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new J3());
    }
}
