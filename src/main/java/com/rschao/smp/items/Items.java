package com.rschao.smp.items;

import com.rschao.smp.Plugin;

import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
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
   public NamespacedKey UnbanBeaconKey;
   public NamespacedKey BanBeaconKey;
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
   public static ItemStack UnbanBeacon;
   public static ItemStack BanBeacon;
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
      UnbanBeaconKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "unbankon");
      BanBeaconKey = new NamespacedKey(Plugin.getPlugin(Plugin.class), "bankon");
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
      if (this.plugin.getConfig().getBoolean("items.pots")) {
         this.Pots();
      }

      if (this.plugin.getConfig().getBoolean("items.vanillaplus")) {
         this.vanillaplus();
      }

      if (this.plugin.getConfig().getBoolean("items.OPKit")) {
         this.OPKit();
      }

      this.BanSword();
      this.BadOmen();
      this.createTestSword();
      this.LifeUpGem();
      MagicStone();
      HeartExtractor();
      ReviveBeacon();
      Emblem(17);
      EmblemTotem(17);
      BuffPot();
      PicoPato();
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("shulkershellxd"), new ItemStack(Material.SHULKER_SHELL, 2));
      sr.shape(new String[]{" A ", "AOA", " A "});
      sr.setIngredient('A', Material.AMETHYST_SHARD);
      sr.setIngredient('O', Material.OBSIDIAN);
      sr.setCategory(CraftingBookCategory.MISC);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("compressedpearl"), item);
      sr.shape(new String[]{"PPP", "PPP", "PPP"});
      sr.setIngredient('P', Material.ENDER_PEARL);
      sr.setCategory(CraftingBookCategory.MISC);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("swordrecipe"), item);
      sr.shape(new String[]{"FGF", "GCG", "FGF"});
      sr.setIngredient('F', new ExactChoice(PureSF));
      sr.setIngredient('G', Material.GOLDEN_APPLE);
      sr.setIngredient('C', Material.FISHING_ROD);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
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
      ShapedRecipe hg = new ShapedRecipe(NamespacedKey.minecraft("godgem"), item);
      hg.shape(new String[]{"NGN", "CSC", "NGN"});
      hg.setIngredient('S', Material.NETHER_STAR);
      hg.setIngredient('N', Material.NETHERITE_INGOT);
      hg.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
      hg.setIngredient('C', new ExactChoice(smallhgem));
      hg.setCategory(CraftingBookCategory.EQUIPMENT);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("speedfragrecipe"), item);
      sr.shape(new String[]{"SQS", "QDQ", "SQS"});
      sr.setIngredient('S', Material.SUGAR);
      sr.setIngredient('Q', Material.QUARTZ);
      sr.setIngredient('D', Material.DIAMOND);
      sr.setCategory(CraftingBookCategory.MISC);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("tooheart"), item);
      sr.shape(new String[]{"FIF", "DND", "GIG"});
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setIngredient('D', Material.DIAMOND_BLOCK);
      sr.setIngredient('I', Material.NETHERITE_INGOT);
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('G', Material.GOLD_BLOCK);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      Bukkit.getServer().addRecipe(sr);
   }

   void VanillaRecipes() {
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("notch"), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
      sr.shape(new String[]{"GGG", "GNG", "GGG"});
      sr.setIngredient('G', Material.GOLD_INGOT);
      sr.setIngredient('N', Material.GOLDEN_APPLE);
      Bukkit.getServer().addRecipe(sr);
      sr = null;
      sr = new ShapedRecipe(NamespacedKey.minecraft("endportal"), new ItemStack(Material.END_PORTAL_FRAME));
      sr.shape(new String[]{"PEP", "SCS", "SSS"});
      sr.setIngredient('P', new ExactChoice(CompressedPearl));
      sr.setIngredient('E', Material.ENDER_EYE);
      sr.setIngredient('S', Material.END_STONE);
      sr.setIngredient('C', Material.END_CRYSTAL);
      sr.setCategory(CraftingBookCategory.BUILDING);
      Bukkit.getServer().addRecipe(sr);
      ShapelessRecipe Sr = new ShapelessRecipe(NamespacedKey.minecraft("froglight_o"), new ItemStack(Material.OCHRE_FROGLIGHT));
      Sr.addIngredient(Material.SLIME_BALL);
      Sr.addIngredient(Material.MAGMA_BLOCK);
      Sr.addIngredient(Material.ORANGE_DYE);
      Bukkit.addRecipe(Sr);
      Sr = null;
      Sr = new ShapelessRecipe(NamespacedKey.minecraft("froglight_p"), new ItemStack(Material.PEARLESCENT_FROGLIGHT));
      Sr.addIngredient(Material.SLIME_BALL);
      Sr.addIngredient(Material.MAGMA_BLOCK);
      Sr.addIngredient(Material.PURPLE_DYE);
      Bukkit.addRecipe(Sr);
      Sr = null;
      Sr = new ShapelessRecipe(NamespacedKey.minecraft("froglight_v"), new ItemStack(Material.VERDANT_FROGLIGHT));
      Sr.addIngredient(Material.SLIME_BALL);
      Sr.addIngredient(Material.MAGMA_BLOCK);
      Sr.addIngredient(Material.GREEN_DYE);
      Bukkit.addRecipe(Sr);
      Sr = null;
      Sr = new ShapelessRecipe(NamespacedKey.minecraft("shardgud"), new ItemStack(Material.ECHO_SHARD));
      Sr.addIngredient(Material.AMETHYST_SHARD);
      Sr.addIngredient(Material.SCULK);
      Sr.addIngredient(Material.SCULK);
      Sr.addIngredient(Material.SCULK);
      Sr.addIngredient(Material.SCULK);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("sharpsword"), item);
      sr.shape(new String[]{" A ", "ASA", " A "});
      sr.setIngredient('A', new ExactChoice(smallhgem));
      sr.setIngredient('S', Material.NETHERITE_SWORD);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.addRecipe(sr);
   }

   void OPArmor() {
      ItemStack item = new ItemStack(Material.NETHERITE_HELMET);
      ItemMeta meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      item.setItemMeta(meta);
      OPHelm = item;
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ophelm"), item);
      sr.shape(new String[]{"NHN"});
      sr.setIngredient('H', Material.NETHERITE_HELMET);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.addRecipe(sr);
      sr = null;
      item = null;
      item = new ItemStack(Material.NETHERITE_CHESTPLATE);
      meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      item.setItemMeta(meta);
      OPChest = item;
      sr = new ShapedRecipe(NamespacedKey.minecraft("opchest"), item);
      sr.shape(new String[]{"NCN"});
      sr.setIngredient('C', Material.NETHERITE_CHESTPLATE);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.addRecipe(sr);
      sr = null;
      item = null;
      item = new ItemStack(Material.NETHERITE_LEGGINGS);
      meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      item.setItemMeta(meta);
      OPLeggs = item;
      sr = new ShapedRecipe(NamespacedKey.minecraft("opleggs"), item);
      sr.shape(new String[]{"NLN"});
      sr.setIngredient('L', Material.NETHERITE_LEGGINGS);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.addRecipe(sr);
      sr = null;
      item = null;
      item = new ItemStack(Material.NETHERITE_BOOTS);
      meta = item.getItemMeta();
      meta.addEnchant(Enchantment.PROTECTION, 5, true);
      meta.addEnchant(Enchantment.FEATHER_FALLING, 5, true);
      item.setItemMeta(meta);
      OPBoots = item;
      sr = new ShapedRecipe(NamespacedKey.minecraft("opboots"), item);
      sr.shape(new String[]{"NBN"});
      sr.setIngredient('B', Material.NETHERITE_BOOTS);
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
      server.addRecipe(sr);
   }

   static void OPShield() {
      ItemStack item = new ItemStack(Material.SHIELD);
      ItemMeta meta = item.getItemMeta();
      meta.setUnbreakable(true);
      item.setItemMeta(meta);
      Shield = item;
   }

   void LunarPick() {
      ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.LPKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.YELLOW + "Lunar Pickaxe");
      item.setItemMeta(meta);
      lunarPick = item;
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("lunarpiky"), item);
      sr.shape(new String[]{"DDD", " S ", " S "});
      sr.setIngredient('D', Material.DIAMOND_BLOCK);
      sr.setIngredient('S', Material.BLAZE_ROD);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("gpot"), item);
      sr.shape(new String[]{"SFS"});
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('S', Material.PRISMARINE_SHARD);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().addRecipe(sr);
   }

   void HastePot() {
      ItemStack item = new ItemStack(Material.QUARTZ);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.HasteKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.YELLOW + "Haste Essence");
      item.setItemMeta(meta);
      HastePot = item;
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("hpot"), item);
      sr.shape(new String[]{"SFS"});
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('S', Material.QUARTZ);
      sr.setCategory(CraftingBookCategory.MISC);
      Bukkit.getServer().addRecipe(sr);
   }

   void SlowPot() {
      ItemStack item = new ItemStack(Material.AMETHYST_SHARD);
      ItemMeta meta = item.getItemMeta();
      meta.getPersistentDataContainer().set(this.SlowKey, PersistentDataType.BOOLEAN, true);
      meta.setItemName(ChatColor.BLACK + "Stillness Essence");
      item.setItemMeta(meta);
      SlowPot = item;
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("spot"), item);
      sr.shape(new String[]{"SFS"});
      sr.setIngredient('F', new ExactChoice(SpeedFragment));
      sr.setIngredient('S', Material.AMETHYST_SHARD);
      sr.setCategory(CraftingBookCategory.MISC);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("adminabuse"), BadOmen);
      sr.shape(new String[]{"EEE", "EDE", "EEE"});
      sr.setIngredient('E', Material.EMERALD);
      sr.setIngredient('D', Material.TOTEM_OF_UNDYING);
      sr.setCategory(CraftingBookCategory.MISC);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("thevickytoria"), LifeUpGem);
      sr.shape(new String[]{"HDH", "DSD", "HDH"});
      sr.setIngredient('H', new ExactChoice(smallhgem));
      sr.setIngredient('D', Material.DIAMOND);
      sr.setIngredient('S', Material.WITHER_SKELETON_SKULL);
      sr.setCategory(CraftingBookCategory.MISC);
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
      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("optrim"), HeartExtractor);
      sr.shape("AFA", "NSN", "AFA");
      sr.setIngredient('S', new ExactChoice(MagicStone));
      sr.setIngredient('A', new ExactChoice(SlowPot));
      sr.setIngredient('F', new ExactChoice(HealthGem));
      sr.setIngredient('N', Material.ENCHANTED_GOLDEN_APPLE);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
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

      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("opbeacon"), ReviveBeacon);
      sr.shape("CFC", "FSF", "OOO");
      sr.setIngredient('F', new ExactChoice(LifeUpGem));
      sr.setIngredient('C', new ExactChoice(CompressedPearl));
      sr.setIngredient('S', Material.END_CRYSTAL);
      sr.setIngredient('O', Material.OBSIDIAN);
      sr.setCategory(CraftingBookCategory.EQUIPMENT);
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

      ShapelessRecipe sr = new ShapelessRecipe(NamespacedKey.minecraft("wither"), item);
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

      ShapelessRecipe sr = new ShapelessRecipe(NamespacedKey.minecraft("bufitos"), item);
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
      meta.setItemName("Tipped arrow of Glitch");
      meta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 800, 255), true);
      meta.setColor(Color.RED);
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
      meta.setItemName(ChatColor.DARK_PURPLE + "Showdown " + ChatColor.GOLD + "SMP " + ChatColor.LIGHT_PURPLE + "Sacred Totem");
      List<String> list = new ArrayList<String>();
      meta.getPersistentDataContainer().set(EmblemTotemKey, PersistentDataType.BOOLEAN, true);
      list.add("This emblem powered up by");
      list.add("the true gods of Showdown SMP.");
      list.add("It will act as a totem ");
      list.add("almost guaranteeing your survival.");
      list.add("It marks the success of SSMP Season 2.");
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

      ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("pico_pato"), item);
      sr.shape("BBB", "BBB", "BBB");
      sr.setIngredient('B', Material.BARRIER);
      Bukkit.addRecipe(sr);
   }
}
