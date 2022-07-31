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
import java.util.Locale;

public class ServerSettings extends View {

    private FileConfiguration config;
    public ServerSettings(FileConfiguration config){
        super(1, config.getString("settings.title"));
        this.config = config;
        setCancelOnClick(true);
//        //Stop server, check port
        ItemStack stopServer = new ItemStack(Material.BARRIER);
        ItemMeta stopServerMeta = stopServer.getItemMeta();
        stopServerMeta.setDisplayName(config.getString("settings.stopServer.name"));
        stopServerMeta.setLore(config.getStringList("settings.stopServer.lore"));
        stopServer.setItemMeta(stopServerMeta);
        slot(3, stopServer).onClick((e) -> {
            LaunchMenu.getInstance().getUser().stopServer(e.getPlayer());
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
        if(context.getPlayer().hasPermission(LaunchMenu.PERMISSION_SAVE)){
            ItemStack deleteworld = new ItemStack(Material.BARRIER);
            ItemMeta stopServerMeta = deleteworld.getItemMeta();
            stopServerMeta.setDisplayName(config.getString("settings.deleteworld.name"));
            stopServerMeta.setLore(config.getStringList("settings.deleteworld.lore"));
            deleteworld.setItemMeta(stopServerMeta);
            slot(5, deleteworld).onClick(e -> {
                LaunchMenu.getInstance().getUser().RemoveWorld(e.getPlayer());
            });
        }
    }
}
