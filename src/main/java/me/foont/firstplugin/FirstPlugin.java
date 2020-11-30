package me.foont.firstplugin;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.KeyAlreadyRegisteredException;
import com.palmergames.bukkit.towny.object.metadata.IntegerDataField;
import me.foont.firstplugin.commands.buyracks1;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import me.foont.firstplugin.tasks.racksTask;


public final class FirstPlugin extends JavaPlugin {

    private static String keyname = "barrackObama"; // This key must be unique to your plugin.
    private static int defaultVal = 0; // This is the default value your data field will have whenever its added to an object.
    private static String label = "Уровень казарм"; // Label that will be displayed when the towny object's status is shown.
    public static IntegerDataField Barracks = new IntegerDataField(keyname, defaultVal, label);

    @Override
    public void onLoad() {
        // Plugin startup logic
        try {
            TownyAPI.getInstance().registerCustomDataField(Barracks);
        } catch (KeyAlreadyRegisteredException e) {}
        getLogger().info("[FirstPlugin] is enabled!");
        getCommand("buyracks1").setExecutor(new buyracks1());
        BukkitTask taskracks = new racksTask(this).runTaskTimer(this,0L, 100L);
    }
    public static IntegerDataField getBarracks() {
        return Barracks;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[FirstPlugin] is disabled!");
    }
}
