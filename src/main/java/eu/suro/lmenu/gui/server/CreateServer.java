package eu.suro.lmenu.gui.server;

import eu.suro.lmenu.LaunchMenu;
import me.saiintbrisson.minecraft.View;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import server.ServerOuterClass;

import java.util.Arrays;
import java.util.Locale;

public class CreateServer extends View {

    int i = 2;
    public CreateServer(){
        super(1, "Выбор сервера");

        LaunchMenu.getInstance().getVersions().forEach(version -> {
            ItemStack versionItem = new ItemStack(Material.DIRT);
            ItemMeta versionMeta = versionItem.getItemMeta();
            versionMeta.setDisplayName(version.getName());
            versionMeta.setLore(Arrays.asList(version.getDescription().split("\n")));
            versionItem.setItemMeta(versionMeta);
            slot(i++, versionItem).onClick((e) -> {
                LaunchMenu.getInstance().server.CreateServer(e.getPlayer().getName().toLowerCase(Locale.ROOT),
                        true, false, version.getId());
                e.getPlayer().sendMessage("§aИдет создание сервера подождите минуту");
            });
        });
    }
}
