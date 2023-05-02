package cn.whitesoul.wsstrengthen.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Strengthen implements InventoryHolder {
    private Inventory inventory;
    public Strengthen() {
        inventory = Bukkit.createInventory(this, 6 * 9, "强化");
    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
