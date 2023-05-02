package cn.whitesoul.wsstrengthen;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String startLore = "yes";
        String endLore = "no";
        List<String> oldLore = new ArrayList<>();
        oldLore.add("§7强化等级: §a+0");
        oldLore.add("§7强化成功率: §a100%");
        oldLore.add("§7强化消耗: §a0");
        List<String> newLore = new ArrayList<>();
        newLore.add("§7强化等级: §a+1");
        newLore.add("§7强化成功率: §a100%");
        newLore.add("§7强化消耗: §a0");
        if (oldLore == null) {
            return;
        }
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < oldLore.size(); i++) {
            String lore = oldLore.get(i);
            if (lore.contains(startLore)) {
                startIndex = i;
                continue;
            }
            if (lore.contains(endLore)) {
                endIndex = i;
                break;
            }
        }

        if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
            return;
        }

        for (int i = endIndex; i >= startIndex; i--) {
            oldLore.remove(i);
        }
        oldLore.addAll(startIndex, newLore);
    }
    }
