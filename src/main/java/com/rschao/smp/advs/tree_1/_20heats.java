package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.HiddenVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import org.bukkit.Material;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.rschao.smp.Plugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class _20heats extends BaseAdvancement implements HiddenVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "20heats");


  public _20heats(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.BEACON, "§4§lMaster of Hearts", AdvancementFrameType.CHALLENGE, true, true, x, y, new String[]{"Get to the max amount of hearts"}), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
       NamespacedKey GemKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "HealthGem");
       Player player = ev.getPlayer();
       ItemStack stack = ev.getItem();
       if (stack != null) {
          if (stack.getItemMeta().getPersistentDataContainer().has(GemKey, PersistentDataType.BOOLEAN)) {
             this.incrementProgression(player);
            }

         }
      });
   }

 public void giveReward(Player player) {
   player.getInventory().addItem(Items.MagicStone);
   Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
   Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "execute as" + player.getName() + "run tellraw @a " + ChatColor.GREEN + player.getName() + " has reached " + ChatColor.DARK_PURPLE + "20 hearts" + ChatColor.GREEN + "!");
   
   }
}
  