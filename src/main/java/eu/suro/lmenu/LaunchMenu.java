package eu.suro.lmenu;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import eu.suro.lmenu.commands.Command;
import eu.suro.lmenu.config.Config;
import eu.suro.lmenu.gui.MainMenu;
import eu.suro.lmenu.gui.friends.MainFriends;
import eu.suro.lmenu.gui.settings.ServerSettings;
import eu.suro.lmenu.server.Server;
import eu.suro.lmenu.server.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.saiintbrisson.minecraft.ViewFrame;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import server.ServerGrpc;
import server.ServerOuterClass;
import user.UserGrpc;
import user.UserOuterClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public final class LaunchMenu extends JavaPlugin {
    static LaunchMenu instance;
    public Server server;
    private User user;
    private ManagedChannel channel;
    private ViewFrame view;
    private List<ServerOuterClass.ServerInfo> servers = new ArrayList<>();
    //cache users
    public LoadingCache<String, Optional<UserOuterClass.UserM>> users = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, Optional<UserOuterClass.UserM>>() {
                        @Override
                        public Optional<UserOuterClass.UserM> load(String key) throws Exception {
                            return Optional.ofNullable(user.getUser(key.toLowerCase(Locale.ROOT)));
                        }
                    }
            );
    @Override
    public void onEnable() {
        //init
        instance = this;
        Config config = new Config();
        //grpc
        createGrpcClient(config);
        //register bungee message out
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        //create menus
        view.with(new MainMenu(), new ServerSettings(), new MainFriends());
        //register commands
        new Command();
    }

    public void createGrpcClient(Config config){
        channel = ManagedChannelBuilder
                .forAddress(config.getString("grpc.host"),config.getInt("grpc.port"))
                .usePlaintext()
                .build();
        //Maybe future???
        UserGrpc.UserBlockingStub userStub = UserGrpc.newBlockingStub(channel);
        ServerGrpc.ServerBlockingStub stub = ServerGrpc.newBlockingStub(channel);
        Server server = new Server(stub);
        this.server = server;
        user = new User(userStub);
        //schedule for update list servers
        new BukkitRunnable() {
            @Override
            public void run() {
                if(getInstance().isEnabled()){
                    server.ListServers();
                }
            }
        }.runTaskTimerAsynchronously(this, 10L, 20*10);
    }

    @Override
    public void onDisable() {
        //disable grpc client
        channel.shutdown();
    }

    public static LaunchMenu getInstance() {
        return instance;
    }

    public List<ServerOuterClass.ServerInfo> getServers() {
        return servers;
    }

    public ViewFrame getView() {
        return view;
    }

    public User getUser() {
        return user;
    }

    public LoadingCache<String, Optional<UserOuterClass.UserM>> getUsers() {
        return users;
    }

    public void setServers(List<ServerOuterClass.ServerInfo> servers) {
        this.servers = servers;
    }

}