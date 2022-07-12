package eu.suro.lmenu.listener;

import eu.suro.lmenu.LaunchMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import user.UserOuterClass;

import java.util.Locale;
import java.util.Optional;

public class Player implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        final String username = e.getPlayer().getName().toLowerCase(Locale.ROOT);
        Optional<UserOuterClass.UserM> userM = LaunchMenu.getInstance().getUsers().getIfPresent(username);
        if(!userM.isPresent()){
            LaunchMenu.getInstance().getUser().CreateUser(username);
            LaunchMenu.getInstance().getUsers().refresh(username);
        }
    }
}
