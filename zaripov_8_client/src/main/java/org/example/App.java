package org.example;

import com.example.grpc.TaskServiceGrpc;
import com.example.grpc.TaskServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        TaskServiceGrpc.TaskServiceBlockingStub stub = TaskServiceGrpc.newBlockingStub(channel);

        TaskServiceOuterClass.TaskRequest1 request = TaskServiceOuterClass.TaskRequest1
                .newBuilder()
                .setId("1")
                .build();

        TaskServiceOuterClass.TaskResponse1 response = stub.getTask(request);

        System.out.println(response.getTask());


        TaskServiceOuterClass.TaskRequest2 request2 = TaskServiceOuterClass.TaskRequest2
                .newBuilder()
                .setTask(TaskServiceOuterClass.Task.newBuilder().setId("4").setDescription("444").setName("44").build())
                .build();

        TaskServiceOuterClass.TaskResponse2 response2 = stub.postTask(request2);

        System.out.println(response2.getAnswer() + "\n");




        TaskServiceOuterClass.TaskRequest1 request3 = TaskServiceOuterClass.TaskRequest1
                .newBuilder()
                .setId("4")
                .build();

        TaskServiceOuterClass.TaskResponse1 response3 = stub.getTask(request3);

        System.out.println(response3.getTask());


        channel.shutdownNow();
    }
}
