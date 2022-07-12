package eu.suro.lmenu.commands;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.implementation.LiteFactory;
import eu.suro.lmenu.LaunchMenu;
import org.bukkit.command.CommandSender;

public class Command {

    LiteCommands<CommandSender> commands;
    public Command(){
        commands = LiteFactory.builder(CommandSender.class)
                .command(LaunchCommand.class)
                .register();
    }
}
