package eu.suro.lmenu.commands;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.implementation.LiteFactory;
import eu.suro.lmenu.LaunchMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command {

    LiteCommands<CommandSender> commands;
    public Command(){
        commands = LiteBukkitFactory.builder(LaunchMenu.getInstance().getServer(), "LaunchMenu")
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual("&cТолько игрок может!"))
                .command(LaunchCommand.class)
                .register();
    }
}
