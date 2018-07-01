package JeromqTest;

import org.zeromq.ZMQ;

import java.text.MessageFormat;
import java.util.StringTokenizer;

public class WeatherUpdateClient {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        System.out.println("Collecting updates from weather server");
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        subscriber.connect("tcp://localhost:5556");

        //  Subscribe to zipcode, default is NYC, 10001
        String filter = (args.length > 0) ? args[0] : "10001";
        subscriber.subscribe(filter.getBytes());

        int update_nbr;
        long total_temp = 0;
        for (update_nbr = 0; update_nbr < 100; update_nbr++) {
            //  Use trim to remove the tailing '0' character
            String string = subscriber.recvStr(0).trim();

            StringTokenizer sscanf = new StringTokenizer(string, " ");
            int zipcode = Integer.valueOf(sscanf.nextToken());
            int temperature = Integer.valueOf(sscanf.nextToken());
            int relhumidity = Integer.valueOf(sscanf.nextToken());

            System.out.println(MessageFormat.format("Received {0} for zip {1}", temperature, zipcode));

            total_temp += temperature;

        }
        int averageTemp = (int)(total_temp / update_nbr);
        System.out.println(MessageFormat.format("Average temperature for zipcode {0} was {1}", filter, averageTemp));

        subscriber.close();
        context.term();
    }
}
