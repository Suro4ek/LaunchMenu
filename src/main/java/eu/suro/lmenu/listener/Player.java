package eu.suro.lmenu.listener;

import eu.suro.lmenu.LaunchMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import user.UserOuterClass;

import java.util.Locale;

public class Player implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        final String username = e.getPlayer().getName().toLowerCase(Locale.ROOT);
        UserOuterClass.UserM userM = LaunchMenu.getInstance().getUser().getUser(username);
        if(userM.getName() == ""){
            LaunchMenu.getInstance().getUser().CreateUser(username);
            userM = UserOuterClass.UserM.newBuilder().setName(username).build();
        }
    }
}
