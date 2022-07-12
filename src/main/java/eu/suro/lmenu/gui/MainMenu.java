package eu.suro.lmenu.gui;

import com.google.common.base.Strings;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.server.Server;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import server.ServerOuterClass;
import user.UserOuterClass;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class MainMenu extends PaginatedView<ServerOuterClass.ServerInfo> {
    public MainMenu() {
        super(6, "Главное меню - Launch+");

        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXX<X>XXX",
                "XXXXXXXXX"
        );
        setSource((context) -> {
          final UserOuterClass.UserM user = context.get("user");
          return LaunchMenu.getInstance().getServers().stream().filter(serverInfo -> {
              //if server is not open filter by friend list and if friend list is player then add list server else server not visible
              if (!serverInfo.getOpen()){
                  if(user.getFriendsList().stream().filter(friend -> friend.getName() == serverInfo.getOwnerName()).collect(Collectors.toList()).size()>0){
                      return true;
                  }
                  return  false;
              }else{
                  return true;
              }
          }).collect(Collectors.toList());
        });
        slot(5,5, new ItemStack(Material.BOOK));
        setNextPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
        setPreviousPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
    }

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
        if(user.getFriendsList().stream().filter(friend -> friend.getName() == value.getOwnerName()).collect(Collectors.toList()).size()>0)
        {
            serverItem.setType(Material.YELLOW_WOOL);
        }else if(value.getOwnerName() == context.getPlayer().getName().toLowerCase(Locale.ROOT)){
            serverItem.setType(Material.GREEN_WOOL);
        }else{
            serverItem.setType(Material.CYAN_WOOL);
        }
        serverItem.setItemMeta(serverItemMeta);
        //config.yml servers [port -> serverName bungeecord]
        viewItem.withItem(serverItem).onClick((e) -> {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            ConfigurationSection section = LaunchMenu.getInstance().getConfig().getConfigurationSection("servers");
            for(String key : section.getKeys(false)){
                if(key == e.data("port")){
                    out.writeUTF(section.getString(key));
                    break;
                }
            }
            e.getPlayer().sendPluginMessage(LaunchMenu.getInstance(), "BungeeCord", out.toByteArray());
        }).setData("port",value.getPort());
    }

}
