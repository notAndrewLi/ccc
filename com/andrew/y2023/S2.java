package ccc.com.andrew.y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S2 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("senior").resolve("s2");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "s2.*.in"));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path, 2)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    public static int processCrop(int[] crop) {
        int asymVal = 0;
        for (int idxCalcs = 0; idxCalcs < crop.length / 2; idxCalcs++) {
            int diff = Math.abs(crop[idxCalcs] - crop[crop.length - 1 - idxCalcs]);
            asymVal += diff;
        }
        return asymVal;
    }

    @Override
    public String run(TestCase tc) {
        String[] firstRow = tc.In[1];
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        // System.out.println("First row : " + Arrays.toString(firstRow));
        // System.out.println("Second row : " + Arrays.toString(secondRow));
        String answer = "0";
        int[] numbers = new int[firstRow.length];
        for (int i = 0; i < firstRow.length; i++) {
            numbers[i] = Integer.parseInt(firstRow[i]);
        }
        for (int cropSize = 2; cropSize <= 3 && cropSize <= firstRow.length; cropSize++) {
            int minAsymVal = Integer.MAX_VALUE;
            for (int idxCrops = 0; idxCrops < firstRow.length + 1 - cropSize; idxCrops++) {
                int asymVal = Math.abs(numbers[idxCrops] - numbers[idxCrops + cropSize - 1]);
                if (asymVal < minAsymVal) {
                    minAsymVal = asymVal;
                }
            }
            answer += " " + Integer.toString(minAsymVal);
        }
        for (int cropSize = 4; cropSize <= firstRow.length; cropSize++) {
            final int windowSize = cropSize;
            OptionalInt minAsymVal = IntStream.range(0, firstRow.length + 1 - cropSize)
                    .parallel()
                    .mapToObj(i -> Arrays.copyOfRange(numbers, i, i + windowSize))
                    .mapToInt(crop -> processCrop(crop))
                    .min();

            answer += " " + Integer.toString(minAsymVal.getAsInt());
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Problem problem = new S2();
        ArrayList<TestCase> testCases = problem.getTestCases();
        testCases.forEach((tc) -> {
            Instant start = Instant.now();
            String result = problem.run(tc);
            Instant end = Instant.now();

            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result
                        + " is correct! Execution duration: " + Duration.between(start, end));
            } else {
                System.out.println(tc.InFile.getFileName().toString() + ": BOOM! Result " + result
                        + " is wrong!!! Expect result is " + tc.Out);
            }
        });
    }
}
