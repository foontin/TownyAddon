package me.foont.firstplugin.tasks;

import me.foont.firstplugin.FirstPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;

import java.util.List;
import java.util.stream.Collectors;

import static me.foont.firstplugin.FirstPlugin.Barracks;

public class racksTask extends BukkitRunnable {

    FirstPlugin plugin;

    public racksTask(FirstPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){
        List<Town> townList = TownyAPI.getInstance().getDataSource().getTowns();
        List<Town> townsWithMetaData = townList.stream().filter(town -> town.hasMeta(Barracks.getKey())).collect(Collectors.toList());
        List<Town> townsWithMetaDataEqualsOne = townsWithMetaData.stream().filter(town -> town.getMetadata(Barracks.getKey()).getValue().equals(1)).collect(Collectors.toList());
        for (Town t : townsWithMetaDataEqualsOne){
            for (Resident res : t.getResidents()){
                res.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6, 1));
            }
        }
    }

}

