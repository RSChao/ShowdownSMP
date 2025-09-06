package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Goated extends BaseAdvancement implements ParentGrantedVisibility {
   public static AdvancementKey KEY;

   static {
      KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "goated");
   }

   public Goated(Advancement parent, float x, float y) {
      super(KEY.getKey(), new AdvancementDisplay(Material.END_CRYSTAL, "§4§lThe Unthinkable", AdvancementFrameType.CHALLENGE, true, true, x, y, new String[]{"Kill two player with full 20 hearts, without", "dying once. Holy shit bro, you are one", "insane player!", "", "§6Reward:", "§6- A full OP kit (Prot 5 + Sharp 6)", "§6- A Command block (memento)"}), parent, 2);
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
      Plugin plugin = (Plugin)Plugin.getPlugin(Plugin.class);
      ItemStack reward2;
      if (plugin.getConfig().getBoolean("items.OPKit")) {
         reward2 = Items.OPSword;
         player.getInventory().addItem(reward2);
         reward2 = Items.OPHelm;
         player.getInventory().addItem(reward2);
         reward2 = Items.OPChest;
         player.getInventory().addItem(reward2);
         reward2 = Items.OPLeggs;
         player.getInventory().addItem(reward2);
         reward2 = Items.OPBoots;
         player.getInventory().addItem(reward2);
      }

      reward2 = new ItemStack(Material.COMMAND_BLOCK);
      player.getInventory().addItem(reward2);
      Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
   }
}