package com.rschao.smp.advs.tab2;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import com.rschao.smp.lives.saveData;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Maxlives extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "maxlives");
  static ItemStack icon = new ItemStack(Material.NETHER_STAR);
  static{
    icon = Items.LifeUpGem;
  }

  public Maxlives(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.FEATHER, "§dLive to the absolute fullest", AdvancementFrameType.CHALLENGE, true, true, x, y , "Reach 10 lives, the absolute maximum."), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
      NamespacedKey LifeKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "Lives");
      Player player = ev.getPlayer();
      ItemStack stack = ev.getItem();
      if (stack != null) {
        if (stack.getItemMeta().getPersistentDataContainer().has(LifeKey, PersistentDataType.BOOLEAN)) {
          if(stack.getItemMeta().getDisplayName().compareToIgnoreCase(Items.LifeUpGem.getItemMeta().getDisplayName()) == 0){
            Bukkit.getScheduler().runTaskLater(Plugin.getPlugin(Plugin.class), () -> {
              if(saveData.getLives(player) == 10){
                incrementProgression(player);
              }
            }, 2);
          }       
        }
      }
    });
  }
  @Override
  public void giveReward(Player player) {
      NamespacedKey key = new NamespacedKey(Plugin.getPlugin(Plugin.class), "lives");
      player.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
      Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
  }
}