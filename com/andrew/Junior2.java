package ccc.com.andrew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Junior2 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("junior").resolve("j2");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "j2.*.in"));
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
        int answer = 0;
        int N = Integer.parseInt(firstRow[0]);
        for (int i = 1; i <= N; i++) {
            switch (tc.In[i][0]) {
                case "Poblano":
                    answer += 1500;
                    break;
                case "Mirasol":
                    answer += 6000;
                    break;
                case "Serrano":
                    answer += 15500;
                    break;
                case "Cayenne":
                    answer += 40000;
                    break;
                case "Thai":
                    answer += 75000;
                    break;
                case "Habanero":
                    answer += 125000;
                    break;
                default:
                    System.out.println("What are you talking about? Ppepper not found, eeerorr code 707");
                    break;
            }
        }

        return Integer.toString(answer);
    }

    public static void main(String[] args) throws IOException {
        Problem problem = new Junior2();
        ArrayList<TestCase> testCases = problem.getTestCases();
        testCases.forEach((tc) -> {
            String result = problem.run(tc);
            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result
                        + " is correct! You are goood :::192738210283701280");
            } else {
                System.out.println(
                        tc.InFile.getFileName().toString() + ": Explosion!!!!! :sadface :sadface BOOM! Result " + result
                                + " is wrong!!! Expect result is " + tc.Out);
            }
        });
    }
}
