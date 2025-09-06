package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.visibilities.VanillaVisibility;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class Lol extends BaseAdvancement implements VanillaVisibility {
   public static AdvancementKey KEY;

   static {
      KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "lol");
   }

   public Lol(Advancement parent, float x, float y) {
      super(KEY.getKey(), new AdvancementDisplay(Material.SHULKER_BOX, "§aMadlad", AdvancementFrameType.TASK, true, true, x, y, new String[]{"get a shulker. with no end. sounds fun lol"}), parent, 1);
      this.registerEvent(CraftItemEvent.class, (e) -> {
         ItemStack i = new ItemStack(Material.SHULKER_SHELL, 2);
         if (e.getRecipe().getResult().equals(i)) {
            this.incrementProgression((Player)e.getWhoClicked());
         }

      });
   }
}