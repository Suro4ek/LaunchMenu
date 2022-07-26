package eu.suro.lmenu.gui.settings;

import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.gui.MainMenu;
import me.saiintbrisson.minecraft.OpenViewContext;
import me.saiintbrisson.minecraft.View;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ServerSettings extends View {

    public ServerSettings(FileConfiguration config){
        super(1, config.getString("settings.title"));
        setCancelOnClick(true);
//        //Stop server, check port
        ItemStack stopServer = new ItemStack(Material.BARRIER);
        ItemMeta stopServerMeta = stopServer.getItemMeta();
        stopServerMeta.setDisplayName(config.getString("settings.stopServer"));
        stopServer.setItemMeta(stopServerMeta);
        slot(3, stopServer).onClick((e) -> {
            e.getPlayer().sendMessage("§cSoon");
//            LaunchMenu.getInstance().server.DeleteServer();
        });
//        slot(4, new ItemStack(Material.PLAYER_HEAD)).onClick((e) -> {e.open(MainFriends.class,
//                new HashMap<String,Object>(){{
//                    put("user",LaunchMenu.getInstance().getUsers().getIfPresent(e.getPlayer().getName()));
//        }});});
    }

    @Override
    protected void onOpen(@NotNull OpenViewContext context) {
        //Delete world
        if(context.getPlayer().hasPermission("launch.save")){
            slot(5, new ItemStack(Material.BARRIER));
        }
    }
}
