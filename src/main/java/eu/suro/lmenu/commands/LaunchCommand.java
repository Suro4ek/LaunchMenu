package eu.suro.lmenu.commands;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.section.Section;
import dev.rollczi.litecommands.platform.LiteSender;
import eu.suro.lmenu.LaunchMenu;
import eu.suro.lmenu.gui.MainMenu;
import eu.suro.lmenu.gui.friends.MainFriends;
import eu.suro.lmenu.gui.settings.ServerSettings;
import me.saiintbrisson.minecraft.ViewFrame;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Locale;

@Section(route = "launch")
public class LaunchCommand {

    @Execute(required = 0)
    public void CommandsList(Player sender){
        sender.sendMessage("Помощь по командам");
    }

    @Execute(required = 1, route = "menu")
    public void OpenMenu(Player sender){
        LaunchMenu.getInstance().getView().open(MainMenu.class, sender,
                new HashMap<String,Object>(){{
            put("user",LaunchMenu.getInstance().getUsers().getIfPresent(sender.getName()));
        }});
    }

    @Execute(required = 1, route = "friends")
    public void OpenFriends(Player sender){
        LaunchMenu.getInstance().getView().open(MainFriends.class, sender,
                new HashMap<String,Object>(){{
                    put("user",LaunchMenu.getInstance().getUsers().getIfPresent(sender.getName()));
                }});
    }

    @Execute(required = 1, route = "settings")
    public void OpenSettings(Player sender){
        LaunchMenu.getInstance().getView().open(ServerSettings.class, sender,
                new HashMap<String,Object>(){{
                    put("user",LaunchMenu.getInstance().getUsers().getIfPresent(sender.getName()));
                }});
    }

    @Execute(required = 2, route = "friend")
    public void Friend(Player sender, @Arg String command, @Arg @Name("friend") String player ){
        if (command.equals("add")){
            LaunchMenu.getInstance().getUser().addFriend(sender.getName().toLowerCase(Locale.ROOT),
                    player.toLowerCase());
        }else{
            sender.sendMessage("§cНеизвестная команда");
        }
    }
}
