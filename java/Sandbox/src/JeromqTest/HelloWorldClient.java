package JeromqTest;

import org.zeromq.ZMQ;

public class HelloWorldClient {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);

        System.out.println(String.format("Version string: %s, Version int: %d",
                ZMQ.getVersionString(),
                ZMQ.getFullVersion()));

        //  Socket to talk to server
        System.out.println("Connecting to hello world server…");

        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://localhost:5555");

        for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
            String request = "Hello";
            System.out.println("Sending Hello " + requestNbr);
            requester.send(request.getBytes(), 0);

            byte[] reply = requester.recv(0);
            System.out.println("Received " + new String(reply) + " " + requestNbr);
        }
        requester.close();
        context.term();
    }
}
