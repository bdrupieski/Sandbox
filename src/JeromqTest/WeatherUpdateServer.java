package JeromqTest;

import org.zeromq.ZMQ;

import java.util.Random;

public class WeatherUpdateServer {
    public static void main(String[] args) throws Exception {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        publisher.bind("tcp://*:5556");
        publisher.bind("ipc://weather");

        Random srandom = new Random(System.currentTimeMillis());
        while (!Thread.currentThread().isInterrupted()) {
            int zipcode, temperature, relHumidity;
            zipcode = 10000 + srandom.nextInt(10000);
            temperature = srandom.nextInt(215) - 80 + 1;
            relHumidity = srandom.nextInt(50) + 10 + 1;

            String update = String.format("%05d %d %d", zipcode, temperature, relHumidity);
            //System.out.println("sending update: " + update);
            publisher.send(update, 0);
        }

        publisher.close();
        context.term();
    }
}
