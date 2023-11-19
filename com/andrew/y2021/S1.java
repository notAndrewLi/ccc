package ccc.com.andrew.y2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S1 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2021").resolve("senior").resolve("s1");

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
        int shapes = Integer.valueOf(tc.In[0][0]);
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        double totalArea = 0;
        int[][] heights = new int[shapes][2];
        for (int heightIdx = 0; heightIdx < shapes; heightIdx++) {
            heights[heightIdx][0] = Integer.valueOf(tc.In[1][heightIdx]);
            heights[heightIdx][1] = Integer.valueOf(tc.In[1][heightIdx + 1]);
        }

        for (int shapeCalc = 0; shapeCalc < shapes; shapeCalc++) {
            int width = Integer.valueOf(tc.In[2][shapeCalc]);
            totalArea += (heights[shapeCalc][0] + heights[shapeCalc][1]) * width / 2.0;
        }
        return new ArrayList<String>(Arrays.asList(String.format("%.1f", totalArea)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S1());
    }
}
