package com.rschao.smp.advs.tab2;

import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.rschao.smp.Plugin;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.logs.log;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.multiParents.MultiParentsAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;

import java.util.UUID;

public class Diamond23 extends MultiParentsAdvancement  {

  public static AdvancementKey KEY = new AdvancementKey(AdvancementTabNamespaces.tab2_NAMESPACE, "diamond23");


  public Diamond23(float x, float y,  BaseAdvancement... parents) {
    super(KEY.getKey(), new AdvancementDisplay(Material.DIAMOND, "§5§lShowDown §6Master", AdvancementFrameType.CHALLENGE, true, true, x,y , "Become the master of the Showdown SMP", "", "§6Reward:", "§6- +2 Enderchest pages (total 5)"),1, parents );
    this.registerEvent(PlayerInteractEvent.class, (e) -> {
      Player player = e.getPlayer();
      NamespacedKey key = new NamespacedKey(Plugin.getPlugin(Plugin.class), "lives");
      NamespacedKey HeartsKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "hearts");

      boolean hearts = player.getPersistentDataContainer().has(key, PersistentDataType.BOOLEAN);
      boolean lives = player.getPersistentDataContainer().has(HeartsKey, PersistentDataType.BOOLEAN);
      if(hearts && lives){
        incrementProgression(player);
      }
    });
  }

  @Override
  public void giveReward(Player player) {
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add ssmp-master");
    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "playsound ui.toast.challenge_complete master " + player.getName());
    //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ecvpages " + player.getName() + " set 5");

    log logs = new log(player.getName() + "has upgraded their page to 5", "enderchest_increase.log");
  }
}