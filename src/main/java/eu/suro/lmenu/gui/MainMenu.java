package eu.suro.lmenu.gui;

import me.saiintbrisson.minecraft.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends PaginatedView<Integer> {

    public MainMenu() {
        super(6, "Главное меню - Launch+");
        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXX<X>XXX",
                "XXXXXXXXX"
        );
        setNextPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
        setPreviousPageItem((context, item) -> item.withItem(new ItemStack(Material.BONE)));
    }


    @Override
    protected void onUpdate(@NotNull ViewContext context) {
//        context.getPlayer().sendMessage("Updated.");
    }

    @Override
    protected void onItemRender(@NotNull PaginatedViewSlotContext<Integer> context, @NotNull ViewItem viewItem, @NotNull Integer value) {

    }
}
