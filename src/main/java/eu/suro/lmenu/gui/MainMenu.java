package eu.suro.lmenu.gui;

import com.google.common.base.Strings;
import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.server.Server;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import server.ServerOuterClass;
import user.UserOuterClass;

import java.util.Locale;
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
        serverItemMeta.setDisplayName("§cСервер игрока "+value.getOwnerName());
        if(user.getFriendsList().stream().filter(friend -> friend.getName() == value.getOwnerName()).collect(Collectors.toList()).size()>0)
        {
            serverItem.setType(Material.YELLOW_WOOL);
        }else if(value.getOwnerName() == context.getPlayer().getName().toLowerCase(Locale.ROOT)){
            serverItem.setType(Material.GREEN_WOOL);
        }else{
            serverItem.setType(Material.CYAN_WOOL);
        }
        serverItem.setItemMeta(serverItemMeta);
        //todo connect bungee server
        // config.yml servers [port -> serverName bungeecord]
        viewItem.withItem(serverItem).onClick((e) -> {

        });
    }
}
