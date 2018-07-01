package JavaSe8ForTheReallyImpatient.Ch1;

import java.io.File;
import java.util.Arrays;

/*
* Using the listFiles(FileFilter) and isDirectory methods of the java.io.File class,
* write a method that returns all subdirectories of a given directory. Use a
* lambda expression instead of a FileFilter object. Repeat with a method
* expression.
* */
public class Ex2 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(allSubDirectoriesInDirectoryLambda(new File("C:/anaconda3"))));
        System.out.println(Arrays.toString(allSubDirectoriesInDirectoryMethodReference(new File("C:/anaconda3"))));
    }

    public static File[] allSubDirectoriesInDirectoryLambda(File f) {
        return f.listFiles(x -> x.isDirectory());
    }

    public static File[] allSubDirectoriesInDirectoryMethodReference(File f) {
        return f.listFiles(File::isDirectory);
    }
}
