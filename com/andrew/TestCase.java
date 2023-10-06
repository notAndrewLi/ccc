package ccc.com.andrew;

import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner; // Import the Scanner class to read text files

public class TestCase {
  public static Path basePath = Paths.get("/Users/andrew/Downloads/all_data/senior");
  public int NumColumns;
  public String[][] In;
  public String Out;

  public TestCase(String problem, String testCase, int numRows) {
    try {
      Path inFile = basePath.resolve(problem).resolve(testCase + ".in");
      Path outFile = basePath.resolve(problem).resolve(testCase + ".out");
      In = new String[numRows][];
      File inObj = inFile.toFile();
      Scanner myReader = new Scanner(inObj);
      NumColumns = Integer.parseInt(myReader.nextLine());
      System.out.println(NumColumns);
      int lineId = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        // System.out.println(data);
        String[] splitArray = data.split(" ");
        System.out.println(Arrays.toString(splitArray));
        In[lineId] = splitArray;
        lineId++;
      }
      myReader.close();
      File outObj = outFile.toFile();
      myReader = new Scanner(outObj);
      Out = myReader.nextLine();
      System.out.println(Out);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    TestCase tc = new TestCase("s1j4", "s1.1-01", 2);
    System.out.println(tc);
  }
}
