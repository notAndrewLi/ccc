package ccc.com.andrew;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class TestCase {
  public static Path basePath = Paths.get(System.getProperty("user.home") + "/Downloads/");
  public int NumColumns;
  public String[][] In;
  public ArrayList<String> Out = new ArrayList<>();
  public Path InFile;

  // numLines <= 0 meaning it's dynamic and the first line has the value
  public TestCase(Path inFile, int numLines) {
    try {
      InFile = inFile;
      Path outFile = inFile.resolveSibling(inFile.getFileName().toString().replaceAll(".in", ".out"));
      List<String> fileStream = Files.readAllLines(inFile);
      if (numLines > 0 && numLines != fileStream.size()) {
        throw new RuntimeException("Inconsistent number of lines.");
      }
      numLines = fileStream.size();
      In = new String[numLines][];
      for (int lineId = 0; lineId < numLines; lineId++) {
        String line = fileStream.get(lineId);
        String[] splitArray = line.split(" ");
        In[lineId] = splitArray;
      }

      File outObj = outFile.toFile();
      Scanner myReader = new Scanner(outObj);
      while (myReader.hasNextLine()) {
        Out.add(myReader.nextLine().trim());
      }
      // System.out.println(Out);
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("File not found.");
      e.printStackTrace();
    }
  }
}
