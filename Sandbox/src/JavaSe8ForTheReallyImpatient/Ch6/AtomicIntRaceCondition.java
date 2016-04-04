package JavaSe8ForTheReallyImpatient.Ch6;

import JavaSe8ForTheReallyImpatient.Ch6.Work.SleepAndRunWork;
import com.google.common.base.Stopwatch;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntRaceCondition {

    public static void main(String[] args) throws InterruptedException {
        //raceCondition();
        noRaceCondition();
    }

    private static void raceCondition() {
        final int[] count = {0};
        int expectedCount = 5000;

        runWork(expectedCount, () -> count[0]++);

        System.out.println(MessageFormat.format("ExpectedCount: {0} ActualCount: {1}", expectedCount, count[0]));
    }

    private static void noRaceCondition() {
        AtomicInteger count = new AtomicInteger();
        int expectedCount = 5000;

        runWork(expectedCount, count::getAndIncrement);

        System.out.println(MessageFormat.format("ExpectedCount: {0} ActualCount: {1}", expectedCount, count.get()));
    }

    private static void runWork(int expectedCount, Runnable work) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Stopwatch sw = Stopwatch.createStarted();

        for (int i = 1; i <= expectedCount; i++) {
            executorService.submit(new SleepAndRunWork(i, 2, work));
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("forcing shutdown");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("awaiting shutdown interrupted");
        }

        sw.stop();
        System.out.println(MessageFormat.format("Elapsed: {0} ms", sw.elapsed(TimeUnit.MILLISECONDS)));
    }
}
