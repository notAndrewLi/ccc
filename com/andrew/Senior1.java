package ccc.com.andrew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Senior1 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("senior").resolve("s1j4");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                    "s1.[1|2]-.*.in"
                    //".*.1-01.in"
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
        //System.out.println("First row        : " + Arrays.toString(firstRow));
        //System.out.println("Second row       : " + Arrays.toString(secondRow));
        int answer = 0;

        int numOnes = 0;
        for (int i = 0; i < firstRow.length; i++) {
            if (firstRow[i].equals("1")) {//if we find a black triangle we will add one to the streak of ones
                numOnes++;
            }
            
            if (firstRow[i].equals("0") || i == firstRow.length - 1) {//if we find a white triangle or we reach the end of the row, we calculate the streak of ones.
                if (numOnes == 0) {//if there is no streak, nothing happens
                } else if (numOnes == 1) {//if there is a 1 streak, add 3 to the amount of tape
                    answer += 3;
                } else if (numOnes == 2) {//if there is a 2 streak, add 4
                    answer += 4;
                } else {//for everything else, use this formula to calculate the amount needed
                    answer += numOnes + 2;
                }
                numOnes = 0;//reset streak
            }
        }
        for (int i = 0; i < secondRow.length; i++) {
            if (secondRow[i].equals("1")) {
                answer += 3;
            }
        }
        return Integer.toString(answer);
    }

    public static void main(String[] args) throws IOException {
        Senior1 problem1 = new Senior1();
        ArrayList<TestCase> testCases = problem1.getTestCases();
        testCases.forEach((tc) -> {
            String result = problem1.run(tc);
            if (result.equals(tc.Out)) {
                System.out.println(tc.InFile.getFileName().toString() + ": Yeah! Result " + result + " is correct!");
            } else {
                System.out.println(tc.InFile.getFileName().toString() + ": BOOM! Result " + result + " is wrong!!! Expect result is " + tc.Out);
            }
        });
    }
}
