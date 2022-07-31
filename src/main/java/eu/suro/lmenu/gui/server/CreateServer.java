package eu.suro.lmenu.gui.server;

import eu.suro.lmenu.LaunchMenu;
import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import server.ServerOuterClass;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class CreateServer extends PaginatedView<ServerOuterClass.Version> {
    private FileConfiguration config;
    public CreateServer(FileConfiguration config){
        super(5, config.getString("create.title"));
        this.config = config;
        setCancelOnClick(true);
        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXX<X>XXX"
        );
        setSource(e -> {
            return LaunchMenu.getInstance().getVersions().stream().collect(Collectors.toList());
        });
        ItemStack nextPageItem = new ItemStack(Material.ARROW);
        ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();
        nextPageItemMeta.setDisplayName(config.getString("main.nextPage"));
        nextPageItem.setItemMeta(nextPageItemMeta);
        setNextPageItem((context, item) -> item.withItem(nextPageItem));
        ItemStack previousPageItem = new ItemStack(Material.ARROW);
        ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
        previousPageItemMeta.setDisplayName(config.getString("main.prevPage"));
        previousPageItem.setItemMeta(previousPageItemMeta);
        setPreviousPageItem((context, item) -> item.withItem(previousPageItem));
    }

    @Override
    protected void onItemRender(@NotNull PaginatedViewSlotContext<ServerOuterClass.Version> context, @NotNull ViewItem viewItem, ServerOuterClass.@NotNull Version value) {
        ItemStack versionItem = new ItemStack(Material.DIRT);
        ItemMeta versionMeta = versionItem.getItemMeta();
        versionMeta.setDisplayName(value.getName());
        versionMeta.setLore(Arrays.asList(value.getDescription().split("\n")));
        versionItem.setItemMeta(versionMeta);
        viewItem.withItem(versionItem).onClick((e) -> {
            boolean save_world = false;
            if(e.getPlayer().hasPermission(LaunchMenu.PERMISSION_SAVE)){
                save_world = true;
            }
            LaunchMenu.getInstance().server.CreateServer(e.getPlayer(),e.getPlayer().getName().toLowerCase(Locale.ROOT),
                    true, save_world, value.getId());
            e.getPlayer().sendMessage(config.getString("server.create.success"));
        }).closeOnClick();
    }
}
