package predavanje14;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class PWTest {
  
  public static void main(String[] args) throws Exception {
    FileOutputStream fis = new FileOutputStream("viri/test.txt");
    OutputStreamWriter osw = new OutputStreamWriter(fis, "cp1250");
    PrintWriter pw = new PrintWriter(osw);
    
    Scanner sc = new Scanner(new File("viri/a.txt"), "utf8");
  }

}
