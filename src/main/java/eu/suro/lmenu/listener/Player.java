package eu.suro.lmenu.listener;

import eu.suro.lmenu.LaunchMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import user.UserOuterClass;

import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class Player implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        final String username = e.getPlayer().getName().toLowerCase(Locale.ROOT);
        LaunchMenu.getInstance().getUsers().refresh(username);
        UserOuterClass.UserM userM = LaunchMenu.getInstance().getUsers().getIfPresent(username);
        if(userM == null){
            LaunchMenu.getInstance().getUser().CreateUser(username, e.getPlayer().getName());
            LaunchMenu.getInstance().getUsers().refresh(username);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

    }
}
