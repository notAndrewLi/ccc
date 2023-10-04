package ccc.com.andrew;

// import java.util.Arrays;

// class Test
// {
//     public static void main(String []args)
//     {
//         System.out.println("My First Java Program with these arguments: " + Arrays.toString(args));
//     }
// };

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Test {
  public static void main(String[] args) {
    try {
      File myObj = new File("/Users/andrew/Downloads/all_data/senior/s1j4/s1.1-01.in");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
