package eu.suro.lmenu.gui.settings;

import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.gui.MainMenu;
import eu.suro.lmenu.gui.friends.MainFriends;
import me.saiintbrisson.minecraft.OpenViewContext;
import me.saiintbrisson.minecraft.View;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ServerSettings extends View {

    public ServerSettings(){
        super(1, "Настройка своего сервера");
        //todo user data
        slot(2, new ItemStack(Material.ARROW)).onClick((e) -> {e.open(MainMenu.class);});
//        //Stop server, check port
//        slot(3, new ItemStack(Material.RED_WOOL)).onClick((e) -> {
//            LaunchMenu.getInstance().server.DeleteServer();
//        });
        //Friends todo add friends
        slot(4, new ItemStack(Material.PLAYER_HEAD)).onClick((e) -> {e.open(MainFriends.class);});
    }

    @Override
    protected void onOpen(@NotNull OpenViewContext context) {
        //Delete world
        if(context.getPlayer().hasPermission("launch.save")){
            slot(5, new ItemStack(Material.BARRIER));
        }
    }
}
