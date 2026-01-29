package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Kill3_20 extends BaseAdvancement implements HiddenVisibility {
   public static AdvancementKey KEY;

   static {
      KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "kill3_20");
   }

   public Kill3_20(Advancement parent, float x, float y) {
      super(KEY.getKey(), new AdvancementDisplay(Material.DIAMOND_SWORD, "§6The immortal player", AdvancementFrameType.CHALLENGE, true, true, x, y, new String[]{"Get your hands dirty one last time", "and beat 3 players with 20 hearts each.", "Dying is not an option", "", "§6Reward:", "§6- Bedrock x5", "§6- Purified Speed Fragment x4"}), parent, 3);
      this.registerEvent(PlayerDeathEvent.class, (ev) -> {
         Player p = ev.getEntity();
         if (p.isDead() && p.getKiller() instanceof Player) {
            if (p.getAttribute(Attribute.MAX_HEALTH).getValue() >= 40.0D) {
               this.incrementProgression(p.getKiller());
            }

            if(!p.getAdvancementProgress((org.bukkit.advancement.Advancement) this).isDone()){
               this.setProgression(p, 0);
            }
         }

      });
   }

   public void giveReward(Player player) {
      ItemStack reward1 = new ItemStack(Material.BEDROCK, 5);
      player.getInventory().addItem(reward1);
      ItemStack reward2 = Items.PureSF;
      reward2.setAmount(4);
      player.getInventory().addItem(reward2);
      Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
   }
}
    