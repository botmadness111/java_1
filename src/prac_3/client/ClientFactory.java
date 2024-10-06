package prac_3.client;

import prac_3.server.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientFactory {
    public static void main(String[] args) throws InterruptedException, IOException {
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            clients.add(new Client(i + "", new Socket("localhost", Server.PORT)));
        }

        for (Client client : clients) {
            client.sendMessage();
        }

        Thread.sleep(6000);

        for (Client client : clients) {
            new Thread(client::getMessage).start();
        }
    }
}
