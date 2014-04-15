package br.kits.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Dia implements CommandExecutor{
	
	  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
		    if (!(sender instanceof Player)) {
		        sender.sendMessage(ChatColor.RED + "Voce nao e um jogador!");
		        return false;
		      }
	    final Player player = (Player)sender;
	    if (commandLabel.equalsIgnoreCase("day") && player.hasPermission("fight.staff")) {
	    	player.getWorld().setTime(0L);
	    }
		return false;
	  }
}
