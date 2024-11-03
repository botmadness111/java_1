package org.example.service;


import com.example.grpc.TaskServiceGrpc;
import com.example.grpc.TaskServiceOuterClass;
import org.example.model.Task;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

public class TaskService extends TaskServiceGrpc.TaskServiceImplBase {

    @Override
    public void getTask(TaskServiceOuterClass.TaskRequest1 request,
                        io.grpc.stub.StreamObserver<TaskServiceOuterClass.TaskResponse1> responseObserver) {

        String id = request.getId();

        Task task1 = Task.findById(id);

        TaskServiceOuterClass.Task task = TaskServiceOuterClass.Task
                .newBuilder()
                .setId(task1.id())
                .setName(task1.name())
                .setDescription(task1.description())
                .build();

        TaskServiceOuterClass.TaskResponse1 response = TaskServiceOuterClass
                .TaskResponse1.newBuilder()
                .setTask(task)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();

    }

    @Override
    public void postTask(com.example.grpc.TaskServiceOuterClass.TaskRequest2 request,
                         io.grpc.stub.StreamObserver<com.example.grpc.TaskServiceOuterClass.TaskResponse2> responseObserver) {

        TaskServiceOuterClass.Task task = request.getTask();

        Task taskNew = new Task(task.getId(), task.getName(), task.getDescription());
        Task.addTask(taskNew);

        TaskServiceOuterClass.TaskResponse2 response = TaskServiceOuterClass.TaskResponse2
                .newBuilder()
                .setAnswer("Ok")
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

    @Override
    public void deleteTask(com.example.grpc.TaskServiceOuterClass.TaskRequest1 request,
                           io.grpc.stub.StreamObserver<com.example.grpc.TaskServiceOuterClass.TaskResponse1> responseObserver) {

        String id = request.getId();

        Task task = Task.deleteTask(id);

        TaskServiceOuterClass.Task task1 = TaskServiceOuterClass.Task
                .newBuilder()
                .setId(task.id())
                .setName(task.name())
                .setDescription(task.description())
                .build();

        TaskServiceOuterClass.TaskResponse1 response = TaskServiceOuterClass.TaskResponse1
                .newBuilder()
                .setTask(task1)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

}
