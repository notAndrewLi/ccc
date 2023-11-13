package ccc.com.andrew.y2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ccc.com.andrew.Problem;
import ccc.com.andrew.TestCase;

public class S2J4 implements Problem {
    private static Path BasePath = TestCase.basePath.resolve("2022").resolve("senior").resolve("s2j4");

    @Override
    public ArrayList<TestCase> getTestCases() throws IOException {
        Stream<Path> stream = Files.find(BasePath, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> path.toFile().getName().matches(
                        "s2.*.in"));
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
        int errors = 0;
        System.out.println("Working on Test Case " + tc.InFile.getFileName().toString());
        Map<String, List<String>> sgConstraints = new HashMap<String, List<String>>();
        int sgcCount = Integer.valueOf(tc.In[0][0]);
        for (int i = 0; i < sgcCount; i++) {
            String key = tc.In[i + 1][0];
            String value = tc.In[i + 1][1];
            if (!sgConstraints.containsKey(key)) {
                sgConstraints.put(key, new ArrayList<>());
            }
            sgConstraints.get(key).add(value);
        }
        Map<String, List<String>> dgConstraints = new HashMap<String, List<String>>();
        int dgcCount = Integer.valueOf(tc.In[sgcCount + 1][0]);
        for (int i = sgcCount; i < sgcCount + dgcCount; i++) {
            String key = tc.In[i + 2][0];
            String value = tc.In[i + 2][1];
            if (!dgConstraints.containsKey(key)) {
                dgConstraints.put(key, new ArrayList<>());
            }
            dgConstraints.get(key).add(value);
        }
        int groups = Integer.valueOf(tc.In[sgcCount + dgcCount + 2][0]);
        for (int i = sgcCount + dgcCount + 2; i < sgcCount + dgcCount + 2 + groups; i++) {
            for (int groupIndex = 0; groupIndex < 3; groupIndex++) {
                String key = tc.In[i + 1][groupIndex];
                List<String> sg = sgConstraints.get(key);
                List<String> dg = dgConstraints.get(key);
                if (sg != null) {
                    for (int j = 0; j < sg.size(); j++) {
                        if (!Arrays.asList(tc.In[i + 1]).contains(sg.get(j))) {
                            errors++;
                        }
                    }
                }
                if (dg != null) {
                    for (int j = 0; j < dg.size(); j++) {
                        if (Arrays.asList(tc.In[i + 1]).contains(dg.get(j))) {
                            errors++;
                        }
                    }
                }
            }
        }
        return new ArrayList<String>(Arrays.asList(Integer.toString(errors)));
    }

    public static void main(String[] args) throws IOException {
        Problem.runAndCheck(new S2J4());
    }
}
