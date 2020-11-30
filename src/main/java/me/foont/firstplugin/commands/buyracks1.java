package me.foont.firstplugin.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import com.palmergames.bukkit.towny.object.metadata.IntegerDataField;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.foont.firstplugin.FirstPlugin.Barracks;

public class buyracks1 implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            Resident resident = null;
            try {
                resident = TownyAPI.getInstance().getDataSource().getResident(p.getName());
            } catch (NotRegisteredException e) {
                e.printStackTrace();
            }
            if (resident.hasTown()) {
                Town town = null;
                try {
                    town = TownyAPI.getInstance().getDataSource().getTown(String.valueOf(resident.getTown()));
                } catch (NotRegisteredException e) {
                    e.printStackTrace();
                }
                if(!town.hasMeta(Barracks.getKey())) {
                    town.addMetaData(Barracks.clone());
                    CustomDataField rracks = town.getMetadata(Barracks.getKey());
                    if (rracks instanceof IntegerDataField){
                        IntegerDataField idf = (IntegerDataField) rracks;
                        idf.setValue(1);
                    }
                }
            } else {
                p.sendMessage("You must be a resident");
            }
        }
        return true;
    }
}