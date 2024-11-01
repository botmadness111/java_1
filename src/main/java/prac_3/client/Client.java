package prac_3.client;

import java.io.*;
import java.net.Socket;

public class Client {
    String name;

    Socket connection;

    public Client(String name, Socket connection) {
        this.name = name;
        this.connection = connection;
    }

    public void sendMessage() {
        try {
            DataOutputStream serverOutput = new DataOutputStream(connection.getOutputStream());

            serverOutput.writeBytes("Hello from Client " + name + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMessage() {
        try (InputStream inputStream = connection.getInputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String receivedData = bufferedReader.readLine();
            System.out.println("Received: " + receivedData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
