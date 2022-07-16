package eu.suro.lmenu.gui.server;

import eu.suro.lmenu.LaunchMenu;
import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;

public class CreateServer extends View {


    public CreateServer(){
        super(1, "Выбор сервера");
        setCancelOnClick(true);

    }
    @Override
    protected void onRender(@NotNull ViewContext context) {
        LaunchMenu.getInstance().getLogger().info(LaunchMenu.getInstance().getVersions().size() + " versions");
        LaunchMenu.getInstance().getVersions().forEach(version -> {
            ItemStack versionItem = new ItemStack(Material.DIRT);
            ItemMeta versionMeta = versionItem.getItemMeta();
            versionMeta.setDisplayName(version.getName());
            versionMeta.setLore(Arrays.asList(version.getDescription().split("\n")));
            versionItem.setItemMeta(versionMeta);
            slot(2, versionItem).onClick((e) -> {
                LaunchMenu.getInstance().server.CreateServer(e.getPlayer(),e.getPlayer().getName().toLowerCase(Locale.ROOT),
                        true, false, version.getId());
                e.getPlayer().sendMessage("§aИдет создание сервера подождите минуту");
            }).closeOnClick();
        });
    }
}
