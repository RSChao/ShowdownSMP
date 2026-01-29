package com.rschao.smp.enchants.sword;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class LifeDrainEnchant extends EasyEnchant {
    public LifeDrainEnchant() {
        super("drain");
        CustomEnchantment enchant = makeEnchantment(ChatColor.DARK_RED + "Life Drain");
        enchant.setSupportedItem("#minecraft:enchantable/sharp_weapon");
        enchant.addExcludedEnchantment("minecraft:attraction");
        enchant.setMaxLevel(3);
        saveBukkitEnchantment(enchant);
    }


    @Override @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        //get item from the event even if entity is not player
        if (ev.getDamager() instanceof org.bukkit.entity.Player player) {
            ItemStack i = player.getInventory().getItemInMainHand();
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) return;
            if (!hasEnchantment(player.getInventory().getItemInMainHand())) return;
            Random random = new Random();
            if(random.nextInt(0, 100) > 30) return;
            int level = i.getEnchantmentLevel(Enchantment.getByKey(getKey()));
            if (level < 1) return;

            double damage = ev.getFinalDamage();
            double healAmount = damage * (0.1 * level); // 10% per level
            player.setHealth(Math.min(player.getHealth() + healAmount, Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getBaseValue()));
        }
    }
}
