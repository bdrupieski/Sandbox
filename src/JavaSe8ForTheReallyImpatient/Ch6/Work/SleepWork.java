package JavaSe8ForTheReallyImpatient.Ch6.Work;

public class SleepWork extends BaseWork {

    private int workLengthMilliseconds;

    public SleepWork(int workNumber, int workLengthMilliseconds) {
        super(workNumber);
        this.workLengthMilliseconds = workLengthMilliseconds;
    }

    @Override
    public void work() throws InterruptedException {
        Thread.sleep(this.workLengthMilliseconds);
    }
}
