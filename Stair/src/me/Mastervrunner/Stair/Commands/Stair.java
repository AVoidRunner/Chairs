package me.Mastervrunner.Stair.Commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Mastervrunner.Stair.Main;
import net.minecraft.server.v1_15_R1.World;

public class Stair implements CommandExecutor{

	static Main plugin;
	public Stair(Main main) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
			if (label.equalsIgnoreCase("Stair")) {
				if(sender.hasPermission("Stair.use")) {
					if(args.length == 0) {
						if(sender instanceof Player) {
							Player p = (Player) sender;
							p.setHealth(20);
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 5));
							sender.sendMessage(ChatColor.DARK_RED + "Since I can't use melons to do this anymore, I made this command - MasterV");
						} else sender.sendMessage(ChatColor.DARK_RED + "Why would you need to use this command? You're not a player. I know this command gives speed, but it won't make the server run faster, sorry!");
						return true;
					}
					if(args.length == 1) {
						if(Bukkit.getPlayerExact(args[0]) != null) {
							Player target = Bukkit.getPlayer(args[0]);
							target.setHealth(20);
							target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 5));
							target.sendMessage(ChatColor.DARK_RED + "Since I can't use melons to do this anymore, I made this command - MasterV ( /superheal )");
							sender.sendMessage(ChatColor.DARK_RED + "You healed and gave speed to " + target.getName());
						} else sender.sendMessage(ChatColor.DARK_RED + "Player not found");
					}
					if(args.length == 2) {
						if(Bukkit.getPlayerExact(args[0]) != null) {
							Player target = Bukkit.getPlayer(args[0]);
							target.setHealth(20);
							target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 10, Integer.parseInt(args[1])));
							target.sendMessage(ChatColor.DARK_RED + "Since I can't use melons to do this anymore, I made this command - MasterV ( /superheal )");
							sender.sendMessage(ChatColor.DARK_RED + "You healed and gave speed to " + target.getName());
						} else sender.sendMessage(ChatColor.DARK_RED + "Player not found");
					}
				} else sender.sendMessage(ChatColor.DARK_RED + "You need permission to use this");
				return true;
			}
			
			return false;
		}
	
	ArrayList<Material> Stairslist = new ArrayList<Material>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
	    if (e.getAction() == Action.LEFT_CLICK_BLOCK){
	    	if(1 == 1){
	    		Stairslist.add(Material.OAK_STAIRS);
	    		Stairslist.add(Material.SPRUCE_STAIRS);
	    		Stairslist.add(Material.BIRCH_STAIRS);
	    		Stairslist.add(Material.JUNGLE_STAIRS);
	    		Stairslist.add(Material.ACACIA_STAIRS);
	    		Stairslist.add(Material.DARK_OAK_STAIRS);
	    		Stairslist.add(Material.STONE_STAIRS);
	    		Stairslist.add(Material.COBBLESTONE_STAIRS);
	    		Stairslist.add(Material.MOSSY_COBBLESTONE_STAIRS);
	    		Stairslist.add(Material.STONE_BRICK_STAIRS);
	    		Stairslist.add(Material.MOSSY_STONE_BRICK_STAIRS);
	    		Stairslist.add(Material.ANDESITE_STAIRS);
	    		Stairslist.add(Material.POLISHED_ANDESITE_STAIRS);
	    		Stairslist.add(Material.DIORITE_STAIRS);
	    		Stairslist.add(Material.POLISHED_DIORITE_STAIRS);
	    		Stairslist.add(Material.GRANITE_STAIRS);
	    		Stairslist.add(Material.POLISHED_GRANITE_STAIRS);
	    		Stairslist.add(Material.SANDSTONE_STAIRS);
	    		Stairslist.add(Material.SMOOTH_SANDSTONE_STAIRS);
	    		Stairslist.add(Material.RED_SANDSTONE_STAIRS);
	    		Stairslist.add(Material.SMOOTH_RED_SANDSTONE_STAIRS);
	    		Stairslist.add(Material.BRICK_STAIRS);
	    		Stairslist.add(Material.PRISMARINE_STAIRS);
	    		Stairslist.add(Material.PRISMARINE_BRICK_STAIRS);
	    		Stairslist.add(Material.DARK_PRISMARINE_STAIRS);
	    		Stairslist.add(Material.NETHER_BRICK_STAIRS);
	    		Stairslist.add(Material.RED_NETHER_BRICK_STAIRS);
	    		Stairslist.add(Material.QUARTZ_STAIRS);
	    		Stairslist.add(Material.SMOOTH_QUARTZ_STAIRS);
	    		Stairslist.add(Material.PURPUR_STAIRS);
	    		Stairslist.add(Material.END_STONE_BRICK_STAIRS);
	    	}
	    	Material clickblock = e.getClickedBlock().getType();

	    	if(Stairslist.contains(clickblock)) {
	            // if the material from the block is a Stair do something here
	    		double SitX = e.getClickedBlock().getLocation().getX();
	    		double SitY = e.getClickedBlock().getLocation().getY();
	    		SitY = SitY - 1;
	    		double SitZ = e.getClickedBlock().getLocation().getZ();
	    	       
	    		Location loc = new Location(e.getClickedBlock().getWorld(), SitX, SitY, SitZ);
	    		
	            ArmorStand as = (ArmorStand) e.getClickedBlock().getWorld().spawn(loc, ArmorStand.class);
	            as.setVisible(false);

	            as.addPassenger(e.getPlayer());
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				String command = "say it worked";
				Bukkit.dispatchCommand(console, command);
				if(e.getPlayer().isSneaking()) {
					as.remove();
				}
	        }
	    }
	}
}
