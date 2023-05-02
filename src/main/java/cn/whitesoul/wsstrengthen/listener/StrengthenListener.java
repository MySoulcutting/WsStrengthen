package cn.whitesoul.wsstrengthen.listener;

import cn.whitesoul.wslib.message.Message;
import cn.whitesoul.wsstrengthen.inventory.StrengthenHolder;
import cn.whitesoul.wsstrengthen.util.StrengthenMethod;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class StrengthenListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == null || event.getClickedInventory() == null || event.getClickedInventory().getHolder() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        //获取背包
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof StrengthenHolder) {
            //判断点击的是不是强化界面的按钮
            for (int index : StrengthenHolder.notClickIndex) {
                if (event.getClickedInventory().getHolder().equals(holder) && event.getSlot() == index || event.getSlot() == StrengthenHolder.buttonIndex) {
                    event.setCancelled(true);
                }
            }
            /*
            强化按钮
             */
            //判断点击的是不是强化按钮
            if (event.getClickedInventory().getHolder().equals(holder) && event.getSlot() == StrengthenHolder.buttonIndex) {
                //判断强化物品是否为null
                if (event.getClickedInventory().getItem(StrengthenHolder.itemIndex) != null) {
                    //判断强化石是否为null
                    if (event.getClickedInventory().getItem(StrengthenHolder.stoneIndex) != null) {
                        //判断武器名字是否符合
                        if (event.getClickedInventory().getItem(StrengthenHolder.itemIndex).getItemMeta().getDisplayName() != null && event.getClickedInventory().getItem(StrengthenHolder.itemIndex).getItemMeta().getDisplayName().equals("强化物品") || event.getClickedInventory().getItem(StrengthenHolder.itemIndex).getItemMeta().getDisplayName().equals("强化物品 +" + StrengthenMethod.getStrengthenNumber(event.getClickedInventory().getItem(StrengthenHolder.itemIndex)))) {
                            //判断强化石名字是否符合
                            if (event.getClickedInventory().getItem(StrengthenHolder.stoneIndex).getItemMeta().getDisplayName() != null && event.getClickedInventory().getItem(StrengthenHolder.stoneIndex).getItemMeta().getDisplayName().equals("强化石")) {
                                ItemStack item = event.getClickedInventory().getItem(StrengthenHolder.itemIndex);
                                ItemStack stone = event.getClickedInventory().getItem(StrengthenHolder.stoneIndex);
                                StrengthenMethod.probability(item, stone, player,holder);
                            } else {
                                player.sendMessage(event.getClickedInventory().getItem(StrengthenHolder.stoneIndex).getItemMeta().getDisplayName());
                                Message.sendMessage(player, "&a请放入正确的强化石");
                            }
                        } else {
                            player.sendMessage(event.getClickedInventory().getItem(StrengthenHolder.stoneIndex).getItemMeta().getDisplayName());
                            Message.sendMessage(player, "&a请放入正确的强化物品");
                        }
                    } else {
                        Message.sendMessage(player, "&a请放入强化石");
                    }

                } else {
                    Message.sendMessage(player, "&a请放入强化物品");
                }
            }
            }
        }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder != null && (holder instanceof StrengthenHolder)) {
            //判断强化物品是否为null
            if (event.getInventory().getItem(StrengthenHolder.itemIndex) != null) {
                if (player.getInventory().firstEmpty() == -1) {
                    Message.sendMessage(player, "&c背包已满，强化物品已掉落到地上");
                    player.getWorld().dropItem(player.getLocation(), holder.getInventory().getItem(StrengthenHolder.itemIndex));
                }  else if (player.getInventory().firstEmpty() != -1){
                    player.getInventory().addItem(holder.getInventory().getItem(StrengthenHolder.itemIndex));
                }
            }
            //判断强化石是否为null
            if (event.getInventory().getItem(StrengthenHolder.stoneIndex) != null) {
                if (player.getInventory().firstEmpty() == -1) {
                    Message.sendMessage(player, "&c背包已满，强化石已掉落到地上");
                    player.getWorld().dropItem(player.getLocation(), holder.getInventory().getItem(StrengthenHolder.stoneIndex));
                } else if (player.getInventory().firstEmpty() != -1){
                    player.getInventory().addItem(holder.getInventory().getItem(StrengthenHolder.stoneIndex));
                }
            }
        }
    }
}
