package com.rschao.smp.advs.tab2;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;

public class Maxhearts extends BaseAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "maxhearts");
static ItemStack icon = new ItemStack(Material.NETHER_STAR);
  static{
    icon = Items.HeartExtractor;
  }

  public Maxhearts(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(icon, "§b§lEminence of Hearts", AdvancementFrameType.CHALLENGE, true, true, x, y , "Use a sacred relic to obtain 30 hearts.", "", "§6Rewards:", "§62xMagic Stone", "§63xLife Feather", "§6[Heart Eminence] rank"), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
      Player player = ev.getPlayer();
      ItemStack stack = ev.getItem();
      if (stack != null) {
         if (player.getAttribute(Attribute.MAX_HEALTH).getBaseValue() == 60D) {
            this.incrementProgression(player);
           }

        }
     });
  }

public void giveReward(Player player) {
  player.getInventory().addItem(Items.MagicStone, Items.MagicStone);
  player.getInventory().addItem(Items.LifeUpGem, Items.LifeUpGem, Items.LifeUpGem);
  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add 30-hearts");
  Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
  NamespacedKey key = new NamespacedKey(Plugin.getPlugin(Plugin.class), "hearts");
  player.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
  }
}
