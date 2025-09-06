package com.rschao.smp.advs.tree_1;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.items.Items;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.visibilities.ParentGrantedVisibility;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
@SuppressWarnings("deprecation")
public class Dragon_egg extends BaseAdvancement implements ParentGrantedVisibility {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tree_1_NAMESPACE, "dragon_egg");
  

  public Dragon_egg(Advancement parent, float x, float y) {
    super(KEY.getKey(), new AdvancementDisplay(Material.DRAGON_EGG, "Dragon Egg Holder", AdvancementFrameType.CHALLENGE, true, true, x, y , "Obtain the Second Dragon Egg", "", "§6Rewards:", "§6- 10xFeather of Reincarnation", "§6- [DRAGON RULER] rank"), parent, 1);
    registerEvent(PlayerPickupItemEvent.class, (ev) -> {
        ItemStack item = ev.getItem().getItemStack();
      if(item.getType().equals(Material.DRAGON_EGG)){
        Player p = ev.getPlayer();
        incrementProgression(p);
      }
    });
  }
  @Override
  public void giveReward(Player player) {
    if(Plugin.getPlugin(Plugin.class).getConfig().getBoolean("advs.dragon_egg")){
      Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add egg");
      ItemStack stack = new ItemStack(Items.LifeUpGem);
      stack.setAmount(10);
      player.getInventory().addItem(stack);
    }
  }
}