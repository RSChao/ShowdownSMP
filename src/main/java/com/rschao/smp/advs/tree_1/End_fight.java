package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class End_fight extends BaseAdvancement implements ParentGrantedVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "end_fight");


  public End_fight(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.DRAGON_HEAD, "§dEnd Fighter", AdvancementFrameType.GOAL, true, true, x, y , "Fight against the §5Showdown §6SMP", "in the End", "", "§6Reward:", "§6- [END FIGHTER] rank"), parent, 1);
    registerEvent(EntityDamageByEntityEvent.class, (ev) ->{
      if(!(ev.getEntity() instanceof Player)) return;
      if(!(ev.getDamager() instanceof Player)) return;
      Player p1 = (Player) ev.getEntity();
      Player p2 = (Player) ev.getDamager();
      Location damagedLocation = p1.getLocation();
      World world = damagedLocation.getWorld();
      if (world != null && world.getName().equals("Showdown_the_end")) {
        incrementProgression(p2);
        incrementProgression(p1);
      }
    });
  }
  @Override
  public void giveReward(Player player) {
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add fight");
  }
}