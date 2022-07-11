package eu.suro.lmenu.server;

import io.grpc.stub.StreamObserver;
import server.ServerGrpc;
import server.ServerOuterClass;
import user.UserGrpc;
import user.UserOuterClass;

import java.util.Locale;

public class User {
    private UserGrpc.UserStub stub;

    public User(UserGrpc.UserStub stub){
        this.stub = stub;
    }

    public UserOuterClass.UserM getUser(String name){
        UserOuterClass.UserM userm = UserOuterClass.UserM.newBuilder().build();
        GetUser user = new GetUser(userm);
        this.stub.getUser(UserOuterClass.GetUserRequest.
                newBuilder().
                setName(name.toLowerCase(Locale.ROOT)).
                build(), user);
        return userm;
    }

    public void CreateUser(String name){
        UserOuterClass.CreateUserRequest request = UserOuterClass.CreateUserRequest.newBuilder()
                .setName(name).buildPartial();
        this.stub.createUser(request, new FriendCallback());
    }

    class GetUser implements StreamObserver<UserOuterClass.GetUserResponse>{
        UserOuterClass.UserM userm;
        public GetUser(UserOuterClass.UserM userm){
            this.userm = userm;
        }

        @Override
        public void onNext(UserOuterClass.GetUserResponse value) {
            this.userm = value.getUser();
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }

    public void removeFriend(String username, String friend_name){
        stub.removeFriend(UserOuterClass.RemoveFriendRequest
                .newBuilder()
                        .setName(username.toLowerCase(Locale.ROOT))
                        .setFriendName(friend_name.toLowerCase(Locale.ROOT))
                .build(), new FriendCallback());
    }

    class FriendCallback implements StreamObserver<UserOuterClass.Response>{
        @Override
        public void onNext(UserOuterClass.Response value) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onCompleted() {

        }
    }
}
