package br.kits;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import br.kits.abilidades.Kangaroo;

public class Fight extends JavaPlugin{

	 public ArrayList<String> oneKit = new ArrayList<String>();
	 public ArrayList<String> kangaroo = new ArrayList<String>();

	 public void onEnable() {
		 registerEvents();
	 }
	 org.bukkit.plugin.PluginManager pm = Bukkit.getServer().getPluginManager();
	 public void registerEvents() {
		 pm.registerEvents(new Kangaroo(this), this);
	 }
	public void onDisable(){
		  HandlerList.unregisterAll((Plugin)this);
	  }
}
