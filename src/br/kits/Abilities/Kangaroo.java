package br.kits.Abilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import br.kits.Fight;

public class Kangaroo implements Listener{

	   public static Fight plugin;
	   public Kangaroo(Fight kitpvp){
	   plugin = kitpvp;
		}
	   public ArrayList<String> kangaroodj = new ArrayList<String>();
	   ArrayList<Player> kanga = new ArrayList<Player>();


	 @SuppressWarnings("deprecation")
	 @EventHandler
	   public void onPlayerKangaroo(PlayerInteractEvent event) {
	     final Player p = event.getPlayer();
	     if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_AIR) || (event.getAction() != Action.LEFT_CLICK_BLOCK)) && 
	       (plugin.kangaroo.contains(p.getName())) && (p.getItemInHand().getType() == Material.FIREWORK)) {
	       event.setCancelled(true);
	       if (p.isOnGround()) {
	         if (!p.isSneaking()) {
	           Vector vector = p.getEyeLocation().getDirection();
	           vector.multiply(0.28F);
	           vector.setY(0.9F);
	           p.setVelocity(vector);
	           if (this.kangaroodj.contains(p.getName()))
	             Bukkit.getServer()
	               .getScheduler()
	               .scheduleSyncDelayedTask(plugin, 
	               new Runnable() {
	               public void run() {
	                 kangaroodj.remove(p.getName());
	               } } );
	         }
	         else if (p.isSneaking()) {
	           Vector vector = p.getEyeLocation().getDirection();
	           vector.multiply(0.28F);
	           vector.setY(0.65F);
	           p.setVelocity(vector);
	           if (this.kangaroodj.contains(p.getName()))
	             Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, 
	               new Runnable() {
	               public void run() {
	                kangaroodj.remove(p.getName());
	               }
	             });
	         }
	       }
	       else if (!this.kangaroodj.contains(p.getName()))
	         if (!p.isSneaking()) {
	           Vector vector = p.getEyeLocation().getDirection();
	           vector.multiply(0.28F);
	           vector.setY(0.85F);
	           p.setVelocity(vector);
	           this.kangaroodj.add(p.getName());
	         } else if (p.isSneaking()) {
	           Vector vector = p.getEyeLocation().getDirection();
	           vector.multiply(1.5F);
	           vector.setY(0.65F);
	           p.setVelocity(vector);
	           this.kangaroodj.add(p.getName());
	         }
	     }
	   }

	   @EventHandler
	   public void onMove(PlayerMoveEvent event)
	   {
	     Player p = event.getPlayer();
	     if (this.kanga.contains(p)) {
	       Block b = p.getLocation().getBlock();
	       if ((b.getType() != Material.AIR) || 
	         (b.getRelative(BlockFace.DOWN).getType() != Material.AIR))
	         this.kanga.remove(p);
	     }
	   }

	   @EventHandler
	   public void onDamage(EntityDamageEvent event){
	     Entity e = event.getEntity();
	     if ((e instanceof Player)) {
	       Player player = (Player)e;
	       if (((event.getEntity() instanceof Player)) && 
	         (event.getCause() == EntityDamageEvent.DamageCause.FALL) && (player.getInventory().contains(Material.FIREWORK)) && (event.getDamage() >= 7.0D))
	         event.setDamage(7.0D);
	     }
	   }

	   @EventHandler(priority=EventPriority.NORMAL)
	   public void onPlayerMove(PlayerMoveEvent event) {
	     Player p = event.getPlayer();
	     Block b = p.getLocation().getBlock();
	     if (((b.getType() != Material.AIR) || 
	       (b.getRelative(BlockFace.DOWN).getType() != Material.AIR)) && 
	       (this.kangaroodj.contains(p.getName())))
	       this.kangaroodj.remove(p.getName());
	   }   
}
