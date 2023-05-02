package cn.whitesoul.wsstrengthen.command;

import cn.whitesoul.wsstrengthen.inventory.StrengthenHolder;
import cn.whitesoul.wsstrengthen.util.StrengthenMethod;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class testCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        InventoryHolder holder = new StrengthenHolder();
        player.openInventory(holder.getInventory());
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        StrengthenMethod.updateLore(itemStack,"yes","no", Arrays.asList("abcd","efgh"));
        return false;
    }
}
