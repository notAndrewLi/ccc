package ccc.com.andrew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.Stream;

public class J3_2023 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("junior").resolve("j3");

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
    public String run(TestCase tc) {
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

        return String.join(",", maxIdx);
    }

    public static void main(String[] args) throws IOException {
        Problem problem = new J3_2023();
        ArrayList<TestCase> testCases = problem.getTestCases();
        testCases.forEach((tc) -> {
            Instant start = Instant.now();
            String result = problem.run(tc);
            Instant end = Instant.now();

            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result
                        + " is correct! You are goood :::192738210283701280 Execution duration: "
                        + Duration.between(start, end));
            } else {
                System.out.println(
                        tc.InFile.getFileName().toString() + ": Explosion!!!!! :sadface :sadface BOOM! Result " + result
                                + " is wrong!!! Expect result is " + tc.Out);
            }
        });
    }
}
