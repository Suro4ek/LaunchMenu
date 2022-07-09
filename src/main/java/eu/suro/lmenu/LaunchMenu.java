package eu.suro.lmenu;

import eu.suro.lmenu.server.Server;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import server.ServerGrpc;
import server.ServerOuterClass;

import java.util.ArrayList;
import java.util.List;

public final class LaunchMenu extends JavaPlugin {

    static Server server;
    static JavaPlugin plugin;
    static ManagedChannel channel;

    public static List<ServerOuterClass.ServerInfo> servers = new ArrayList<>();
    @Override
    public void onEnable() {
        channel = ManagedChannelBuilder
                .forAddress("localhost",9000)
                .usePlaintext()
                .build();
        ServerGrpc.ServerStub stub = ServerGrpc.newStub(channel);
        Server server = new Server(stub);
        this.server = server;
        this.plugin = this;
        new BukkitRunnable() {

            @Override
            public void run() {
                if(plugin.isEnabled()){
                    server.ListServers();
                }
            }
        }.runTaskTimerAsynchronously(this, 10L, 20*10);
    }

    @Override
    public void onDisable() {
        channel.shutdown();
    }
}