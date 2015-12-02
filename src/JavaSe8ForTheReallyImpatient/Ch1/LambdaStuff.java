package JavaSe8ForTheReallyImpatient.Ch1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaStuff {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(String.format("running %d", i));
            }
        };

        Thread t = new Thread(runnable);
        t.start();

        runnable.run();
        t.join();


        EventHandler<ActionEvent> listener = event -> System.out.println("Thanks for clicking!");
        listener.handle(new ActionEvent());

        Consumer consumer = System.out::println;
        consumer.accept("whoa");
        consumer.accept(1);
        consumer.accept(consumer);

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("whoa2");

        Comparator<String> cmp = String::compareTo;
        System.out.println(cmp.compare("hello", "world"));

        SomeClass a = new SomeClass();

        Function<String, String> instanceMethod = a::instanceMethod;
        System.out.println(instanceMethod.apply("hi"));

        BiFunction<SomeClass, String, String> instanceMethod1 = SomeClass::instanceMethod;
        System.out.println(instanceMethod1.apply(a, "hi"));

        Function<String, String> staticMethod = SomeClass::staticMethod;
        System.out.println(staticMethod.apply("hi"));

        Arrays.asList(1, 2, 3, 4).stream().map(AnotherClass::new).forEach(System.out::println);
        List<AnotherClass> l = Arrays.asList(1, 2, 3, 4).stream().map(AnotherClass::new).collect(Collectors.toList());
        System.out.println(l);

        repeatMessage("hi", 5);
    }

    public static void repeatMessage(final String text, final int count) {
        Runnable r = () -> {
            for (int i = 0; i < count; i++) {
                System.out.println(text);
                Thread.yield();
            }
        };
        new Thread(r).start();
    }
}

class SomeClass {
    public String instanceMethod(String s) {
        return s + ".";
    }

    public static String staticMethod(String s) {
        return s + ".";
    }
}

class AnotherClass {
    public int a;
    public AnotherClass(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "AnotherClass{" +
                "a=" + a +
                '}';
    }
}