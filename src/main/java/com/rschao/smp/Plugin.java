package com.rschao.smp;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.FancyAdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import com.fren_gor.ultimateAdvancementAPI.util.CoordAdapter;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;
import com.rschao.plugins.showdowncore.showdownCore.api.runnables.ShowdownScript;
import com.rschao.plugins.showdowncore.showdownCore.api.runnables.registry.ScriptRegistry;
import com.rschao.smp.advs.AdvancementTabNamespaces;
import com.rschao.smp.advs.tab2.Diamond23;
import com.rschao.smp.advs.tab2.Feather19;
import com.rschao.smp.advs.tab2.Maxhearts;
import com.rschao.smp.advs.tab2.Maxlives;
import com.rschao.smp.advs.tab2._10lives;
import com.rschao.smp.advs.tab2._15hearts;
import com.rschao.smp.advs.tab2._20hearts;
import com.rschao.smp.advs.tree_1.Dragon_egg;
import com.rschao.smp.advs.tree_1.End_fight;
import com.rschao.smp.advs.tree_1.Goated;
import com.rschao.smp.advs.tree_1.Heartslmao;
import com.rschao.smp.advs.tree_1.Kill3_20;
import com.rschao.smp.advs.tree_1.Lol;
import com.rschao.smp.advs.tree_1.Pk;
import com.rschao.smp.advs.tree_1.Shieldadv;
import com.rschao.smp.advs.tree_1.Ssmp_end;
import com.rschao.smp.commands.*;
import com.rschao.smp.enchants.armor.FlameArmorEnchant;
import com.rschao.smp.enchants.bow.UltraInfinityEnchant;
import com.rschao.smp.enchants.emblem.GodTouchEnchant;
import com.rschao.smp.enchants.sword.AttractionEnchant;
import com.rschao.smp.enchants.sword.LifeDrainEnchant;
import com.rschao.smp.enchants.tools.SmelterEnchant;
import com.rschao.smp.events.*;
import com.rschao.smp.items.Items;
import com.rschao.smp.lives.manageLife;
import com.rschao.smp.lives.saveData;

import com.rschao.smp.logs.EnderChestBackup;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {
   public static UltimateAdvancementAPI api;
   public AdvancementTab tree_1;
   public AdvancementTab tab2;
   public Plugin plugin;
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
   events evs;
   Items items;
   invEvents guiEvs;
   saveData lifeData;
   private final Logger LOGGER = Logger.getLogger("ShowdownSMP");
   private static boolean pauseLives = false;
    static List<EasyEnchant> ezenchants = new ArrayList<>();
    static ShowdownScript<Void> pauseLivesScript = new ShowdownScript<Void>((args) -> {
        boolean newState = (boolean) args[0];
        setPauseLives(newState);
        return null;
    });
    static ShowdownScript<Boolean> pauseLivesGet = new ShowdownScript<Boolean>((args) -> {
        return getPauseLives();
    });
    @Override
    public void onLoad() {

        this.LOGGER.info("Showdown SMP Custom plugin is loading");
        this.plugin = this;
        this.GemKey = new NamespacedKey(this.plugin, "HealthGem");
        this.speedKey = new NamespacedKey(this.plugin, "SpeedFragment");
        this.hgKey = new NamespacedKey(this.plugin, "SpaceUK");
        this.LPKey = new NamespacedKey(this.plugin, "LunarPickaxe");
        this.HasteKey = new NamespacedKey(this.plugin, "HastePot");
        this.SlowKey = new NamespacedKey(this.plugin, "NoMove");
        this.GPKey = new NamespacedKey(this.plugin, "MiningFatigue");
        this.BSKey = new NamespacedKey(this.plugin, "BanSword");
        this.BOKey = new NamespacedKey(this.plugin, "BadOmen");
        this.LifeKey = new NamespacedKey(this.plugin, "Lives");
        this.HexKey = new NamespacedKey(this.plugin, "HeartSave");
        this.HstKey = new NamespacedKey(this.plugin, "HeartSteal");
        lifeData = new saveData(plugin);
        this.items = new Items(this.plugin, this.GemKey, this.speedKey, this.hgKey, this.LPKey, this.HasteKey, this.SlowKey, this.GPKey, this.BSKey, this.BOKey, this.LifeKey, this.HexKey, this.HstKey);
        this.evs = new events(this.plugin, this.GemKey, this.speedKey, this.hgKey, this.LPKey, this.HasteKey, this.SlowKey, this.GPKey, this.BSKey, this.BOKey, this.LOGGER, this.LifeKey, this.HexKey, this.HstKey);

    }

    public void onEnable() {
      ezenchants.addAll(List.of(new AttractionEnchant(), new LifeDrainEnchant(), new FlameArmorEnchant(), new UltraInfinityEnchant(), new GodTouchEnchant(), new SmelterEnchant()));
      this.guiEvs = new invEvents();
      startEvents();
      this.items.Init();
      this.startCmds();
      this.initializeTabs();
      LOGGER.info("Backing up enderchests...");
      try {
         new EnderChestBackup().backupEnderChests();
         LOGGER.info("Enderchests backed up successfully");
      } catch (Exception e) {
         e.printStackTrace();
            LOGGER.severe("Failed to back up enderchests");
      }
        ScriptRegistry.registerScript("smp:pause_lives", pauseLivesScript);
        ScriptRegistry.registerScript("smp:get_pause_lives", pauseLivesGet);
      this.LOGGER.info("Showdown SMP Custom plugin has been enabled");
   }
   void startEvents(){
       this.getServer().getPluginManager().registerEvents(this.evs, this);
       this.getServer().getPluginManager().registerEvents(guiEvs, this);
       this.getServer().getPluginManager().registerEvents(this, this);
       if(getConfig().getBoolean("lives.enabled")){
           this.getServer().getPluginManager().registerEvents(new manageLife(), this);
       }
       for (EasyEnchant e : ezenchants){
           this.getServer().getPluginManager().registerEvents(e, this);
       }
   }
   void startCmds() {
      item.LoadCommand().register("showdownsmp");
      adminitem.LoadCommand().register("showdownsmp");
      tpworld.LoadCommand().register("showdownsmp");
      smpworld.LoadCommand().register("showdownsmp");
      time.LoadCommand().register("showdownsmp");
      time.night().register("showdownsmp");
      if(plugin.getConfig().getBoolean("lives.enabled")) {
          lifecheck.LoadCommand().register("showdownsmp");
          withdraw.LoadCommand().register("showdownsmp");
          revive.LoadCommand().register("showdownsmp");
      }
      showdown.LoadCommand().register("showdownsmp");
      FuseItems.LoadCommand().register("showdownsmp");
      togglePauseLives.LoadCommand().register("showdownsmp");
      showdown.LoadCommand().register("showdownsmp");
      addEnchant.LoadCommand().register("showdownsmp");
      TPCancelCommands.registerCommands();
   }
   public void initializeTabs() {
  api = UltimateAdvancementAPI.getInstance(this);
  tree_1 = api.createAdvancementTab(AdvancementTabNamespaces.tree_1_NAMESPACE);
  AdvancementKey tree_startKey = new AdvancementKey(tree_1.getNamespace(), "tree_start");
  CoordAdapter adaptertree_1 = CoordAdapter.builder().add(tree_startKey, 0f, 0f).add(Pk.KEY, 1f, 0f).add(Shieldadv.KEY, 1f, 1f).add(Heartslmao.KEY, 2f, 0.5f).add(Goated.KEY, 3f, 0f).add(Lol.KEY, 2f, -0.5f).add(Kill3_20.KEY, 4f, 0f).add(End_fight.KEY, 5f, 1f).add(Dragon_egg.KEY, 5f, -1f).add(Ssmp_end.KEY, 6f, 0f).build();
  tab2 = api.createAdvancementTab(AdvancementTabNamespaces.tab2_NAMESPACE);
  AdvancementKey rootKey = new AdvancementKey(tab2.getNamespace(), "root");
  CoordAdapter adaptertab2 = CoordAdapter.builder().add(tree_startKey, 0f, 0f).add(Pk.KEY, 1f, 0f).add(Shieldadv.KEY, 1f, 1f).add(Heartslmao.KEY, 2f, 0.5f).add(Goated.KEY, 3f, 0f).add(Lol.KEY, 2f, -0.5f).add(Kill3_20.KEY, 4f, 0f).add(End_fight.KEY, 5f, 1f).add(Dragon_egg.KEY, 5f, -1f).add(Ssmp_end.KEY, 6f, 0f).add(rootKey, 0f, 0f).add(_15hearts.KEY, 1f, -0.5f).add(_20hearts.KEY, 2f, -1f).add(Maxhearts.KEY, 3f, -1f).add(_10lives.KEY, 1f, 0.5f).add(Feather19.KEY, 2f, 1f).add(Diamond23.KEY, 4f, 0f).add(Maxlives.KEY, 3f, 1f).build();
  RootAdvancement tree_start = new RootAdvancement(tree_1, tree_startKey.getKey(), new AdvancementDisplay(Material.DIAMOND, "Plugin Advancements!", AdvancementFrameType.TASK, false, false, adaptertree_1.getX(tree_startKey), adaptertree_1.getY(tree_startKey), "§5Showdown §6SMP §aadvancements"),"textures/block/amethyst_block.png",1);
  Pk pk = new Pk(tree_start,adaptertree_1.getX(Pk.KEY), adaptertree_1.getY(Pk.KEY));
  Shieldadv shieldadv = new Shieldadv(tree_start,adaptertree_1.getX(Shieldadv.KEY), adaptertree_1.getY(Shieldadv.KEY));
  Heartslmao heartslmao = new Heartslmao(pk,adaptertree_1.getX(Heartslmao.KEY), adaptertree_1.getY(Heartslmao.KEY));
  Goated goated = new Goated(heartslmao,adaptertree_1.getX(Goated.KEY), adaptertree_1.getY(Goated.KEY));
  Lol lol = new Lol(pk,adaptertree_1.getX(Lol.KEY), adaptertree_1.getY(Lol.KEY));
  Kill3_20 kill3_20 = new Kill3_20(goated,adaptertree_1.getX(Kill3_20.KEY), adaptertree_1.getY(Kill3_20.KEY));
  End_fight end_fight = new End_fight(kill3_20,adaptertree_1.getX(End_fight.KEY), adaptertree_1.getY(End_fight.KEY));
  Dragon_egg dragon_egg = new Dragon_egg(kill3_20,adaptertree_1.getX(Dragon_egg.KEY), adaptertree_1.getY(Dragon_egg.KEY));
  Ssmp_end ssmp_end = new Ssmp_end(kill3_20,adaptertree_1.getX(Ssmp_end.KEY), adaptertree_1.getY(Ssmp_end.KEY));
  tree_1.registerAdvancements(tree_start ,pk ,shieldadv ,heartslmao ,goated ,lol ,kill3_20 ,end_fight ,dragon_egg ,ssmp_end );
  RootAdvancement root = new RootAdvancement(tab2, rootKey.getKey(), new FancyAdvancementDisplay(Material.DIAMOND_BLOCK, "Hearts and lives can also advance", AdvancementFrameType.TASK, true, true, adaptertab2.getX(rootKey), adaptertab2.getY(rootKey),"", ""),"textures/block/command_block_back.png",1);
  _15hearts __15hearts = new _15hearts(root,adaptertab2.getX(_15hearts.KEY), adaptertab2.getY(_15hearts.KEY));
  _20hearts __20hearts = new _20hearts(__15hearts,adaptertab2.getX(_20hearts.KEY), adaptertab2.getY(_20hearts.KEY));
  Maxhearts maxhearts = new Maxhearts(__20hearts,adaptertab2.getX(Maxhearts.KEY), adaptertab2.getY(Maxhearts.KEY));
  _10lives __10lives = new _10lives(root,adaptertab2.getX(_10lives.KEY), adaptertab2.getY(_10lives.KEY));
  Feather19 feather19 = new Feather19(__10lives,adaptertab2.getX(Feather19.KEY), adaptertab2.getY(Feather19.KEY));
  Maxlives maxlives = new Maxlives(feather19,adaptertab2.getX(Maxlives.KEY), adaptertab2.getY(Maxlives.KEY));
  Diamond23 diamond23 = new Diamond23( adaptertab2.getX(Diamond23.KEY), adaptertab2.getY(Diamond23.KEY),maxhearts);
  if(getConfig().getBoolean("lives.enabled")){
      tab2.registerAdvancements(root ,__15hearts ,__20hearts ,maxhearts ,__10lives ,feather19 ,maxlives ,diamond23 );
      return;
  }
    tab2.registerAdvancements(root ,__15hearts ,__20hearts ,maxhearts ,diamond23);
  }

   public void onDisable() {
      this.LOGGER.info("smp-plugin disabled");
   }

   @EventHandler
   public void onJoin(PlayerLoadingCompletedEvent e) {
      Player p = e.getPlayer();
      tab2.grantRootAdvancement(p);
      tree_1.grantRootAdvancement(p);
      getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:recipe give " + p.getName() + " *");
      
      if(!saveData.CheckExisting(p)){
         saveData.SaveLives(p, 5);
      }
      if(saveData.getLives(p) < 1){
         p.kickPlayer(ChatColor.RED + "You have been " + ChatColor.DARK_RED + "Death Banned. " + ChatColor.RED + "Wait for a friend to use a revive beacon on you.");
      }
   }
    public static boolean getPauseLives(){
        return pauseLives;
    }
    public static void setPauseLives(boolean b){
        pauseLives = b;
    }
}
