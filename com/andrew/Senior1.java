package ccc.com.andrew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Senior1 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("senior").resolve("s1j4");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
            (path, basicFileAttributes) -> path.toFile().getName().matches(".*.in"));
        try {
            ArrayList<TestCase> testCases = new ArrayList<TestCase>();
            stream.forEach((path) -> testCases.add(new TestCase(path, 2)));
            return testCases;
        } finally {
            stream.close();
        }
    }

    @Override
    public String run(String[][] in) {
        return "9";
    }
    
}
