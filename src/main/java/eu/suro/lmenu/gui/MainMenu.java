package eu.suro.lmenu.gui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.gui.server.CreateServer;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
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

    public MainMenu() {
        super(5, "Главное меню - Launch+");
        setCancelOnClick(true);
        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXX<X>XXX"
        );

        slot(5,5, new ItemStack(Material.BOOK));
        slot(0,5,new ItemStack(Material.BEACON)).onClick((e) -> {
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
        //TODO as 2.5.2 version framework releaed, need to fix this
//        scheduleUpdate(20L * 5);
        setNextPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
        setPreviousPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
    }
    //TODO as 2.5.2 version framework releaed, need to fix this
//    @Override
//    protected void onRender(@NotNull ViewContext context) {
//        setSource((context1) -> {
//            final UserOuterClass.UserM user = context1.get("user");
//            List<ServerOuterClass.ServerInfo> serverInfos = LaunchMenu.getInstance().getServers().stream().filter(serverInfo -> {
//                //if server is not open filter by friend list and if friend list is player then add list server else server not visible
////                if (!serverInfo.getOpen()){
//////                    if(user.getFriendsList().stream().filter(friend -> friend.getName() == serverInfo.getOwnerName()).collect(Collectors.toList()).size()>0){
//////                        return true;
//////                    }
////                    return  false;
////                }else{
//                if(serverInfo.getVersion() == ""){
//                    return false;
//                }
//                return true;
////                }
//            }).collect(Collectors.toList());
//            return serverInfos;
//        });
//    }


    @Override
    protected void onItemRender(@NotNull PaginatedViewSlotContext<ServerOuterClass.ServerInfo> context,
                                @NotNull ViewItem viewItem,
                                ServerOuterClass.@NotNull ServerInfo value) {
        final UserOuterClass.UserM user = context.get("user");
        ItemStack serverItem = new ItemStack(Material.WHITE_WOOL);
        ItemMeta serverItemMeta = serverItem.getItemMeta();
        //todo text to config
        serverItemMeta.setDisplayName("§cСервер игрока "+value.getOwnerName());
        //todo add lore text
        List<String> lore = new ArrayList<>();
        lore.add("§7Владелец: "+value.getOwnerName());
        lore.add("§7Порт: "+value.getPort());
        lore.add("§7Открыт: "+value.getOpen());
        lore.add("§7Игроков: "+value.getPlayers());
        lore.add("§7Максимальное количество игроков: "+value.getMaxplayers());
        lore.add("§7Версия: "+value.getVersion());
        lore.add("§7Статус: "+value.getStatus());
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

    public void sendToServer(Player target, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();

        output.writeUTF("Connect");
        output.writeUTF(server);
        target.sendMessage("§aПереход на сервер §c"+server);
        target.sendPluginMessage(LaunchMenu.getInstance(), "BungeeCord", output.toByteArray());
    }
}

