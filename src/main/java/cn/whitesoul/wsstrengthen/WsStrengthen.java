package cn.whitesoul.wsstrengthen;

import cn.whitesoul.wsstrengthen.command.testCommand;
import cn.whitesoul.wsstrengthen.listener.StrengthenListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WsStrengthen extends JavaPlugin {
    public static WsStrengthen INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getServer().getPluginManager().registerEvents(new StrengthenListener(), this);
        getCommand("test").setExecutor(new testCommand());
    }

    @Override
    public void onDisable() {
    }
}
