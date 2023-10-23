package ccc.com.andrew;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public interface Problem {
    public ArrayList<String> run(TestCase tc);

    public ArrayList<TestCase> getTestCases() throws IOException;

    public static void runAndCheck(Problem problem) throws IOException {
        ArrayList<TestCase> testCases = problem.getTestCases();
        testCases.forEach((tc) -> {
            Instant start = Instant.now();
            ArrayList<String> result = problem.run(tc);
            Instant end = Instant.now();

            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result.get(0)
                        + " is correct! Execution duration: "
                        + Duration.between(start, end));
            } else {
                String error = tc.InFile.getFileName().toString();
                if (result.size() != tc.Out.size()) {
                    error += ": BOOM! Incorrect "
                            + result.size()
                            + " lines!!! Expect " + tc.Out.size() + " lines";
                } else {
                    error += ": Explosion!!!!! BOOM! Result "
                            + result.get(0)
                            + " is wrong!!! Expect result is " + tc.Out.get(0);
                }
                throw new RuntimeException(error);
            }
        });
    }
}
