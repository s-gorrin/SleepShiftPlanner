import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to write a schedule to a file.
 */
public class ToFile {

  /**
   * Write a string to a file.
   *
   * @param string  the string to write to the file
   */
  public static void toFile(String filename, String string) {
    File file = new File(filename);

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(string);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
