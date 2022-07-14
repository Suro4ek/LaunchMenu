package eu.suro.lmenu.gui.friends;

import dev.dbassett.skullcreator.SkullCreator;
import eu.suro.lmenu.LaunchMenu;
import me.saiintbrisson.minecraft.PaginatedView;
import me.saiintbrisson.minecraft.PaginatedViewSlotContext;
import me.saiintbrisson.minecraft.ViewItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import user.UserOuterClass;

public class MainFriends extends PaginatedView<UserOuterClass.UserM> {

    public MainFriends(){
        super(5, "Друзья");

        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXX<X>XXX"
        );

        setSource((context) -> {
            final UserOuterClass.UserM user = context.get("user");
            return user.getFriendsList();
        });

        setNextPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
        setPreviousPageItem((context, item) -> item.withItem(new ItemStack(Material.ARROW)));
    }
    @Override
    protected void onItemRender(@NotNull PaginatedViewSlotContext<UserOuterClass.UserM> context, @NotNull ViewItem viewItem, UserOuterClass.@NotNull UserM value) {
        viewItem.withItem(SkullCreator.itemFromName(value.getName())).onClick((e) -> {
            LaunchMenu.getInstance().getUser().removeFriend(e.getPlayer().getName(), value.getName());
        });
    }
}
