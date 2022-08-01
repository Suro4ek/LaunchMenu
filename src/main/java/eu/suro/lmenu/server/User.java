package eu.suro.lmenu.server;

import eu.suro.lmenu.LaunchMenu;
import io.grpc.stub.StreamObserver;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import server.ServerGrpc;
import user.UserGrpc;
import user.UserOuterClass;

import java.util.Locale;

public class User {
    private UserGrpc.UserStub stub;
    private UserGrpc.UserBlockingStub notBlockStub;
    private FileConfiguration config;

    public User(UserGrpc.UserStub stub, UserGrpc.UserBlockingStub notBlockStub, FileConfiguration config) {
        this.stub = stub;
        this.notBlockStub = notBlockStub;
        this.config = config;
    }

    public UserOuterClass.UserM getUser(String name){
        UserOuterClass.UserM userm = UserOuterClass.UserM.newBuilder().build();
        user.UserOuterClass.GetUserResponse user  = this.notBlockStub.getUser(UserOuterClass.GetUserRequest.
                newBuilder().
                setName(name).
                buildPartial());
       userm = user.getUser();
       return userm;
    }

    public void CreateUser(String name, String real_name){
        UserOuterClass.CreateUserRequest request = UserOuterClass.CreateUserRequest.newBuilder()
                .setName(name).setRealName(real_name).buildPartial();
        this.stub.createUser(request, new StreamObserver<UserOuterClass.Response>() {
            @Override
            public void onNext(UserOuterClass.Response value) {
                LaunchMenu.getInstance().getLogger().info("User created");
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().info("User not created");
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void RemoveWorld(Player player){
        UserOuterClass.RemoveWorldRequest request = UserOuterClass.RemoveWorldRequest.newBuilder()
                .setName(player.getName().toLowerCase(Locale.ROOT)).buildPartial();
        this.stub.deleteWorld(request, new StreamObserver<UserOuterClass.Response>() {
            @Override
            public void onNext(UserOuterClass.Response value) {
                if(player != null){
                    player.sendMessage(config.getString("messages.world_removed"));
                }
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().info("World not removed");
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void stopServer(Player player){
        UserOuterClass.StopServerRequest request = UserOuterClass.StopServerRequest.newBuilder()
                .setUsername(player.getName().toLowerCase(Locale.ROOT)).buildPartial();
        this.stub.stopServer(request, new StreamObserver<UserOuterClass.Response>() {
            @Override
            public void onNext(UserOuterClass.Response value) {
                if(player != null){
                    player.sendMessage(config.getString("messages.server_stopped"));
                }
            }

            @Override
            public void onError(Throwable t) {
                LaunchMenu.getInstance().getLogger().info("Server not stopped");
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
