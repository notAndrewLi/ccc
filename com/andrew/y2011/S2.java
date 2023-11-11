package ccc.com.andrew.y2011;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S2 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2011").resolve("senior").resolve("s2");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "s2.*.in"));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path, 0)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    @Override
    public ArrayList<String> run(TestCase tc) {
        int score = 0;
        int questions = Integer.valueOf(tc.In[0][0]);
        for (int i = 1; i <= questions; i++) {
            String answer = tc.In[questions + i][0];
            if (tc.In[i][0].equals(answer)) {
                score++;
            }
        }
        return new ArrayList<String>(Arrays.asList(Integer.toString(score)));

    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S2());
    }
}
