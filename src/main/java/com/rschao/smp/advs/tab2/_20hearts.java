package com.rschao.smp.advs.tab2;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class _20hearts extends BaseAdvancement implements ParentGrantedVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "20hearts");
static ItemStack icon = new ItemStack(Material.NETHER_STAR);
  static{
    ItemMeta meta = icon.getItemMeta();
    meta.setItemModel(NamespacedKey.minecraft("gem_twenty"));
    icon.setItemMeta(meta);
  }

  public _20hearts(Advancement parent, float x, float y) {
    
    super(KEY.getKey(), new AdvancementDisplay(icon, "§4§lMaster of Hearts", AdvancementFrameType.CHALLENGE, true, true, x, y , "Obtain a gem of health and absorb it's power", "", "§6Rewards:", "§6-4xStolen Shard", "§6-1xGem of Health"), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
       Player player = ev.getPlayer();
       if(ev.getItem() == null) return;
      ItemStack stack = ev.getItem();
      if (stack != null) {
         if (player.getAttribute(Attribute.MAX_HEALTH).getBaseValue() >= 40D) {
            this.incrementProgression(player);
           }

        }
      });
   }

  public void giveReward(Player player) {
  
    Bukkit.getScheduler().runTaskLater(Plugin.getPlugin(Plugin.class), () -> {
      player.getInventory().addItem(Items.HealthGem);
    }, 4);
    player.getInventory().addItem(Items.BadOmen, Items.BadOmen, Items.BadOmen, Items.BadOmen);
    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
  }
}
