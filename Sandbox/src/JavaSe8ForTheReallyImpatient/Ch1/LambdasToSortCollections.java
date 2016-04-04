package JavaSe8ForTheReallyImpatient.Ch1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdasToSortCollections {

    public static void main(String[] args) {

        {
            String[] p = getSomeStrings();

            Arrays.sort(p, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.compare(o1.length(), o2.length());
                }
            });
            System.out.println(Arrays.toString(p));
        }

        {
            String[] p = getSomeStrings();
            Arrays.sort(p, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
            System.out.println(Arrays.toString(p));
        }

        {
            List<String> p = Arrays.asList(getSomeStrings());
            p.sort((x, y) -> Integer.compare(x.length(), y.length()));
            System.out.println(p);

        }

        {
            List<String> p = Arrays.asList(getSomeStrings());
            p.sort(new LengthComparator());
            System.out.println(p);
        }

        {
            List<String> p = Arrays.asList(getSomeStrings());
            p.sort(String::compareTo);
            System.out.println(p);
        }

    }

    private static String[] getSomeStrings() {
        String s = "two is a number and now here's a reallylongstring";
        return s.split(" ");
    }
}

class LengthComparator implements Comparator<String> {
    public int compare(String first, String second) {
        return Integer.compare(first.length(), second.length());
    }
}