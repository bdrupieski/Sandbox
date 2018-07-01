package JavaSe8ForTheReallyImpatient.Ch6;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import JavaSe8ForTheReallyImpatient.Ch6.Work.SleepWork;
import com.google.common.base.Stopwatch;

public class BasicExecutorService {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Stopwatch sw = Stopwatch.createStarted();

        executorService.submit(new SleepWork(1, 3000));
        executorService.submit(new SleepWork(2, 2000));
        executorService.submit(new SleepWork(3, 1000));
        executorService.submit(new SleepWork(4, 1000));

        executorService.shutdown();
        if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("forcing shutdown");
            executorService.shutdownNow();
        }

        sw.stop();
        System.out.println(MessageFormat.format("{0} ms", sw.elapsed(TimeUnit.MILLISECONDS)));
    }
}

