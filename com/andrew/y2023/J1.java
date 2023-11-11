package ccc.com.andrew.y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class J1 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2023").resolve("junior").resolve("j1");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "j1.*.in"));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    @Override
    public ArrayList<String> run(TestCase tc) {
        String[] firstRow = tc.In[0];
        String[] secondRow = tc.In[1];
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        // System.out.println("First row : " + Arrays.toString(firstRow));
        // System.out.println("Second row : " + Arrays.toString(secondRow));
        int answer = 0;
        int P = Integer.parseInt(firstRow[0]);
        int C = Integer.parseInt(secondRow[0]);
        answer = P * 50 - C * 10;
        if (P > C) {
            // Give it a bonus
            answer += 500;
        }

        return new ArrayList<String>(Arrays.asList(Integer.toString(answer)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new J1());
    }
}
