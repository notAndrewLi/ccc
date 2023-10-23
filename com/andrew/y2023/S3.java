package ccc.com.andrew.y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S3 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2023").resolve("senior").resolve("s3");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "s3.1.*.in"));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path, 2)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    @Override
    public ArrayList<String> run(TestCase tc) {
        String[] firstRow = tc.In[0];
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        int N = Integer.parseInt(firstRow[0]);
        int M = Integer.parseInt(firstRow[1]);
        int R = Integer.parseInt(firstRow[2]);
        int C = Integer.parseInt(firstRow[3]);
        // System.out.println(N + " " + M + " " + R + " " + C);
        char[][] pattern = new char[N][M];
        for (int i = 0; i < M; i++) {
            pattern[0][i] = 'a';
        }
        for (int i = 1; i < N; i++) {
            pattern[i][0] = 'a';
            for (int c = 1; c < M; c++) {
                pattern[i][c] = 'b';
            }
        }
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < pattern.length; i++) {
            answer.add(new String(pattern[i]));
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S3());
    }
}
