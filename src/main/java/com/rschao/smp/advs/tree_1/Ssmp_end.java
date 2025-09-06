package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import com.rschao.smp.logs.log;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

import java.util.UUID;

public class Ssmp_end extends BaseAdvancement implements ParentGrantedVisibility {
  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "ssmp_end");
  static ItemStack icon = new ItemStack(Material.NETHER_STAR);
  static{
    ItemMeta meta = icon.getItemMeta();
    meta.setItemModel(NamespacedKey.minecraft("emblem"));
    icon.setItemMeta(meta);
  }

  public Ssmp_end(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(icon, "Showdown Survivor", AdvancementFrameType.CHALLENGE, true, true, x, y , "Survive the Showdown SMP and earn ", "the ultimate treasure.", "", "§6Rewards:", "§6- Victory Emblem", "§6- [Showdown Survivor], the top rank", "§6- +3 Enderchest pages (total 8)"), parent, 1);
  }
  @Override
  public void giveReward(Player player) {
    player.getInventory().addItem(Items.Emblem);
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add smp-god");


    log logs = new log(player.getName() + "has upgraded their page to 8", "enderchest_increase.log");

  }
}