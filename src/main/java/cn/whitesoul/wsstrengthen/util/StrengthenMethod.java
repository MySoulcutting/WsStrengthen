package cn.whitesoul.wsstrengthen.util;

import cn.whitesoul.wslib.item.WsItem;
import cn.whitesoul.wslib.message.Message;
import cn.whitesoul.wsstrengthen.inventory.StrengthenHolder;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrengthenMethod {
    private static int number;
    private static int nextNumber;
    private static String name;
    public static void updateLore(ItemStack itemStack, String startLore, String endLore, List<String> newLore) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < lore.size(); i++) {
            String currentLore = lore.get(i);

            if (currentLore.contains(startLore)) {
                startIndex = i;
            }

            if (currentLore.contains(endLore)) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1) {
            return;
        }

        lore.subList(startIndex + 1, endIndex).clear();

        for (String newLine : newLore) {
            lore.add(startIndex + 1, newLine);
            startIndex++;
        }

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }
    //获取强化等级
    public static int getStrengthenNumber(ItemStack itemStack) {
        WsItem item = new WsItem(itemStack);
        String s = item.getItemMeta().getDisplayName();
        String patternStr = "\\+(\\d+)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            number = Integer.parseInt(matcher.group(1));
        }
        return number;
    }
    //获取完整的强化等级
    public static String getStrengthenName(ItemStack itemStack) {
        WsItem item = new WsItem(itemStack);
        String s = item.getItemMeta().getDisplayName();
        String patternStr = "\\+(\\d+)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            name  = matcher.group();
        }
        return name;
    }
    //是否包含强化等级
    public static boolean isStrengthen(ItemStack itemStack) {
        WsItem item = new WsItem(itemStack);
        String s = item.getDisplayName();
        String patternStr = "\\+(\\d+)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
    //强化几率
    public static void probability(ItemStack strengthen , ItemStack stone , Player player ,InventoryHolder holder) {
        //强化满级
        if (getStrengthenNumber(strengthen) == 10) {
            Message.sendMessage(player, "&c强化失败，已经强化到最高等级");
            return;
        }
        /*
        强化几率的操作
        */
        Random rand = new Random();
        int randomNumber = rand.nextInt(101); // 100%的几率
        //强化成功
        if (randomNumber <= 50) {
            success(strengthen,stone,player,holder);
        } else {
            //强化失败
            failure(strengthen,stone,player,holder);
        }
    }
    //强化成功
    public static void success(ItemStack strengthen , ItemStack stone , Player player ,InventoryHolder holder) {
        /*
        强化成功的操作
        */
        WsItem wsItem = new WsItem(strengthen);
        WsItem wsStone = new WsItem(stone);
        //是否第一次强化
        if (!isStrengthen(strengthen)) {
            String oldName = wsItem.getDisplayName();
            String newName = " +1";
            wsItem.setDisplayName(oldName + newName);
            //第一次强化
            wsItem.addLore("yes");
            wsItem.addLore("abcd");
            wsItem.addLore("1234");
            wsItem.addLore("no");
            Message.sendMessage(player, "&a强化成功" + 1 + "级");
        } else {
            number = getStrengthenNumber(strengthen);
            nextNumber = number + 1;
            //不是第一次强化
            wsItem.setDisplayName(wsItem.getDisplayName().replace(number+"",nextNumber+""));
            updateLore(wsItem, "yes", "no", Arrays.asList("aaaaa", "123bbbbbbb4"));
            if (nextNumber == 10){
                Message.sendAllPlayerMessage("恭喜这个B玩家强化到了10级");
            }
        }
        wsStone.setAmount(wsStone.getAmount() - 1);
        Message.sendMessage(player, "&a强化成功 当前为" + nextNumber + "级");
        holder.getInventory().setItem(StrengthenHolder.itemIndex, wsItem);
        holder.getInventory().setItem(StrengthenHolder.stoneIndex, wsStone);
    }
    //强化失败
    public static void failure(ItemStack strengthen , ItemStack stone , Player player ,InventoryHolder holder) {
        /*
        强化失败的操作
        */
        //强化到0级
        if (getStrengthenNumber(strengthen) == 0) {
            Message.sendMessage(player, "&c强化失败，已经强化到最低等级");
            return;
        }
        WsItem wsItem = new WsItem(strengthen);
        WsItem wsStone = new WsItem(stone);
        nextNumber = number - 1;
        wsStone.setAmount(wsStone.getAmount() - 1);
        Message.sendMessage(player, "&c强化失败 当前为" + nextNumber + "级");
        updateLore(wsItem, "yes", "no", Arrays.asList("aaaaa", "123bbbbbbb4"));
        wsItem.setDisplayName(wsItem.getDisplayName().replace(number+"",nextNumber+""));
        holder.getInventory().setItem(StrengthenHolder.itemIndex, wsItem);
        holder.getInventory().setItem(StrengthenHolder.stoneIndex, wsStone);
    }
}
