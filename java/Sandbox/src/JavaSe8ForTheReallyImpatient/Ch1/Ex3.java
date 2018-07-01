package JavaSe8ForTheReallyImpatient.Ch1;

import java.io.File;
import java.util.Arrays;

/*
 * Using the list(FilenameFilter) method of the java.io.File class, write a method
 * that returns all files in a given directory with a given extension. Use a lambda
 * expression, not a FilenameFilter. Which variables from the enclosing scope does
 * it capture?
*/
public class Ex3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(allFilesInDirectoryWithExtension(new File("C:/anaconda3"), ".exe")));
    }

    public static String[] allFilesInDirectoryWithExtension(File dir, String extension) {
        return dir.list((file, name) -> name.endsWith(extension));
    }
}
