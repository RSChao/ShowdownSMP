package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Pk extends BaseAdvancement {
   public static AdvancementKey KEY;

   static {
      KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "pk");
   }

   public Pk(Advancement parent, float x, float y) {
      super(KEY.getKey(), new AdvancementDisplay(Material.NETHERITE_SWORD, "§4Player Killer", AdvancementFrameType.GOAL, true, true, x, y, new String[]{"§cMurder someone with your own hands"}), parent, 1);
      this.registerEvent(PlayerDeathEvent.class, (ev) -> {
         Player p = ev.getEntity();
         if (p.isDead() && p.getKiller() instanceof Player) {
            this.incrementProgression(p.getKiller());
         }

      });
   }
}