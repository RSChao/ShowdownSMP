package com.rschao.smp.advs.tab2;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.VanillaVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
public class _15hearts extends BaseAdvancement implements VanillaVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "15hearts");
static ItemStack icon = new ItemStack(Material.NETHER_STAR);
  static{
    icon = Items.smallhgem;
  }

  public _15hearts(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(icon, "§aHeart Beginner", AdvancementFrameType.TASK, true, true, x, y , "You just started your journey towards", "infinite power. Keep going!", "", "§6Rewards:", "§6-4xSpeed frag", "§6-2xNetherite ingot"), parent, 1);
    this.registerEvent(PlayerInteractEvent.class, (ev) -> {
      Player player = ev.getPlayer();
      if(ev.getItem() == null) return;
      ItemStack stack = ev.getItem();
      if (stack != null) {
         if (player.getAttribute(Attribute.MAX_HEALTH).getBaseValue() == 30D) {
            this.incrementProgression(player);
           }

        }
     });
  }

public void giveReward(Player player) {
  player.getInventory().addItem(Items.SpeedFragment, Items.SpeedFragment);
  player.getInventory().addItem(Items.SpeedFragment, Items.SpeedFragment);
  ItemStack rItemStack = new ItemStack(Material.NETHERITE_INGOT, 2);
  player.getInventory().addItem(rItemStack);
  }
}
