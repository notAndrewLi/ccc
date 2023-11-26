package ccc.com.andrew.y2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S2J5 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2021").resolve("senior").resolve("s2j5");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "j5.0[9].in"));
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
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        int M = Integer.valueOf(tc.In[0][0]);
        int N = Integer.valueOf(tc.In[1][0]);
        int[][] canvas = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                canvas[i][j] = -1;
            }
        }
        int golds = 0;
        int strokes = Integer.valueOf(tc.In[2][0]);
        for (int i = 0; i < strokes; i++) {
            int strokeLocation = Integer.valueOf(tc.In[i + 3][1]) - 1;
            if (tc.In[i + 3][0].equals("R")) {
                for (int rowIdx = 0; rowIdx < N; rowIdx++) {
                    canvas[strokeLocation][rowIdx] = -canvas[strokeLocation][rowIdx];
                    // golds += canvas[strokeLocation][rowIdx];
                }
            } else {
                for (int colIdx = 0; colIdx < M; colIdx++) {
                    canvas[colIdx][strokeLocation] = -canvas[colIdx][strokeLocation];
                    // golds += canvas[colIdx][strokeLocation];
                }
            }
        }
        return new ArrayList<String>(Arrays.asList(Integer.toString(golds)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S2J5());
    }
}
