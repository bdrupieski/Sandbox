package JeromqTest;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

public class PathologicSubscriber {

    public static void main(String[] args)
    {
        ZContext context = new ZContext();
        ZMQ.Socket subscriber = context.createSocket(ZMQ.SUB);
        if (args.length == 1)
            subscriber.connect(args[0]);
        else
            subscriber.connect("tcp://localhost:5556");

        Random rand = new Random(System.currentTimeMillis());
        String subscription = String.format("%03d", rand.nextInt(2));
        System.out.println(subscription);
        subscriber.subscribe(subscription.getBytes());

        while (true) {
            String topic = subscriber.recvStr();
            if (topic == null)
                break;
            String data = subscriber.recvStr();
            assert(topic.equals(subscription));
            System.out.println(data);
        }
        context.destroy();
    }
}
