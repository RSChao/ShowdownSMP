package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Shieldadv extends BaseAdvancement implements ParentGrantedVisibility {
   public static AdvancementKey KEY;

   static {
      KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "shieldadv");
   }

   public Shieldadv(Advancement parent, float x, float y) {
      super(KEY.getKey(), new AdvancementDisplay(Material.SHIELD, "Shield Master", AdvancementFrameType.GOAL, true, true, x, y, new String[]{"§5Break a shield. Like, break it a lot.", "", "§6Reward: Unbreakable Shield"}), parent, 300);
      this.registerEvent(PlayerItemDamageEvent.class, (e) -> {
         Player p = e.getPlayer();
         if (e.getItem().getType().equals(Material.SHIELD)) {
            this.incrementProgression(p);
         }

      });
   }

   public void giveReward(Player player) {
      ItemStack reward = Items.Shield;
      player.getInventory().addItem(new ItemStack[]{reward});
   }
}