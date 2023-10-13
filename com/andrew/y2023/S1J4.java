package ccc.com.andrew.y2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S1J4 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("senior").resolve("s1j4");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "s1.[1|2|3|4]-.*.in"
                // ".*.1-01.in"
                ));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path, 3)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    @Override
    public String run(TestCase tc) {
        String[] firstRow = tc.In[1];
        String[] secondRow = tc.In[2];
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        // System.out.println("First row : " + Arrays.toString(firstRow));
        // System.out.println("Second row : " + Arrays.toString(secondRow));
        int answer = 0;

        int numOnes = 0;
        for (int i = 0; i < firstRow.length; i++) {
            if (firstRow[i].equals("1")) {// if we find a black triangle we will add one to the streak of ones
                numOnes++;
            }

            if (firstRow[i].equals("0") || i == firstRow.length - 1) {// if we find a white triangle or we reach the end
                                                                      // of the row, we calculate the streak of ones.
                if (numOnes == 0) {// if there is no streak, nothing happens
                } else {// use this formula to calculate the amount needed
                    answer += numOnes + 2;
                }
                numOnes = 0;// reset streak
            }
        }

        for (int i = 0; i < secondRow.length; i++) { // calculating the second rows answer
            if (secondRow[i].equals("1")) {
                if (i % 2 == 0 && firstRow[i].equals("1"))// check for if the triangle is even and if there is a black
                                                          // triangle above it.
                {
                    answer -= 2; // we subtract the two lines that will cancel each other out
                }
                numOnes++;
            }
            if (secondRow[i].equals("0") || i == secondRow.length - 1) {// if we find a white triangle or we reach the
                                                                        // end of the row, we calculate the streak of
                                                                        // ones.
                if (numOnes == 0) {// if there is no streak, nothing happens
                } else {// use this formula to calculate the amount needed
                    answer += numOnes + 2;
                }
                numOnes = 0;// reset streak
            }
        }
        return Integer.toString(answer);
    }

    public static void main(String[] args) throws IOException {
        S1J4 problem1 = new S1J4();
        ArrayList<TestCase> testCases = problem1.getTestCases();
        testCases.forEach((tc) -> {
            String result = problem1.run(tc);
            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result + " is correct!");
            } else {
                System.out.println(tc.InFile.getFileName().toString() + ": BOOM! Result " + result
                        + " is wrong!!! Expect result is " + tc.Out);
            }
        });
    }
}
