package ccc.com.andrew;

public class Checker {

    public static void main(String[] args)
    {
        TestCase tc = new TestCase("s1j4", "s1.1-01", 2);
        s1j4 problem1 = new s1j4();
        String result = problem1.run(tc.In);
        if (result.equals(tc.Out))
        {
            System.out.println("Yeah! Result " + result + " is correct!");
        }
        else
        {
            System.out.println("BOOM! Result " + result + " is wrong!!!");
        }
    }
};
