package ccc.com.andrew.y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S2 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2023").resolve("senior").resolve("s2");

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

    @Override
    public String run(TestCase tc) {
        String[] firstRow = tc.In[1];
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        // System.out.println("First row : " + Arrays.toString(firstRow));
        // System.out.println("Second row : " + Arrays.toString(secondRow));
        String answer = "0";
        int minAsymVal = Integer.MAX_VALUE;
        final int N = firstRow.length;
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(firstRow[i]);
        }
        int[][] asymVals = new int[N + 1][N];
        /*
         * to find the asym value of the crop sizes between a left and right point
         * we iterate through the crops so that, for each crop [l, r],
         * we use the already calculated asym value for the crop [l + 1, r - 1] in the
         * previous step
         * then we can update the value for the current crop without redoing all the
         * calculations
         */

        for (int idxCrop = 0; idxCrop < N - 1; idxCrop++) {
            int asymVal = Math.abs(numbers[idxCrop + 1] - numbers[idxCrop]);
            asymVals[2][idxCrop] = asymVal;
            if (asymVal < minAsymVal) {
                minAsymVal = asymVal;
            }
        }
        answer += " " + minAsymVal;
        for (int cropSize = 3; cropSize <= N; cropSize++) {
            minAsymVal = Integer.MAX_VALUE;
            for (int idxCrop = 0; idxCrop < N + 1 - cropSize; idxCrop++) {
                int asymVal = asymVals[cropSize - 2][idxCrop + 1] // if we start at cropsize 3, the asym value of
                        // cropsize 1 is always 0
                        + Math.abs(numbers[cropSize + idxCrop - 1] - numbers[idxCrop]);
                asymVals[cropSize][idxCrop] = asymVal;
                if (asymVal < minAsymVal) {
                    minAsymVal = asymVal;
                }
            }
            answer += " " + minAsymVal;
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
                return;
            }
        });
    }
}
