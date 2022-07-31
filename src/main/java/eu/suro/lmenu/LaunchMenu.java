package eu.suro.lmenu;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import eu.suro.lmenu.commands.Command;
import eu.suro.lmenu.config.Config;
import eu.suro.lmenu.gui.MainMenu;
import eu.suro.lmenu.gui.server.CreateServer;
import eu.suro.lmenu.gui.settings.ServerSettings;
import eu.suro.lmenu.listener.Player;
import eu.suro.lmenu.server.Server;
import eu.suro.lmenu.server.User;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
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
import java.util.logging.Logger;

public final class LaunchMenu extends JavaPlugin {

    public static final String PERMISSION_SAVE = "launch.save";
    static LaunchMenu instance;
    public Server server;
    private User user;
    private ManagedChannel channel;
    private ViewFrame view;
    private List<ServerOuterClass.ServerInfo> servers = new ArrayList<>();
    private List<ServerOuterClass.Plugin> plugins = new ArrayList<>();
    private List<ServerOuterClass.Version> versions = new ArrayList<>();

    //cache users
    public LoadingCache<String, UserOuterClass.UserM> users = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, UserOuterClass.UserM>() {
                        @Override
                        public UserOuterClass.UserM load(String key) throws Exception {
                            return user.getUser(key.toLowerCase(Locale.ROOT));
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
        view = new ViewFrame(this);
        view.addView(new MainMenu(config.getConfig()),
                new ServerSettings(config.getConfig()),
                new CreateServer(config.getConfig()));
        view.register();
        //register commands
        new Command();
        //register events
        getServer().getPluginManager().registerEvents(new Player(), this);
    }

    public void createGrpcClient(Config config){
        ChannelCredentials credentials = InsecureChannelCredentials.create();
        channel = Grpc.newChannelBuilder(config.getString("grpc.host")+":"+config.getString("grpc.host"),
                        credentials)
                .build();
            UserGrpc.UserStub userStub = UserGrpc.newStub(channel);
            UserGrpc.UserBlockingStub userBlockingStub = UserGrpc.newBlockingStub(channel);
            ServerGrpc.ServerStub stub = ServerGrpc.newStub(channel);
            Server server = new Server(stub, config.getConfig());
            this.server = server;
            server.ListVersions();
            user = new User(userStub, userBlockingStub, config.getConfig());
        //schedule for update list servers
        new BukkitRunnable() {
            @Override
            public void run() {
                if(getInstance().isEnabled()){
                    server.ListServers();
                }
            }
        }.runTaskTimerAsynchronously(this, 20L*10, 20L*10);
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

    public List<ServerOuterClass.Plugin> getPlugins() {
        return plugins;
    }

    public List<ServerOuterClass.Version> getVersions() {
        return versions;
    }

    public void setPlugins(List<ServerOuterClass.Plugin> plugins) {
        this.plugins = plugins;
    }

    public void setVersions(List<ServerOuterClass.Version> versions) {
        this.versions = versions;
    }

    public ViewFrame getView() {
        return view;
    }

    public User getUser() {
        return user;
    }

    public LoadingCache<String, UserOuterClass.UserM> getUsers() {
        return users;
    }

    public void setServers(List<ServerOuterClass.ServerInfo> servers) {
        this.servers = servers;
    }

}