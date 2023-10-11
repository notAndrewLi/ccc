package ccc.com.andrew;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner; // Import the Scanner class to read text files

public class TestCase {
  public static Path basePath = Paths.get(System.getProperty("user.home") + "/Downloads/all_data");
  public int NumColumns;
  public String[][] In;
  public String Out;
  public Path InFile;

  // numLines <= 0 meaning it's dynamic and the first line has the value
  public TestCase(Path inFile, int numLines) {
    try {
      InFile = inFile;
      Path outFile = inFile.resolveSibling(inFile.getFileName().toString().replaceAll(".in", ".out"));
      File inObj = inFile.toFile();
      Scanner myReader = new Scanner(inObj);
      int lineId = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        // System.out.println(data);
        String[] splitArray = data.split(" ");
        if (lineId == 0) {
          if (numLines <= 0) {
            numLines = Integer.parseInt(splitArray[0]) + 1;
          }
          In = new String[numLines][];
        }
        // System.out.println(Arrays.toString(splitArray));
        In[lineId] = splitArray;
        lineId++;
      }
      myReader.close();
      File outObj = outFile.toFile();
      myReader = new Scanner(outObj);
      Out = myReader.nextLine();
      // System.out.println(Out);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
