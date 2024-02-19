import java.io.*;
import java.util.*;

public class S1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLines = scanner.nextInt();
        scanner.nextLine();
        int countT = 0;
        int countS = 0;
        for(int i = 0; i < numLines; i++){
            String line = scanner.nextLine();
            for(int stringIdx = 0; stringIdx < line.length(); stringIdx++){
                char c = Character.toLowerCase(line.charAt(stringIdx));
                if(c == 't') countT++;
                else if(c == 's') countS++;
            }
        }
        if(countS >= countT){
            System.out.println("French");
        } else{
            System.out.println("English");
        }
        scanner.close();
    }
}
