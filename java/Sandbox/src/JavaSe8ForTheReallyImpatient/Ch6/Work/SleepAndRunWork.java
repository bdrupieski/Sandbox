package JavaSe8ForTheReallyImpatient.Ch6.Work;

public class SleepAndRunWork extends SleepWork {

    private Runnable runnable;

    public SleepAndRunWork(int workNumber, int workLengthMilliseconds, Runnable runnable) {
        super(workNumber, workLengthMilliseconds);
        this.runnable = runnable;
    }

    @Override
    public void work() throws InterruptedException {
        super.work();
        runnable.run();
    }
}
