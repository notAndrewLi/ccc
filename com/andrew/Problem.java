package ccc.com.andrew;

import java.io.IOException;
import java.util.ArrayList;

public interface Problem {
    public String run(TestCase tc);

    public ArrayList<TestCase> getTestCases() throws IOException;
}
