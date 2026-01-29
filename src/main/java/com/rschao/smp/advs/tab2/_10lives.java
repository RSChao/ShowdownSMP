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

public class _10lives extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "10lives");


  public _10lives(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.FEATHER, "§dFull of life", AdvancementFrameType.TASK, true, true, x, y , "Absorb a Life Feather"), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
      NamespacedKey LifeKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "Lives");
      if(ev.getItem() == null) return;
      Player player = ev.getPlayer();
      ItemStack stack = ev.getItem();
      if (stack != null) {
        if (stack.getItemMeta().getPersistentDataContainer().has(LifeKey, PersistentDataType.BOOLEAN)) {
          incrementProgression(player);
        }
      }
    });
  }
}
