package eu.suro.lmenu.gui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.gui.server.CreateServer;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import server.ServerOuterClass;
import user.UserOuterClass;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MainMenu extends PaginatedView<ServerOuterClass.ServerInfo> {

    private FileConfiguration config;
    public MainMenu(FileConfiguration config) {
        super(5, config.getString("main.title"));
        this.config = config;
        setCancelOnClick(true);
        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXX<X>XXX"
        );
        ItemStack bookItem = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = bookItem.getItemMeta();
        bookMeta.setDisplayName(config.getString("main.book.title"));
        bookMeta.setLore(config.getStringList("main.book.lore"));
        bookItem.setItemMeta(bookMeta);
        slot(5,5,bookItem);
        ItemStack createServerItem = new ItemStack(Material.BEACON);
        ItemMeta createServerMeta = createServerItem.getItemMeta();
        createServerMeta.setDisplayName(config.getString("main.create-server.title"));
        createServerMeta.setLore(config.getStringList("main.create-server.lore"));
        createServerItem.setItemMeta(createServerMeta);
        slot(0,5, createServerItem).onClick((e) -> {
            Player sender = e.getPlayer();
            LaunchMenu.getInstance().getView().open(CreateServer.class, sender,
                    new HashMap<String,Object>(){{
                        put("user",LaunchMenu.getInstance().getUsers().getIfPresent(sender.getName()));
                    }});
        });
        setSource((context) -> {
            final UserOuterClass.UserM user = context.get("user");
            List<ServerOuterClass.ServerInfo> serverInfos = LaunchMenu.getInstance().getServers().stream().filter(serverInfo -> {
                //if server is not open filter by friend list and if friend list is player then add list server else server not visible
//                if (!serverInfo.getOpen()){
////                    if(user.getFriendsList().stream().filter(friend -> friend.getName() == serverInfo.getOwnerName()).collect(Collectors.toList()).size()>0){
////                        return true;
////                    }
//                    return  false;
//                }else{
                if(serverInfo.getVersion() == ""){
                    return false;
                }
                    return true;
//                }
            }).collect(Collectors.toList());
            return serverInfos;
        });
        //TODO as 2.5.3 version framework releaed, need to fix this
        scheduleUpdate(20L * 5);

        ItemStack nextPageItem = new ItemStack(Material.ARROW);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        nextPageItemMeta.setDisplayName(config.getString("main.nextPage"));
        nextPageItem.setItemMeta(nextPageItemMeta);
        setNextPageItem((context, item) -> item.withItem(nextPageItem));
        ItemStack previousPageItem = new ItemStack(Material.ARROW);
        ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
        previousPageItemMeta.setDisplayName(config.getString("main.prevPage"));
        previousPageItem.setItemMeta(previousPageItemMeta);
        setPreviousPageItem((context, item) -> item.withItem(previousPageItem));
    }
    //TODO as 2.5.2 version framework releaed, need to fix this
    @Override
    protected void onItemRender(@NotNull PaginatedViewSlotContext<ServerOuterClass.ServerInfo> context,
                                @NotNull ViewItem viewItem,
                                ServerOuterClass.@NotNull ServerInfo value) {
        final UserOuterClass.UserM user = context.get("user");
        ItemStack serverItem = new ItemStack(Material.WHITE_WOOL);
        ItemMeta serverItemMeta = serverItem.getItemMeta();
        serverItemMeta.setDisplayName(config.getString("main.server.title").replace("{name}",value.getOwnerName()));
        List<String> lore = new ArrayList<>();
        lore = config.getStringList("main.server.lore").stream().map(s ->
                s.replace("{name}",value.getOwnerName())
                .replace("{version}", value.getVersion())
                .replace("{players}", value.getPlayers()+"")
                .replace("{maxPlayers}", value.getMaxplayers()+"")).collect(Collectors.toList());
        serverItemMeta.setLore(lore);
//        if(user.getFriendsList() != null && user.getFriendsList().stream().filter(friend -> friend.getName() == value.getOwnerName()).collect(Collectors.toList()).size()>0)
//        {
//            serverItem.setType(Material.YELLOW_WOOL);
//        }else if(value.getOwnerName() == context.getPlayer().getName().toLowerCase(Locale.ROOT)){
//            serverItem.setType(Material.GREEN_WOOL);
//        }else{
//            serverItem.setType(Material.CYAN_WOOL);
//        }
        serverItem.setItemMeta(serverItemMeta);
        //config.yml servers [port -> serverName bungeecord]
        viewItem.withItem(serverItem).onClick((e) -> {
            String server = "L-10";
            ConfigurationSection section = LaunchMenu.getInstance().getConfig().getConfigurationSection("servers");
            for(String key : section.getKeys(false)){
                if(key.equals(value.getPort())){
                    server = section.getString(key);
                    break;
                }
            }

            sendToServer(e.getPlayer(), server);
        });
    }

    public void sendToServer(@NotNull Player target, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        target.sendPluginMessage(LaunchMenu.getInstance(), "BungeeCord", output.toByteArray());
    }
}

