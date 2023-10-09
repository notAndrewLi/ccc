package ccc.com.andrew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Senior2 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("senior").resolve("s2");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                    "s2.[1]-.*.in"
                    ));
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
        //System.out.println("First row        : " + Arrays.toString(firstRow));
        //System.out.println("Second row       : " + Arrays.toString(secondRow));
        int answer = 0;

        return Integer.toString(answer);
    }

    public static void main(String[] args) throws IOException {
        Problem problem = new Senior2();
        ArrayList<TestCase> testCases = problem.getTestCases();
        testCases.forEach((tc) -> {
            String result = problem.run(tc);
            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result + " is correct!");
            } else {
                System.out.println(tc.InFile.getFileName().toString() + ": BOOM! Result " + result + " is wrong!!! Expect result is " + tc.Out);
            }
        });
    }
}
