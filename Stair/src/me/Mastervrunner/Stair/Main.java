package me.Mastervrunner.Stair;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Spigot;
import org.bukkit.block.BlockFace;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.spigotmc.event.entity.EntityDismountEvent;
import me.Mastervrunner.Stair.Commands.Stair;

public class Main  extends JavaPlugin implements Listener{

	public static Main instance;
	
	@Override
	public void onEnable(){
		RegisterCommands();
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public void onLoad(){
		instance = this;
		addStairs();
	}
	
	public void RegisterCommands() {
		this.getCommand("Stair").setExecutor(new Stair(this));
	}
	
	Slime as;
	
	ArrayList<Material> Stairslist = new ArrayList<Material>();
	
	public void addStairs() {
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


	/*
	Step 1: Detect on Right Click
	Step 2: Detect if Right Clicked on block in Stairslist.
	Step 3: Detect if sneaking and has no block in main hand
	
	Step 4: Create slime and set passenger to player
	Step 5: Add Slime to slime list with player name
	
	Step 6: If player right clicks a block, and there is a Slime in SlimeList, destroy original slime with the dismount event. Create slimeRider list so we can destroy any entity if that has the name of the person who was once riding it.
	Step 7: Create new slime, and set passenger to player
	
	
	 */
	
	ArrayList<Slime> slimeList = new ArrayList<Slime>();
	
	ArrayList<Player> slimeRiderList = new ArrayList<Player>();
	
	ArrayList<Player> canDismount = new ArrayList<Player>();

	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		
		for (int i = 0; i < slimeList.size(); i++) {
			if(slimeList.get(i).getCustomName() == e.getPlayer().getDisplayName()) {
				slimeList.get(i).remove();
			}
		}
		
	}
	
	//Step 1: Add all slimes to a list with the name Rider:+RiderName.getdisplayname"
	//Step 2: On player toggle sneak, get all slimes in the slimeList, and kill the ones with the name of Rider+e.getplayer.getdisplayname
	
		
	@EventHandler
    public void onVehicle(EntityDismountEvent e){
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
		
			for (int i = 0; i < slimeRiderList.size(); i++){
		   	 		if(slimeRiderList.get(i).getDisplayName() == e.getDismounted().getCustomName()) {
		   	 			e.getDismounted().remove();
		   	 			
			   	 		new BukkitRunnable(){
			  			  @Override
			  			  public void run(){
			  				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			  				double yloc = p.getLocation().getBlockY();
			  				yloc = yloc + 1;
			  				String command2 = "tp " + p.getName() + " " + p.getLocation().getX() + " " + yloc + " " + p.getLocation().getZ();

			  				Bukkit.dispatchCommand(console, command2);
			  				
			  				
			  			  }
			   	 		}.runTaskLater(this, 1);
		   	 			
		   	 			
		   	 			break;
					}
		   	}
			
			if(p.isSneaking() && e.getDismounted().getType() == EntityType.SLIME && e.getDismounted().getCustomName() != null) {
				new BukkitRunnable(){
					  @Override
					  public void run(){
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						double yloc = p.getLocation().getBlockY();
						yloc = yloc + 1;
						String command2 = "tp " + p.getName() + " " + p.getLocation().getX() + " " + yloc + " " + p.getLocation().getZ();

						Bukkit.dispatchCommand(console, command2);
						
						
					  }
		 	 		}.runTaskLater(this, 1);
			}
		
		}
	
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && Stairslist.contains(e.getClickedBlock().getType()) && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {

			for (int i = 0; i < slimeList.size(); i++){
	       	 		if(slimeList.get(i).getCustomName() == e.getPlayer().getCustomName()) {
	       	 			slimeList.get(i).removePassenger(e.getPlayer());
	    			}
	       	 }
			
			createSlime(e);
			
			new BukkitRunnable(){
    			  @Override
    			  public void run(){
    				  canDismount.add(e.getPlayer());
    			  }
    		}.runTaskLater(this, 20);
			
		}
	}
	
	public void createSlime(PlayerInteractEvent e) {
		double SitX = e.getClickedBlock().getLocation().getX();
		SitX = SitX + 0.5;
		double SitY = e.getClickedBlock().getLocation().getY();
		SitY = SitY - 0.5;
		double SitZ = e.getClickedBlock().getLocation().getZ();
		
		if(e.getBlockFace() == BlockFace.NORTH) {
			SitZ = SitZ - 1.5;
			
		}
	    
		if(e.getBlockFace() == BlockFace.SOUTH) {
			SitZ = SitZ + 0.2;
		}
		
		Location loc = new Location(e.getClickedBlock().getWorld(), e.getClickedBlock().getLocation().getX() + 0.5, e.getClickedBlock().getLocation().getY() - 0.25, e.getClickedBlock().getLocation().getZ() + 0.5);
		
		as = (Slime) e.getClickedBlock().getWorld().spawn(loc, Slime.class);
			
		as.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000*10, 1));
        as.setAI(false);
        as.setGravity(false);
        as.setInvulnerable(true);
          
        as.setSize(1);
        as.setCollidable(false);
         
        as.addPassenger(e.getPlayer());
          
        as.setCustomName("Rider:" +e.getPlayer().getDisplayName());
        
        slimeList.add(as);
		
	}
	 
}
