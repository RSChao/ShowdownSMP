package com.rschao.smp.enchants.sword;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Calendar;

public class BanEnchant extends EasyEnchant {

    public BanEnchant() {
        super("ban");
        CustomEnchantment enchant = makeEnchantment(ChatColor.BOLD + "Ban");
        enchant.setSupportedItem("#minecraft:enchantable/sharp_weapon");
        enchant.setMaxLevel(1);
        saveBukkitEnchantment(enchant);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        Player killer = ev.getEntity().getKiller();
        if(killer == null) return;
        Player deceased = ev.getEntity();
        ItemStack weapon = killer.getInventory().getItemInMainHand();
        if(weapon.getType() == Material.AIR) return;
        if (!hasEnchantment(weapon)) return;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 30);
        Bukkit.getBanList(BanList.Type.NAME).addBan(deceased.getName(), "Banned by " + killer.getName() + " using Ban Enchantment", cal.getTime(), null);
    }

}
