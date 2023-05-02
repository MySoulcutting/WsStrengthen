package cn.whitesoul.wsstrengthen.inventory;

import cn.whitesoul.wslib.item.WsItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class StrengthenHolder implements InventoryHolder {
    private Inventory inventory;
    public static int[] notClickIndex;
    public static int buttonIndex = 31;
    public static int itemIndex = 13;
    public static int stoneIndex = 37;
    public StrengthenHolder() {
        inventory = Bukkit.createInventory(this, 6 * 9, "强化");
        WsItem glass = new WsItem(Material.STAINED_GLASS_PANE,1,"&a格挡板");
        WsItem button = new WsItem(Material.STAINED_GLASS_PANE,1,"&a强化按钮","&d点击强化","&6强化信息:");
        button.setDurability((short) 5);
        notClickIndex = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 33, 34, 35, 36, 38, 39, 40, 41, 42
        , 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        for (int i : notClickIndex) {
            inventory.setItem(i,glass);
        }
        inventory.setItem(buttonIndex,button);

    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
