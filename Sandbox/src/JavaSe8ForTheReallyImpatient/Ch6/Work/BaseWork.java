package JavaSe8ForTheReallyImpatient.Ch6.Work;

abstract class BaseWork implements Runnable {

    private int workNumber;

    BaseWork(int workNumber) {
        this.workNumber = workNumber;
    }

    public final void run() {
        System.out.println(String.format("%d started", workNumber));
        try {
            work();
        } catch (InterruptedException e) {
            System.out.println(String.format("%d interrupted", workNumber));
        }
        System.out.println(String.format("%d finished", workNumber));
    }

    public abstract void work() throws InterruptedException;
}
