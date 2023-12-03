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
                        "j5.*.in"));
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
        int golds = 0;
        int K = Integer.valueOf(tc.In[2][0]);
        int[] rowOperations = new int[M];
        int[] colOperations = new int[N];
        for (int k = 0; k < K; k++) {
            int strokeLocation = Integer.valueOf(tc.In[k + 3][1]) - 1;
            if (tc.In[k + 3][0].equals("R")) {
                rowOperations[strokeLocation]++;
            } else {
                colOperations[strokeLocation]++;
            }
        }
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                golds += (rowOperations[m] + colOperations[n]) % 2;
            }
        }
        return new ArrayList<String>(Arrays.asList(Integer.toString(golds)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S2J5());
    }
}
