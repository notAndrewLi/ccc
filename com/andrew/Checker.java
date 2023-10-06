package ccc.com.andrew;

import java.io.IOException;
import java.util.ArrayList;

public class Checker {

    public static void main(String[] args) throws IOException
    {
        Senior1 problem1 = new Senior1();
        ArrayList<TestCase> testCases = problem1.getTestCases();
        TestCase tc = testCases.get(0);
        String result = problem1.run(tc.In);
        if (result.equals(tc.Out))
        {
            System.out.println("Yeah! Result " + result + " is correct!");
        }
        else
        {
            System.out.println("BOOM! Result " + result + " is wrong!!! Expect result is " + tc.Out);
        }
    }
};
