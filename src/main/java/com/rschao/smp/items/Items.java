package com.rschao.smp.items;

import com.google.common.collect.Lists;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.registry.EnchantmentRegistry;
import com.rschao.plugins.showdowncore.showdownCore.api.items.registry.ItemRegistry;
import com.rschao.smp.Plugin;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Items {
   Plugin plugin;
   static Server server = Bukkit.getServer();
   public NamespacedKey GemKey;
   public NamespacedKey speedKey;
   public NamespacedKey hgKey;
   public NamespacedKey LPKey;
   public NamespacedKey HasteKey;
   public NamespacedKey SlowKey;
   public NamespacedKey GPKey;
   public NamespacedKey BSKey;
   public NamespacedKey BOKey;
   public NamespacedKey LifeKey;
   public NamespacedKey HexKey;
   public NamespacedKey HstKey;
   public NamespacedKey BeaconKey;
   public static NamespacedKey EmblemTotemKey;
   public static NamespacedKey EmblemKey;
   public static ItemStack testSword;
   public static ItemStack HealthGem;
   public static ItemStack SpeedFragment;
   public static ItemStack smallhgem;
   public static ItemStack OPSword;
   public static ItemStack OPHelm;
   public static ItemStack OPChest;
   public static ItemStack OPLeggs;
   public static ItemStack OPBoots;
   public static ItemStack Shield;
   public static ItemStack PureSF;
   public static ItemStack HastePot;
   public static ItemStack FatiguePot;
   public static ItemStack SlowPot;
   public static ItemStack lunarPick;
   public static ItemStack BanSword;
   public static ItemStack BadOmen;
   public static ItemStack LifeUpGem;
   public static ItemStack CompressedPearl;
   public static ItemStack HeartExtractor;
   public static ItemStack MagicStone;
   public static ItemStack ReviveBeacon;
   public static ItemStack Emblem;
   public static ItemStack TotemEmblem;
   public static ItemStack WitherPot;
   public static ItemStack GlitchArrow;
   public static ItemStack BuffPot;
   public static ItemStack DuckPick;

   public Items(Plugin plugin, NamespacedKey GemKey, NamespacedKey speedKey, NamespacedKey hgKey, NamespacedKey LPKey, NamespacedKey HasteKey, NamespacedKey SlowKey, NamespacedKey GPKey, NamespacedKey BSKey, NamespacedKey BOKey, NamespacedKey LifeKey, NamespacedKey HexKey, NamespacedKey HstKey) {
      this.plugin = plugin;
      this.GemKey = GemKey;
      this.speedKey = speedKey;
      this.hgKey = hgKey;
      this.LPKey = LPKey;
      this.HasteKey = HasteKey;
      this.SlowKey = SlowKey;
      this.GPKey = GPKey;
      this.BSKey = BSKey;
      this.BOKey = BOKey;
      this.LifeKey = LifeKey;
      this.HexKey = HexKey;
      this.HstKey = HstKey;
      BeaconKey = new NamespacedKey(this.plugin, "beakon");
      EmblemKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "ssmp_emblem");
      EmblemTotemKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "emblem");
   }

   public void Init() {
      this.SpeedFrag();
      this.PurifiedSF();
      this.SmallHGem();
      OPShield();
      this.LunarPick();
      this.HealthGemItem(7);
      this.Pots();
      this.vanillaplus();
      this.OPKit();
      this.BanSword();
      this.BadOmen();
      this.createTestSword();

       if(!plugin.getConfig().getBoolean("lives.enabled")) {
           this.LifeUpGem();
           ReviveBeacon();
       }
      MagicStone();
      HeartExtractor();
      Emblem(17);
      EmblemTotem(17);
      BuffPot();
      PicoPato();
      saveItemsInCore();
   }

   void saveItemsInCore(){
      ItemRegistry.registerItem("smp:1_8_sword", testSword);
      ItemRegistry.registerItem("smp:health_gem", HealthGem);
      if(!plugin.getConfig().getBoolean("lives.enabled")) {
            ItemRegistry.registerItem("smp:life_feather", LifeUpGem);
            ItemRegistry.registerItem("smp:revive_beacon", ReviveBeacon);
      }
      ItemRegistry.registerItem("smp:speed_fragment", SpeedFragment);
      ItemRegistry.registerItem("smp:small_health_gem", smallhgem);
      ItemRegistry.registerItem("smp:shield", Shield);
      ItemRegistry.registerItem("smp:lunar_pickaxe", lunarPick);
      ItemRegistry.registerItem("smp:haste_essence", HastePot);
      ItemRegistry.registerItem("smp:fatigue_essence", FatiguePot);
      ItemRegistry.registerItem("smp:stillness_essence", SlowPot);
      ItemRegistry.registerItem("smp:sword_of_judgement", BanSword);
        ItemRegistry.registerItem("smp:stolen_shard", BadOmen);
        ItemRegistry.registerItem("smp:compressed_end_power", CompressedPearl);
      ItemRegistry.registerItem("smp:magic_stone", MagicStone);
        ItemRegistry.registerItem("smp:heart_extractor", HeartExtractor);
      ItemRegistry.registerItem("showdown:emblem", Emblem);
        ItemRegistry.registerItem("showdown:totem_emblem", TotemEmblem);
      ItemRegistry.registerItem("smp:wither_pot", WitherPot);
      ItemRegistry.registerItem("smp:glitch_arrow", GlitchArrow);
      ItemRegistry.registerItem("smp:buff_pot", BuffPot);
      ItemRegistry.registerItem("smp:duck_pickaxe", DuckPick);
      ItemRegistry.registerItem("smp:op_sword", OPSword);
      ItemRegistry.registerItem("smp:op_helm", OPHelm);
        ItemRegistry.registerItem("smp:op_chest", OPChest);
        ItemRegistry.registerItem("smp:op_leggings", OPLeggs);
        ItemRegistry.registerItem("smp:op_boots", OPBoots);
   }

   public void Pots() {
      this.SlowPot();
      this.GuardianPot();
      this.HastePot();
      WitherPot();
      GlitchArrow();
   }

   public void vanillaplus() {
      CompressedPearl();
      this.VanillaRecipes();
      SSRecipe();
   }

   public void OPKit() {
      this.OPSword();
      this.OPArmor();
   }

   static void SSRecipe() {
      NamespacedKey key = NamespacedKey.minecraft("shulkershellxd");
      server.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, new ItemStack(Material.SHULKER_SHELL, 2));
      sr.shape(new String[]{" A ", "AOA", " A "});
      sr.setIngredient('A', Material.AMETHYST_SHARD);
      sr.setIngredient('O', Material.OBSIDIAN);
      sr.setCategory(CraftingBookCategory.MISC);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
   }

   static void CompressedPearl() {
      ItemStack item = new ItemStack(Material.ENDER_EYE, 1);
      ItemMeta meta = item.getItemMeta();
      meta.setItemName("Compressed End Power");
      meta.addEnchant(Enchantment.INFINITY, 1, false);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      item.setItemMeta(meta);
      CompressedPearl = item;
      NamespacedKey key = NamespacedKey.minecraft("compressedpearl");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"PPP", "PPP", "PPP"});
      sr.setIngredient('P', Material.ENDER_PEARL);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   private void createTestSword() {
      ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
      ItemMeta meta = item.getItemMeta();
      meta.setItemName(ChatColor.RED + "Sword of Fast Swipes");
      NamespacedKey key = new NamespacedKey(plugin, "cooldowngobrr");
      AttributeModifier mod = new AttributeModifier(key, 30, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
      meta.addAttributeModifier(Attribute.ATTACK_SPEED, mod);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
      item.setItemMeta(meta);
      testSword = item;
      NamespacedKey keyR = NamespacedKey.minecraft("swordrecipe");
      Bukkit.getServer().removeRecipe(keyR); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(keyR, item);
      sr.shape(new String[]{"FGF", "GCG", "FGF"});
      sr.setIngredient('F', new ExactChoice(PureSF));
      sr.setIngredient('G', Material.GOLDEN_APPLE);
      sr.setIngredient('C', Material.FISHING_ROD);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      Bukkit.getServer().removeRecipe(keyR); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   private void HealthGemItem(int data) {
      ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.GemKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.LIGHT_PURPLE + "Gem of Health");
      meta.setItemModel(NamespacedKey.minecraft("gem_twenty"));
      meta.setCustomModelData(data);
      meta.setRarity(ItemRarity.EPIC);
      item.setItemMeta(meta);
      HealthGem = item;
      NamespacedKey key = NamespacedKey.minecraft("godgem");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe hg = new ShapedRecipe(key, item);
      hg.shape(new String[]{"NGN", "CSC", "NGN"});
      hg.setIngredient('S', Material.NETHER_STAR);
      hg.setIngredient('N', Material.NETHERITE_INGOT);
      hg.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
      hg.setIngredient('C', new ExactChoice(smallhgem));
      hg.setCategory(CraftingBookCategory.EQUIPMENT);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(hg);
   }

   private void SpeedFrag() {
      ItemStack item = new ItemStack(Material.DIAMOND);
      ItemMeta meta = item.getItemMeta();
      meta.addEnchant(Enchantment.MENDING, 1, false);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      meta.getPersistentDataContainer().set(this.speedKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.AQUA + "Fragment of Swiftness");
      item.setItemMeta(meta);
      SpeedFragment = item;
      NamespacedKey key = NamespacedKey.minecraft("speedfragrecipe");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"SQS", "QDQ", "SQS"});
      sr.setIngredient('S', Material.SUGAR);
      sr.setIngredient('Q', Material.QUARTZ);
      sr.setIngredient('D', Material.DIAMOND);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void SmallHGem() {
      ItemStack item = new ItemStack(Material.EMERALD);
      ItemMeta meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 1, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      meta.getPersistentDataContainer().set(this.hgKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.RED + "Essence of Health");
      item.setItemMeta(meta);
      smallhgem = item;
      NamespacedKey key = NamespacedKey.minecraft("tooheart");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"FIF", "DND", "GIG"});
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setIngredient('D', Material.DIAMOND_BLOCK);
      sr.setIngredient('I', Material.NETHERITE_INGOT);
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('G', Material.GOLD_BLOCK);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void VanillaRecipes() {
      NamespacedKey key = NamespacedKey.minecraft("notch");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
      sr.shape(new String[]{"GGG", "GNG", "GGG"});
      sr.setIngredient('G', Material.GOLD_INGOT);
      sr.setIngredient('N', Material.GOLDEN_APPLE);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
      sr = null;
      key = NamespacedKey.minecraft("endportal");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      sr = new ShapedRecipe(key, new ItemStack(Material.END_PORTAL_FRAME));
      sr.shape(new String[]{"PEP", "SCS", "SSS"});
      sr.setIngredient('P', new ExactChoice(CompressedPearl));
      sr.setIngredient('E', Material.ENDER_EYE);
      sr.setIngredient('S', Material.END_STONE);
      sr.setIngredient('C', Material.END_CRYSTAL);
      sr.setCategory(CraftingBookCategory.BUILDING);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
      NamespacedKey skey = NamespacedKey.minecraft("froglight_o");
      Bukkit.removeRecipe(skey); // Remove if exists
      ShapelessRecipe Sr = new ShapelessRecipe(skey, new ItemStack(Material.OCHRE_FROGLIGHT));
      Sr.addIngredient(Material.SLIME_BALL);
      Sr.addIngredient(Material.MAGMA_BLOCK);
      Sr.addIngredient(Material.ORANGE_DYE);
      Bukkit.removeRecipe(skey); // Remove if exists
      Bukkit.addRecipe(Sr);
      skey = NamespacedKey.minecraft("froglight_p");
      Bukkit.removeRecipe(skey); // Remove if exists
      Sr = new ShapelessRecipe(skey, new ItemStack(Material.PEARLESCENT_FROGLIGHT));
      Sr.addIngredient(Material.SLIME_BALL);
      Sr.addIngredient(Material.MAGMA_BLOCK);
      Sr.addIngredient(Material.PURPLE_DYE);
      Bukkit.removeRecipe(skey); // Remove if exists
      Bukkit.addRecipe(Sr);
      skey = NamespacedKey.minecraft("froglight_v");
      Bukkit.removeRecipe(skey); // Remove if exists
      Sr = new ShapelessRecipe(skey, new ItemStack(Material.VERDANT_FROGLIGHT));
      Sr.addIngredient(Material.SLIME_BALL);
      Sr.addIngredient(Material.MAGMA_BLOCK);
      Sr.addIngredient(Material.GREEN_DYE);
      Bukkit.removeRecipe(skey); // Remove if exists
      Bukkit.addRecipe(Sr);
      skey = NamespacedKey.minecraft("shardgud");
      Bukkit.removeRecipe(skey); // Remove if exists
      Sr = new ShapelessRecipe(skey, new ItemStack(Material.ECHO_SHARD));
      Sr.addIngredient(Material.AMETHYST_SHARD);
      Sr.addIngredient(Material.SCULK);
      Sr.addIngredient(Material.SCULK);
      Sr.addIngredient(Material.SCULK);
      Sr.addIngredient(Material.SCULK);
      Bukkit.removeRecipe(skey); // Remove if exists
      Bukkit.addRecipe(Sr);
   }

   void OPSword() {
      ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
      ItemMeta meta = item.getItemMeta();
      meta.addEnchant(Enchantment.SHARPNESS, 6, true);
      meta.setUnbreakable(true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
      item.setItemMeta(meta);
      OPSword = item;
      NamespacedKey key = NamespacedKey.minecraft("sharpsword");
      server.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{" A ", "ASA", " A "});
      sr.setIngredient('A', new ExactChoice(smallhgem));
      sr.setIngredient('S', Material.NETHERITE_SWORD);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
   }

   void OPArmor() {
      ItemStack item = new ItemStack(Material.NETHERITE_HELMET);
      ItemMeta meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      item.setItemMeta(meta);
      OPHelm = item;
      NamespacedKey key = NamespacedKey.minecraft("ophelm");
      server.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"NHN"});
      sr.setIngredient('H', Material.NETHERITE_HELMET);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
      sr = null;
      item = null;
      item = new ItemStack(Material.NETHERITE_CHESTPLATE);
      meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      item.setItemMeta(meta);
      OPChest = item;
      key = NamespacedKey.minecraft("opchest");
      server.removeRecipe(key); // Remove if exists
      sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"NCN"});
      sr.setIngredient('C', Material.NETHERITE_CHESTPLATE);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
      sr = null;
      item = null;
      item = new ItemStack(Material.NETHERITE_LEGGINGS);
      meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      item.setItemMeta(meta);
      OPLeggs = item;
      key = NamespacedKey.minecraft("opleggs");
      server.removeRecipe(key); // Remove if exists
      sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"NLN"});
      sr.setIngredient('L', Material.NETHERITE_LEGGINGS);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
      sr = null;
      item = null;
      item = new ItemStack(Material.NETHERITE_BOOTS);
      meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      meta.addEnchant(Enchantment.FEATHER_FALLING, 5, true);
      item.setItemMeta(meta);
      OPBoots = item;
      key = NamespacedKey.minecraft("opboots");
      server.removeRecipe(key); // Remove if exists
      sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"NBN"});
      sr.setIngredient('B', Material.NETHERITE_BOOTS);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
   }

   void LunarPick() {
      ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.LPKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.YELLOW + "Lunar Pickaxe");
      item.setItemMeta(meta);
      lunarPick = item;
      NamespacedKey key = NamespacedKey.minecraft("lunarpiky");
      server.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"DDD", " S ", " S "});
      sr.setIngredient('D', Material.DIAMOND_BLOCK);
      sr.setIngredient('S', Material.BLAZE_ROD);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.removeRecipe(key); // Remove if exists
      server.addRecipe(sr);
   }

   void PurifiedSF() {
      ItemStack item = new ItemStack(Material.DIAMOND);
      ItemMeta meta = item.getItemMeta();
      meta.setItemName(ChatColor.GREEN + "Purified fragment of Swiftness");
      item.setItemMeta(meta);
      PureSF = item;
   }

   void GuardianPot() {
      ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
      ItemMeta meta = item.getItemMeta();
      meta.setItemName(ChatColor.GRAY + "Guardian Essence");
      meta.getPersistentDataContainer().set(this.GPKey, PersistentDataType.BOOLEAN, true);
      item.setItemMeta(meta);
      FatiguePot = item;
      NamespacedKey key = NamespacedKey.minecraft("gpot");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"SFS"});
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('S', Material.PRISMARINE_SHARD);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void HastePot() {
      ItemStack item = new ItemStack(Material.QUARTZ);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.HasteKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.YELLOW + "Haste Essence");
      item.setItemMeta(meta);
      HastePot = item;
      NamespacedKey key = NamespacedKey.minecraft("hpot");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"SFS"});
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('S', Material.QUARTZ);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void SlowPot() {
      ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.SlowKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.BLACK + "Stillness Essence");
      item.setItemMeta(meta);
      SlowPot = item;
      NamespacedKey key = NamespacedKey.minecraft("spot");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape(new String[]{"SFS"});
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('S', Material.AMETHYST_SHARD);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void BanSword() {
      ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.BSKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.RED + "Sword of Judgement");
      meta.addEnchant(Enchantment.SHARPNESS, 10, true);
      meta.setUnbreakable(true);
      List<String> list = new ArrayList<String>();
      list.add("Rename to number and time unit,");
      list.add("for example:");
      list.add("14d - 14 days");
      list.add("1mo - 1month");
      list.add("Leave default name for permban");
      meta.setLore(list);
      item.setItemMeta(meta);
      BanSword = item;
   }

   void BadOmen() {
      ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.BOKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.GRAY + "Stolen Shard");
      meta.addEnchant(Enchantment.INFINITY, 1, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      item.setItemMeta(meta);
      BadOmen = item;
      NamespacedKey key = NamespacedKey.minecraft("adminabuse");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, BadOmen);
      sr.shape(new String[]{"EEE", "EDE", "EEE"});
      sr.setIngredient('E', Material.EMERALD);
      sr.setIngredient('D', Material.TOTEM_OF_UNDYING);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void LifeUpGem() {
      ItemStack item = new ItemStack(Material.FEATHER);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.LifeKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.LIGHT_PURPLE + "Feather of Reincarnation");
      meta.addEnchant(Enchantment.INFINITY, 1, true);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      item.setItemMeta(meta);
      LifeUpGem = item;
      NamespacedKey key = NamespacedKey.minecraft("thevickytoria");
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, LifeUpGem);
      sr.shape(new String[]{"HDH", "DSD", "HDH"});
      sr.setIngredient('H', new ExactChoice(smallhgem));
      sr.setIngredient('D', Material.DIAMOND);
      sr.setIngredient('S', Material.WITHER_SKELETON_SKULL);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().removeRecipe(key); // Remove if exists
      Bukkit.getServer().addRecipe(sr);
   }

   void HeartExtractor(){
      ItemStack item = new ItemStack(Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(HexKey, PersistentDataType.INTEGER, 0);
      meta.setItemName(ChatColor.RED + "" + ChatColor.BOLD + "Health Extracting Amulet");
      meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
      List<String> list = new ArrayList<String>();
      list.add("Interact with the amulet to store 5 heart,");
      list.add("shift + click to release.");
      list.add("Control + click will store 10 hearts");
      list.add("and fill the amulet.");
      list.add("Once emptied, the amulet will break.");
      meta.setLore(list);
      item.setItemMeta(meta);
      HeartExtractor = item;
      //recipe
      NamespacedKey key = NamespacedKey.minecraft("optrim");
      Bukkit.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, HeartExtractor);
      sr.shape("AFA", "NSN", "AFA");
      sr.setIngredient('S', new ExactChoice(MagicStone));
      sr.setIngredient('A', new ExactChoice(SlowPot));
      sr.setIngredient('F', new ExactChoice(HealthGem));
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      Bukkit.removeRecipe(key); // Remove if exists
      Bukkit.addRecipe(sr);
   }
   void MagicStone(){
      ItemStack item = new ItemStack(Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(HstKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName("Unusual stone");
      List<String> list = new ArrayList<String>();
      list.add("Reacts to human souls");
      meta.setLore(list);
      item.setItemMeta(meta);
      MagicStone = item;
   }
   void ReviveBeacon(){
      ItemStack item = new ItemStack(Material.BEACON);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(BeaconKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.LIGHT_PURPLE + "Revive beacon");
      List<String> list = new ArrayList<String>();
      list.add("Place the beacon to revive a player!");
      meta.setLore(list);
      item.setItemMeta(meta);
      ReviveBeacon = item;
      NamespacedKey key = NamespacedKey.minecraft("opbeacon");
      Bukkit.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, ReviveBeacon);
      sr.shape("CFC", "FSF", "OOO");
      sr.setIngredient('F', new ExactChoice(LifeUpGem));
      sr.setIngredient('C', new ExactChoice(CompressedPearl));
      sr.setIngredient('S', Material.END_CRYSTAL);
      sr.setIngredient('O', Material.OBSIDIAN);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      Bukkit.removeRecipe(key); // Remove if exists
      Bukkit.addRecipe(sr);
   }
   void Emblem(int data){
      ItemStack item = new ItemStack(Material.NETHER_STAR);
      ItemMeta meta = item.getItemMeta();
      meta.setCustomModelData(data);
      meta.setMaxStackSize(1);
      meta.setItemModel(new NamespacedKey("minecraft", "emblem"));
      meta.setRarity(ItemRarity.EPIC);
      EquippableComponent eq = meta.getEquippable();
      eq.setAllowedEntities(EntityType.PLAYER);
      eq.setSlot(EquipmentSlot.OFF_HAND);
      meta.setEquippable(eq);
      meta.setItemName(ChatColor.DARK_PURPLE + "Showdown " + ChatColor.GOLD + "SMP " + ChatColor.DARK_RED + "Victory Emblem");
      meta.getPersistentDataContainer().set(EmblemKey, PersistentDataType.BOOLEAN, true);
      List<String> list = new ArrayList<String>();
      list.add("This emblem represents victory");
      list.add("in the Showdown SMP.");
      list.add("It is the ultimate treasure");
      list.add("any player may have.");
      list.add("It marks the end of an era in SSMP.");
      meta.setLore(list);
      item.setItemMeta(meta);
      Emblem = item;
   }
   void WitherPot(){
      ItemStack item = new ItemStack(Material.SPLASH_POTION);
      PotionMeta meta = (PotionMeta) item.getItemMeta();
      meta.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1), false);
      meta.setColor(Color.BLACK);
      item.setItemMeta(meta);
      WitherPot = item;
      NamespacedKey key = NamespacedKey.minecraft("wither");
      Bukkit.removeRecipe(key); // Remove if exists
      ShapelessRecipe sr = new ShapelessRecipe(key, item);
      sr.addIngredient(Material.GLASS_BOTTLE);
      sr.addIngredient(Material.WATER_BUCKET);
      sr.addIngredient(4, Material.WITHER_ROSE);
      Bukkit.addRecipe(sr);
   }
   void BuffPot(){
      ItemStack item = new ItemStack(Material.SPLASH_POTION);
      PotionMeta meta = (PotionMeta) item.getItemMeta();
      meta.setItemName("Splash potion of Buff");
      meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 90*20, 1), false);
      meta.addCustomEffect(new PotionEffect(PotionEffectType.STRENGTH, 90*20, 1), false);
      meta.addCustomEffect(new PotionEffect(PotionEffectType.RESISTANCE, 90*20, 1), false);
      meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 90*20, 1), false);
      meta.setColor(Color.fromBGR(255, 0, 210));
      item.setItemMeta(meta);
      BuffPot = item;

      NamespacedKey key = NamespacedKey.minecraft("bufitos");
      Bukkit.removeRecipe(key); // Remove if exists
      ShapelessRecipe sr = new ShapelessRecipe(key, item);
      sr.addIngredient(Material.GLASS_BOTTLE);
      sr.addIngredient(Material.WATER_BUCKET);
      sr.addIngredient(Material.NETHERITE_INGOT);
      sr.addIngredient(Material.SUGAR);
      Bukkit.addRecipe(sr);
   }
   void GlitchArrow(){
      ItemStack item = new ItemStack(Material.TIPPED_ARROW);
      PotionMeta meta = (PotionMeta) item.getItemMeta();
      meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 800, 255), true);
      meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 800, 255), true);
      meta.setColor(Color.RED);
      meta.setItemModel(NamespacedKey.minecraft("glitch_arrow"));
      meta.setItemName("Tipped arrow of Glitch");
      meta.setDisplayName("Tipped arrow of Glitch");
      item.setItemMeta(meta);
      GlitchArrow = item; 
   }
   
   void EmblemTotem(int data){
      ItemStack item = new ItemStack(Material.NETHER_STAR);
      ItemMeta meta = item.getItemMeta();
      meta.setCustomModelData(data);
      meta.setMaxStackSize(1);
      meta.setItemModel(new NamespacedKey("minecraft", "emblem"));
      meta.setRarity(ItemRarity.EPIC);
      EquippableComponent eq = meta.getEquippable();
      eq.setAllowedEntities(EntityType.PLAYER);
      eq.setEquipSound(null);
      eq.setSlot(EquipmentSlot.OFF_HAND);
      meta.setEquippable(eq);
      meta.addEnchant(EnchantmentRegistry.getEnchantment("minecraft:emblem_god_touch").toBukkitEnchantment(), 1, true);
      meta.setItemName(ChatColor.DARK_PURPLE + "Showdown " + ChatColor.GOLD + "SMP " + ChatColor.LIGHT_PURPLE + "Sacred Totem");
      List<String> list = new ArrayList<String>();
      meta.getPersistentDataContainer().set(EmblemTotemKey, PersistentDataType.BOOLEAN, true);
      list.add("This emblem powered up by");
      list.add("the true gods of Showdown SMP.");
      list.add("It will act as a totem ");
      list.add("almost guaranteeing your survival.");
      list.add("It marks the success of SSMP");
      meta.setLore(list);
      item.setItemMeta(meta);
      TotemEmblem = item;
   }
   void PicoPato(){
      ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
      ItemMeta meta = item.getItemMeta();
      meta.setRarity(ItemRarity.EPIC);
      meta.setItemName(ChatColor.YELLOW + "Great DuckPeak");
      meta.getPersistentDataContainer().set(LPKey, PersistentDataType.BOOLEAN, true);
      meta.setUnbreakable(true);
      meta.addEnchant(Enchantment.EFFICIENCY, 7, true);
      meta.addAttributeModifier(Attribute.BLOCK_INTERACTION_RANGE, new AttributeModifier(NamespacedKey.minecraft("pikopato"), 3, Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
      item.setItemMeta(meta);
      DuckPick = item;

      NamespacedKey key = NamespacedKey.minecraft("pico_pato");
      Bukkit.removeRecipe(key); // Remove if exists
      ShapedRecipe sr = new ShapedRecipe(key, item);
      sr.shape("BBB", "BBB", "BBB");
      sr.setIngredient('B', Material.BARRIER);
      Bukkit.addRecipe(sr);
   }

   void OPShield(){
       ItemStack item = new ItemStack(Material.SHIELD);
       ItemMeta meta = item.getItemMeta();
       meta.setUnbreakable(true);
       meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
       item.setItemMeta(meta);
       Shield = item;
   }

    public static ItemStack ssmpBanner(){
        ItemStack item = new ItemStack(Material.WHITE_BANNER);
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        meta.setItemName(ChatColor.DARK_PURPLE + "Showdown SMP " + ChatColor.GOLD + "Banner");
        List<Pattern> patterns = Lists.newArrayList();
        patterns.add(new Pattern(DyeColor.RED, PatternType.GRADIENT_UP));
        patterns.add(new Pattern(DyeColor.PURPLE, PatternType.FLOWER));
        patterns.add(new Pattern(DyeColor.PURPLE, PatternType.GRADIENT));
        meta.setPatterns(patterns);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack CrystalBanner(){
        ItemStack item = new ItemStack(Material.BLACK_BANNER);
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        meta.setItemName(ChatColor.DARK_PURPLE + "Crystal Banner");
        List<Pattern> patterns = Lists.newArrayList();
        patterns.add(new Pattern(DyeColor.MAGENTA, PatternType.CIRCLE));
        patterns.add(new Pattern(DyeColor.WHITE, PatternType.GLOBE));
        meta.setPatterns(patterns);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack AngelBanner(){
        ItemStack item = new ItemStack(Material.WHITE_BANNER);
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        meta.setItemName(ChatColor.GOLD + "Angelical Banner");
        List<Pattern> patterns = Lists.newArrayList();
        patterns.add(new Pattern(DyeColor.YELLOW, PatternType.CREEPER));
        patterns.add(new Pattern(DyeColor.YELLOW, PatternType.FLOWER));
        patterns.add(new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER));
        patterns.add(new Pattern(DyeColor.YELLOW, PatternType.STRAIGHT_CROSS));
        patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_BOTTOM));
        patterns.add(new Pattern(DyeColor.WHITE, PatternType.TRIANGLE_TOP));
        meta.setPatterns(patterns);
        item.setItemMeta(meta);
        return item;
    }
}
