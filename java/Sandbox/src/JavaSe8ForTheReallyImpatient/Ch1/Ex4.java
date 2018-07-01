package JavaSe8ForTheReallyImpatient.Ch1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of File objects, sort it so that the directories come before the
 * files, and within each group, elements are sorted by path name. Use a lambda
 * expression, not a Comparator.
 */
public class Ex4 {
    public static void main(String[] args) {
        {
            File[] files = new File("C:/anaconda3").listFiles();
            System.out.println(Arrays.toString(files));
            sortByTypeAndNameComparator(files);
            System.out.println(Arrays.toString(files));
        }

        {
            File[] files = new File("C:/anaconda3").listFiles();
            System.out.println(Arrays.toString(files));
            sortByTypeAndNameLambda(files);
            System.out.println(Arrays.toString(files));
        }
    }

    public static void sortByTypeAndNameLambda(File[] files) {
        Arrays.sort(files, (f1, f2) -> {
            int typeComparison = Boolean.compare(f1.isFile(), f2.isFile());
            if (typeComparison == 0) {
                return f1.getName().compareTo(f2.getName());
            } else {
                return typeComparison;
            }
        });
    }

    public static void sortByTypeAndNameComparator(File[] files) {
        Comparator<File> cmp = Comparator.comparing(File::isFile).thenComparing(File::getName);
        Arrays.sort(files, cmp::compare);
    }
}
