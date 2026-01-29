package com.rschao.smp.advs.tab2;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Feather19 extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "feather19");


  public Feather19(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.BEACON, "§dLifetime kindness", AdvancementFrameType.GOAL, true, true, x, y , "Revive another player"), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
      NamespacedKey BeaconKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "beakon");
      Player player = ev.getPlayer();
      ItemStack stack = ev.getItem();
      if (stack != null) {
        if (stack.getItemMeta().getPersistentDataContainer().has(BeaconKey, PersistentDataType.BOOLEAN)) {
          incrementProgression(player);
        }
      }
    });
  }
}
