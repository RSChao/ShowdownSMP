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
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Heartslmao extends BaseAdvancement implements ParentGrantedVisibility {
   public static AdvancementKey KEY;

   static {
      KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "heartslmao");
   }

   public Heartslmao(Advancement parent, float x, float y) {
      super(KEY.getKey(), new AdvancementDisplay(Material.NETHER_STAR, "§c§lAgainst the numbers", AdvancementFrameType.CHALLENGE, true, true, x, y, new String[]{"§5Go against the numbers and beat a", "player with 15 or more hearts to", "their name. Bro, you're goated!", "", "", "§6Reward: +5 Hearts gem"}), parent, 1);
      this.registerEvent(PlayerDeathEvent.class, (ev) -> {
         Player p = ev.getEntity();
         if ((p.isDead() && p.getKiller() instanceof Player) && p.getAttribute(Attribute.MAX_HEALTH).getValue() >= 30) {
            this.incrementProgression(p.getKiller());
         }

      });
   }

   public void giveReward(Player player) {
      ItemStack reward = Items.smallhgem;
      player.getInventory().addItem(reward);
   }
}