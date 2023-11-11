package ccc.com.andrew.y2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S1 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2022").resolve("senior").resolve("s1");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "s1.*.in"));
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
        int num = Integer.valueOf(tc.In[0][0]);
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        int answer = 0;
        for (int i = 0; i <= num / 4; i++) {
            if ((num - 4 * i) % 5 == 0) {
                answer++;
            }
        }
        return new ArrayList<String>(Arrays.asList(Integer.toString(answer)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S1());
    }
}
