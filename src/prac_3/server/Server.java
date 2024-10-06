package prac_3.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int PORT = 50001;

    private static final List<String> messages = new ArrayList<>();

    private static long startTime;

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        start();
    }

    private static void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = serverSocket.accept(); //wait connection with client
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            work(connection);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void work(Socket connection) throws IOException, InterruptedException {
        try (InputStream inputStream = connection.getInputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            synchronized (messages) {
                String receivedData = bufferedReader.readLine();
                messages.add(receivedData);
            }

            while (true) {
                if (System.currentTimeMillis() - startTime >= 5000) {
                    sendMessage(connection);
                    break;
                }
            }
        }
    }

    public static void sendMessage(Socket connection) throws IOException {
        DataOutputStream serverOutput = new DataOutputStream(connection.getOutputStream());

        synchronized (messages) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String data : messages) stringBuilder.append(data).append(" ");
            stringBuilder.append("\n");

            serverOutput.writeBytes(stringBuilder.toString());
        }
    }
}
