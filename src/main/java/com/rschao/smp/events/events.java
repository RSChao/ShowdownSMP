package com.rschao.smp.events;

import com.rschao.smp.Plugin;
import com.rschao.smp.items.Items;
import com.rschao.smp.lives.lifeAPI;
import com.rschao.smp.lives.saveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class events implements Listener {
   static Server server = Bukkit.getServer();
   static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
   NamespacedKey GemKey;
   NamespacedKey speedKey;
   NamespacedKey hgKey;
   NamespacedKey LPKey;
   NamespacedKey HasteKey;
   NamespacedKey SlowKey;
   NamespacedKey GPKey;
   NamespacedKey BSKey;
   NamespacedKey BOKey;
   NamespacedKey LifeKey;
   NamespacedKey HexKey;
   NamespacedKey HstKey;
   Logger log;
   Plugin plugin;
   private Map<UUID, Long> shootTimes = new HashMap<>();

   public events(Plugin plugin, NamespacedKey GemKey, NamespacedKey speedKey, NamespacedKey hgKey, NamespacedKey LPKey, NamespacedKey HasteKey, NamespacedKey SlowKey, NamespacedKey GPKey, NamespacedKey BSKey, NamespacedKey BOKey, Logger log, NamespacedKey LifeKey, NamespacedKey HexKey, NamespacedKey HstKey) {
      this.plugin = plugin;
      this.GemKey = GemKey;
      this.speedKey = speedKey;
      this.hgKey = hgKey;
      this.LPKey = LPKey;
      this.HasteKey = HasteKey;
      this.SlowKey = SlowKey;
      this.GPKey = GPKey;
      this.BSKey = BSKey;
      this.BOKey = BOKey;
      this.log = log;
      this.LifeKey = LifeKey;
      this.HexKey = HexKey;
      this.HstKey = HstKey;
   }

   @EventHandler
   public void OnUse(PlayerInteractEvent ev) {
      
      if (ev.getAction() == Action.RIGHT_CLICK_BLOCK || ev.getAction() == Action.RIGHT_CLICK_AIR) {
         if (ev.getItem() != null) {
            Player player;
            int newAmount;
            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.GemKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "attribute " + player.getName() + " max_health base set 40");
               player.sendMessage(ChatColor.GREEN + "Your health count has increased and is now at " + ChatColor.BOLD + ChatColor.DARK_RED + "20 hearts" + ChatColor.RESET + ChatColor.GREEN + "!");
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               player.getInventory().getItemInMainHand().setAmount(newAmount);
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.speedKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60*20, 2));
               player.sendMessage(ChatColor.GREEN + "You have received the power of " + ChatColor.BOLD + ChatColor.DARK_RED + "Master Swiftenes" + ChatColor.RESET + ChatColor.GREEN + "!");
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               player.getInventory().getItemInMainHand().setAmount(newAmount);
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.hgKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "attribute " + player.getName() + " max_health base set 30");
               player.sendMessage(ChatColor.GREEN + "Your health count has increased and is now at " + ChatColor.BOLD + ChatColor.DARK_RED + "15 hearts" + ChatColor.RESET + ChatColor.GREEN + "!");
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               player.getInventory().getItemInMainHand().setAmount(newAmount);
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.LPKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               for(Player p : Bukkit.getOnlinePlayers()){
                  if(p.getLocation().distance(player.getLocation()) <= 3){
                     p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300*20, 1));
                  }
               }
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.HasteKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               player.getInventory().getItemInMainHand().setAmount(newAmount);
               for(Player p : Bukkit.getOnlinePlayers()){
                  if(p.getLocation().distance(player.getLocation()) <= 3){
                     p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 300*20, 4));
                  }
               }
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.SlowKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               player.getInventory().getItemInMainHand().setAmount(newAmount);
               for(Player p : Bukkit.getOnlinePlayers()){
                  if(p.getLocation().distance(player.getLocation()) <= 3 && p.getLocation().distance(player.getLocation()) > 1){
                     p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 3*20, 255));
                  }
               }
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.GPKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               player.getInventory().getItemInMainHand().setAmount(newAmount);
               for(Player p : Bukkit.getOnlinePlayers()){
                  if(p.getLocation().distance(player.getLocation()) <= 3 && p.getLocation().distance(player.getLocation()) > 1){
                     p.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 3*20, 255));
                  }
               }
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.BOKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               for(Player p : Bukkit.getOnlinePlayers()){
                  if(p.getLocation().distance(ev.getPlayer().getLocation()) <= 3){
                     p.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 300*20, 5));
                  }
               }
               player.getInventory().getItemInMainHand().setAmount(newAmount);
            }

            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.LifeKey, PersistentDataType.BOOLEAN)) {
               player = ev.getPlayer();
               newAmount = setAmount(player, player.getInventory().getItemInMainHand().getAmount());
               
               if(saveData.getLives(player) >= 10){ 
                  player.sendMessage(ChatColor.LIGHT_PURPLE + "You cannot gain any more lives!");
                  return; 
               }
               lifeAPI.addLife(player);
               
               
               player.getInventory().getItemInMainHand().setAmount(newAmount);
               
               
            }
            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.HexKey, PersistentDataType.INTEGER)){
               player = ev.getPlayer();
               player.getInventory().getItemInMainHand().setAmount(1);
               if(player.isSneaking()){
                  //release

                  //step 0: Check the amulet's storage & player's health
                  float health = (float) player.getAttribute(Attribute.MAX_HEALTH).getValue();
                  int hearts = ev.getItem().getItemMeta().getPersistentDataContainer().get(HexKey, PersistentDataType.INTEGER);
                  boolean toomuchpowah = false;
                  if((health + (hearts*2)) > 60){
                     player.sendMessage(ChatColor.GOLD + "You have too much health", ChatColor.YELLOW + "The amulet shall grant you whatever your soul may bear");
                     toomuchpowah = true;
                  }
                  else{
                     toomuchpowah = false;
                  }
                  if(hearts == 0){
                     player.sendMessage(ChatColor.RED + "You do not have any hearts stored");
                     return;
                  }
                  //step 1: add the hearts
                  float NewHealth = health + (hearts*2);
                  if(toomuchpowah) NewHealth = 60;
                  Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "attribute " + player.getName() +" minecraft:max_health base set " + String.valueOf(NewHealth));
                  //step 2: reset the amulet
                  player.getInventory().getItemInMainHand().setAmount(0);
                  player.getInventory().addItem(Items.HeartExtractor);
               }
               else if(player.isSprinting()){
                  //10hearts
                  //step 0: Check hearts
                  float health = (float) player.getAttribute(Attribute.MAX_HEALTH).getValue();
                  int hearts = ev.getItem().getItemMeta().getPersistentDataContainer().get(HexKey, PersistentDataType.INTEGER);
                  if(health < 40){
                     player.sendMessage(ChatColor.RED + "You do not have enough health.", ChatColor.RED + "You need " + ChatColor.YELLOW + "20" + ChatColor.RED + "hearts to use this function");
                     return;
                  }
                  if((hearts+10) > 20) {
                     player.sendMessage(ChatColor.RED + "You have reached the maximun heart amount");
                     return;
                  }
                  //step 1: add item data
                  ItemMeta meta = ev.getItem().getItemMeta();
                  meta.addEnchant(Enchantment.CHANNELING, 1, true);
                  hearts += 10;
                  meta.getPersistentDataContainer().set(HexKey, PersistentDataType.INTEGER, hearts);
                  List<String> list = meta.getLore();
                  int size = list.size();
                  if(size >= 4){
                     list.set(4, "Hearts stored: " + Integer.toString(hearts));
                  }
                  else{
                     list.add("Hearts stored: " + Integer.toString(hearts));
                  }
                  meta.setLore(list);
                  ev.getItem().setItemMeta(meta);
                  //step 2: subtract hearts
                  Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "attribute " + player.getName() +" minecraft:max_health base set " + String.valueOf(health - 20));
                  player.sendMessage(ChatColor.DARK_GREEN + "You have " + ChatColor.YELLOW + hearts + ChatColor.DARK_GREEN + " hearts stored in the amulet, out of a maximun of" + ChatColor.YELLOW +" 20");
               }
               else{
                  //5hearts
                  //step 0: Check hearts
                  float health = (float) player.getAttribute(Attribute.MAX_HEALTH).getValue();
                  int hearts = ev.getItem().getItemMeta().getPersistentDataContainer().get(HexKey, PersistentDataType.INTEGER);
                  if(health < 26){
                     player.sendMessage(ChatColor.RED + "You do not have enough health.", ChatColor.RED + "You need " + ChatColor.YELLOW + "15" + ChatColor.RED + "hearts to use the amulet");
                     return;
                  }
                  if((hearts+5) > 20) {
                     player.sendMessage(ChatColor.RED + "You have reached the maximun heart amount");
                     return;
                  }
                  //step 1: add item data
                  ItemMeta meta = ev.getItem().getItemMeta();
                  meta.addEnchant(Enchantment.CHANNELING, 1, true);
                  hearts += 5;
                  meta.getPersistentDataContainer().set(HexKey, PersistentDataType.INTEGER, hearts);
                  List<String> list = meta.getLore();
                  int size = list.size();
                  if(size >= 4){
                     list.set(4, "Hearts stored: " + Integer.toString(hearts));
                  }
                  else{
                     list.add("Hearts stored: " + Integer.toString(hearts));
                  }
                  meta.setLore(list);
                  ev.getItem().setItemMeta(meta);
                  //step 2: subtract hearts
                  Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "attribute " + player.getName() +" minecraft:max_health base set " + String.valueOf(health - 10));
                  player.sendMessage(ChatColor.DARK_GREEN + "You have " + ChatColor.YELLOW + hearts + ChatColor.DARK_GREEN + " hearts stored in the amulet, out of a maximun of" + ChatColor.YELLOW +" 20");
               }
               
               
            }
            if(ev.getItem().getItemMeta().getPersistentDataContainer().has(Items.EmblemKey, PersistentDataType.BOOLEAN)){
               if(!ev.getPlayer().getInventory().getItemInOffHand().equals(ev.getItem())){
                  return;
               }
               Player p = ev.getPlayer();
               float health = (float) p.getHealth();
               ev.setCancelled(true);
               Long lastShot = this.shootTimes.get(p.getUniqueId());
               long seconds = System.currentTimeMillis()/1000L;
               if(lastShot != null && seconds-lastShot < 10){
                  p.sendMessage((10-(seconds-lastShot)) + "s");
                  return;
               }
               p.sendMessage("You called for help...");
               shootTimes.put(p.getUniqueId(), seconds);
               if((health <= 10f)){
                  Bukkit.getScheduler().runTaskLater(plugin, ()->{
                     p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 10*20, 0));
                     p.sendMessage(ChatColor.LIGHT_PURPLE + "And they listened! DEFENSE up!");
                     return;
                  }, 10);
               }
               else{
                  p.sendMessage(ChatColor.RED + "But nobody came...");
                  return;
               }

            }
         }

      }
      if(ev.getAction() == Action.LEFT_CLICK_AIR || ev.getAction() == Action.LEFT_CLICK_BLOCK){
         if(ev.getItem() != null){
            Player player = ev.getPlayer();
            if (ev.getItem().getItemMeta().getPersistentDataContainer().has(this.HexKey, PersistentDataType.INTEGER)){
               int hearts = ev.getItem().getItemMeta().getPersistentDataContainer().get(HexKey, PersistentDataType.INTEGER);
               player.sendMessage(ChatColor.DARK_GREEN + "You have " + ChatColor.YELLOW + hearts + ChatColor.DARK_GREEN + " hearts stored in the amulet, out of a maximun of" + ChatColor.YELLOW +" 20");
            }
         }
      }
   }

   @EventHandler
   public void OnDeath(PlayerDeathEvent e) {
      if (e.getEntity() instanceof Player && e.getEntity().isDead()) {
         Player p = e.getEntity();
         if(p == null) return;
         if(e.getEntity().getKiller() == null);
         ItemStack i = e.getEntity().getKiller().getInventory().getItemInMainHand();
         if(i == null) return;
         if (i.getItemMeta().getPersistentDataContainer().has(this.BSKey, PersistentDataType.BOOLEAN)) {
            if (i.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Sword of Judgement")) {
               server.dispatchCommand(console, "ban " + p.getName());
            } else {
               server.dispatchCommand(console, "essentials:tempban " + p.getName() + " " + i.getItemMeta().getDisplayName());
            }
         }
         else return;
      }
   }

   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent ev){
      Player vPlayer = ev.getEntity();
      Player p = ev.getEntity().getKiller();
      if(p == null) return;
      if (saveData.getLives(p) >= 10){
         vPlayer.getWorld().dropItemNaturally(vPlayer.getLocation(), Items.LifeUpGem);
         p.sendMessage(ChatColor.LIGHT_PURPLE + "A Feather of Reincarnation has been dropped!");
         vPlayer.sendMessage(ChatColor.DARK_RED + "Your life essence was turned into a feather of reincarnation!");
      }
      if(saveData.getLives(vPlayer) <= 0){
         p.kickPlayer(ChatColor.RED + "You have been " + ChatColor.DARK_RED + "Death Banned. " + ChatColor.RED + "Wait for a friend to use a revive beacon on you.");
      }
      
   }
   public int setAmount(Player p, int amount){
      int x = 0;
      if(p.getGameMode().equals(GameMode.CREATIVE)){
         x = amount;
      }
      else{
         x = p.getInventory().getItemInMainHand().getAmount() - 1;
      }
      return x;
   }

   @EventHandler
   void playerDamage(EntityDamageEvent ev){
      if(ev.getEntity() instanceof Player){
         Player p = (Player) ev.getEntity();
         if(ev.getDamage() < p.getHealth()) return;
         int odds = 0;
         //check if player has permission totem.nolimit. If so, make a variable odds = 30. If not, make it 70
         if(p.hasPermission("totem.nolimit")){
            odds = 60;
         }
         else{
            odds = 70;
         }
         
         if(p.getInventory().getItemInMainHand().hasItemMeta()){
            if(p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Plugin.getPlugin(Plugin.class), "emblem"))){
               Random random = new Random();
               int i = random.nextInt(100);
               double dmg = ev.getDamage();
               if(i<odds && (dmg < 400 || p.hasPermission("totem.nolimit"))){
                  ev.setCancelled(true);
                  p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 3));
                  p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));
                  p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 800, 1));
                  p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 25, 1);
                  p.setHealth(1);
                  
               }
            }
         }
         
         if(p.getInventory().getItemInOffHand().hasItemMeta()){
            if(p.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Plugin.getPlugin(Plugin.class), "emblem"))){
               Random random = new Random();
               int i = random.nextInt(100);
               double dmg = ev.getDamage();
               if(p.hasPermission("gaster.debug")){
                  p.sendMessage("debug: " + i);
                  p.sendMessage("Debug: " + odds);
               }
               if(p.getKiller().hasPermission("gaster.debug")){
                  p.getKiller().sendMessage("debug: " + i);
                  p.getKiller().sendMessage("Debug: " + odds);
               }
               if(i<odds && (dmg < 400 || p.hasPermission("totem.nolimit"))){
                  ev.setCancelled(true);
                  p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 3));
                  p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));
                  p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 800, 1));
                  p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE, 25, 1);
                  p.setHealth(1);
               }
            }
         }
      }
   }

   @EventHandler
   void lootGen(EntityDeathEvent e) {
      if(e.getEntity() instanceof Zombie){
         //get a random number between 1 and 100
         Random random = new Random();
         int i = random.nextInt(100);
         if(i==0){
            Zombie z = (Zombie) e.getEntity();
            z.getWorld().dropItemNaturally(z.getLocation(), new ItemStack(Items.MagicStone));
         }
      }
   }
}
