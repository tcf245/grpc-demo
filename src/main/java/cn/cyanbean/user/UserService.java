package cn.cyanbean.user;

import cn.cyanbean.grpc.User;
import cn.cyanbean.grpc.userGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class UserService extends userGrpc.userImplBase {

    @Override
    public void login(User.LoginRequest request, StreamObserver<User.APIResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();
        System.out.printf("get request. username: %s, password: %s%n", username, password);

        User.APIResponse.Builder response = User.APIResponse.newBuilder();
        if (username.equals(password)){
            // return SUCCESS
            response.setCode(0).setMsg("SUCCESS").setData("");
        } else {
            // return FAILURE
            response.setCode(100).setMsg("FAILURE").setData("");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void logout(User.Empty request, StreamObserver<User.APIResponse> responseObserver) {
        User.APIResponse response = User.APIResponse.newBuilder().setCode(0).setMsg("OK").setData("").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(9090).addService(new UserService()).build();
        server.start();
        System.out.printf("server User is started at %d%n", server.getPort());
        server.awaitTermination();
    }
}
