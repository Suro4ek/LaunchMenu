package eu.suro.lmenu;

import eu.suro.lmenu.server.Server;
import eu.suro.lmenu.server.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import server.ServerGrpc;
import server.ServerOuterClass;
import user.UserGrpc;

import java.util.ArrayList;
import java.util.List;

public final class LaunchMenu extends JavaPlugin {
    static LaunchMenu instance;
    public Server server;
    private User user;
    private ManagedChannel channel;

    private List<ServerOuterClass.ServerInfo> servers = new ArrayList<>();
    @Override
    public void onEnable() {
        channel = ManagedChannelBuilder
                .forAddress("localhost",9000)
                .usePlaintext()
                .build();
        UserGrpc.UserStub userStub = UserGrpc.newStub(channel);
        ServerGrpc.ServerStub stub = ServerGrpc.newStub(channel);
        Server server = new Server(stub);
        this.server = server;
        user = new User(userStub);
        instance = this;
        new BukkitRunnable() {

            @Override
            public void run() {
                if(getInstance().isEnabled()){
                    server.ListServers();
                }
            }
        }.runTaskTimerAsynchronously(this, 10L, 20*10);
    }

    public static LaunchMenu getInstance() {
        return instance;
    }

    public List<ServerOuterClass.ServerInfo> getServers() {
        return servers;
    }

    public User getUser() {
        return user;
    }

    public void setServers(List<ServerOuterClass.ServerInfo> servers) {
        this.servers = servers;
    }

    @Override
    public void onDisable() {
        channel.shutdown();
    }
}