package data.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.MusicPlayerPluginImpl;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.*;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.campaign.JumpPointAPI.JumpDestination;
import java.awt.Color;
import java.util.*;
import data.scripts.util.MagicCampaign;
import org.lwjgl.input.Keyboard;

//主要来源于虚无主义教程
// 修改文件名和下方为 你的星系名
// 在教程范例中为 Nipher
public class WthC_Citadel {

    public static BaseIndustry Crimson_citadel;

    public void generate(SectorAPI sector) {

        LocationAPI hyper = Global.getSector().getHyperspace();

        StarSystemAPI system = sector.createStarSystem("Void");
        system.getLocation().set(-22000f, -16000f);
        system.setLightColor(new Color(200, 200, 210));
        PlanetAPI star = system.initStar("Void", "black_hole", 900f, 850f);
        system.getMemoryWithoutUpdate().set(MusicPlayerPluginImpl.MUSIC_SET_MEM_KEY, "Crimson_citadel_system");
        star.setCustomDescriptionId("WthC_Citadel");
        system.addTag("WthC_Void");
        system.addTag("theme_unsafe");
        system.addTag("theme_hidden");


        // Space stations
        SectorEntityToken relay = system.addCustomEntity("Citadel_relay", "Citadel Watch Station", "comm_relay", "Crimson_citadel");
        relay.setCircularOrbitPointingDown(star, 0, 10000, 120);

        SectorEntityToken relay2 = system.addCustomEntity("Citadel_relay2", "Watch Pivot", "comm_relay", null);
        relay2.setCircularOrbitPointingDown(star, 90, 12000, 120);

        SectorEntityToken relay3 = system.addCustomEntity("Citadel_relay3", "Watch Pivot", "nav_buoy", null);
        relay3.setCircularOrbitPointingDown(star, 270, 12000, 120);

        SectorEntityToken gate = system.addCustomEntity("Citadel_gate", "Crimson Node", "inactive_gate", "Crimson_citadel");
        gate.setCircularOrbit(star, 180.0F, 10000.0F, 120);
        gate.setCustomDescriptionId("Citadel_gate");

        SectorEntityToken Crimson_citadel = system.addCustomEntity("Crimson_citadel", "Crimson Citadel", "Crimson_citadel", "Crimson_citadel");
        Crimson_citadel.setCircularOrbitPointingDown(star, 0, 3500, 60);
        Crimson_citadel.setCustomDescriptionId("station_Crimson_citadel");
        Crimson_citadel.setInteractionImage("illustrations", "WthC_Crimson_citadel");

        SectorEntityToken Starship_dust = system.addCustomEntity("Starship_dust", "Starship Dust", "Starship_dust", "Crimson_citadel");
        Starship_dust.setCircularOrbitPointingDown(star, 180, 3500, 60);
        Starship_dust.setCustomDescriptionId("Starship_dust");
        Starship_dust.setInteractionImage("illustrations", "WthC_Crimson_citadel");

        // Planets
        PlanetAPI Planet1 = system.addPlanet("anchor_point_fire", star, "Anchor of Strength", "lava", 72.0F, 125.0F, 5500.0F, 60.0F);
        Planet1.setCustomDescriptionId("anchor_point_fire");

        PlanetAPI Planet2 = system.addPlanet("anchor_point_water", star, "Anchor of Depth", "water", 288.0F, 125.0F, 5500.0F, 60.0F);
        Planet2.setCustomDescriptionId("anchor_point_water");

        PlanetAPI Planet3 = system.addPlanet("anchor_point_soil", star, "Anchor of Destiny", "barren", 144.0F, 125.0F, 5500.0F, 60.0F);
        Planet3.setCustomDescriptionId("anchor_point_soil");

        PlanetAPI Planet4 = system.addPlanet("anchor_point_wood", star, "Anchor of Life", "jungle", 0.0F, 125.0F, 5500.0F, 60.0F);
        Planet4.setCustomDescriptionId("anchor_point_wood");

        PlanetAPI Planet5 = system.addPlanet("anchor_point_gold", star, "Anchor of Resolve", "toxic", 216.0F, 125.0F, 5500.0F, 60.0F);
        Planet5.setCustomDescriptionId("anchor_point_gold");

        PlanetAPI Planet6 = system.addPlanet("anchor_point_initial", star, "Anchor of Faith", "gas_giant", 90.0F, 250.0F, 10000.0F, 120.0F);
        Planet6.setCustomDescriptionId("anchor_point_initial");

        PlanetAPI Planet7 = system.addPlanet("anchor_point_end", star, "Anchor of Loyalty", "ice_giant", 270.0F, 250.0F, 10000.0F, 120.0F);
        Planet7.setCustomDescriptionId("anchor_point_end");

        PlanetAPI Planet8 = system.addPlanet("dark_world_w", star, "Mirage", "desert", 45.0F, 300.0F, 20000.0F, 720.0F);
        Planet8.setCustomDescriptionId("dark_world_w");

        PlanetAPI Planet9 = system.addPlanet("XiJie", star, "Rift Realm", "terran", 225.0F, 300.0F, 20000.0F, 720.0F);
        Planet9.setCustomDescriptionId("XiJie");



        //修正和势力
                Planet1.getMarket().addCondition("very_hot");
                Planet1.getMarket().addCondition("thin_atmosphere");
                Planet1.getMarket().addCondition("toxic_atmosphere");
                Planet1.getMarket().addCondition("ore_abundant");
                Planet1.getMarket().addCondition("rare_ore_abundant");

                Planet2.getMarket().addCondition("water_surface");
                Planet2.getMarket().addCondition("habitable");
                Planet2.getMarket().addCondition("organics_plentiful");
                Planet2.getMarket().addCondition("rare_ore_abundant");
                Planet2.getMarket().addCondition("volatiles_abundant");
                Planet2.getMarket().addCondition("WthC_OldGod");

                Planet3.getMarket().addCondition("ore_moderate");
                Planet3.getMarket().addCondition("volatiles_diffuse");
                Planet3.getMarket().addCondition("no_atmosphere");
                Planet3.getMarket().addCondition("low_gravity");

                Planet4.getMarket().addCondition("habitable");
                Planet4.getMarket().addCondition("inimical_biosphere");
                Planet4.getMarket().addCondition("farmland_bountiful");
                Planet4.getMarket().addCondition("organics_abundant");
                Planet4.getMarket().addCondition("ore_abundant");
                Planet4.getMarket().addCondition("WthC_Titan");

                Planet5.getMarket().addCondition("ore_ultrarich");
                Planet5.getMarket().addCondition("rare_ore_ultrarich");
                Planet5.getMarket().addCondition("toxic_atmosphere");
                Planet5.getMarket().addCondition("extreme_weather");
                Planet5.getMarket().addCondition("extreme_tectonic_activity");
                Planet5.getMarket().addCondition("volatiles_plentiful");
                Planet5.getMarket().addCondition("irradiated");

                Planet6.getMarket().addCondition("extreme_weather");
                Planet6.getMarket().addCondition("high_gravity");
                Planet6.getMarket().addCondition("volatiles_plentiful");

                Planet7.getMarket().addCondition("dense_atmosphere");
                Planet7.getMarket().addCondition("high_gravity");
                Planet7.getMarket().addCondition("volatiles_plentiful");

                Planet9.getMarket().addCondition("habitable");
                Planet9.getMarket().addCondition("dark");
                Planet9.getMarket().addCondition("WthC_Xijie");






        MarketAPI Crimson_citadel_market = addMarket(Crimson_citadel, "Crimson_citadel", 0.15f, // 星球和势力和税率
                // 在 data/campaign/conditions里找各类星球特质。其中，population_x是必要的
                new ArrayList(Arrays.asList("population_3","beyondthenoisyworld")),
                new ArrayList(Arrays.asList("citadel", "stardustnode","serdgenerator","elistemple","treasuryvaultofcm","workshopie")),
                new ArrayList(Arrays.asList("open_market", "crimson_treasure","elis_temple","Witch_Data","storage")));
        Crimson_citadel.getMarket().getMemoryWithoutUpdate().set("$noBar",true);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Cube_Crimson", "special_items"), 20.0F);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Cube_DeepBlue", "special_items"), 20.0F);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Cube_DenseFog", "special_items"), 20.0F);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Agreement_Crew", "special_items"), 1000.0F);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Agreement_Supplies", "special_items"), 1000.0F);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Agreement_Fuel", "special_items"), 1000.0F);
        Crimson_citadel.getMarket().getSubmarket("crimson_treasure").getCargo().addSpecial(new SpecialItemData("Agreement_Story", "special_items"), 20.0F);
        Crimson_citadel.getMarket().getSubmarket("open_market").getCargo().addSpecial(new SpecialItemData("Archives_CM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("open_market").getCargo().addSpecial(new SpecialItemData("Archives_CM233", "special_items"), 1.0F);

        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Cube_Crimson", "special_items"), 1000.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Cube_Crimson10", "special_items"), 500.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Cube_DeepBlue", "special_items"), 1000.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Cube_DeepBlue10", "special_items"), 500.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Cube_DenseFog", "special_items"), 1000.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Cube_DenseFog10", "special_items"), 500.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Crad_DM", "special_items"), 10.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Crad_AL", "special_items"), 10.0F);
        Crimson_citadel.getMarket().getSubmarket("elis_temple").getCargo().addSpecial(new SpecialItemData("Page_Zoo", "special_items"), 1F);

        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_RRA_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_RRA_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_RRA_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRA_0", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRA_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRA_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRA_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRA_4", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRACM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_RRAEI", "special_items"), 1.0F);

        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Cross_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Cross_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Cross_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Cross_0", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Cross_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Cross_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Cross_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Cross_4", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_CrossCM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_CrossEI", "special_items"), 1.0F);

        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_IE_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_IE_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_IE_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IE_0", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IE_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IE_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IE_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IE_4", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IECM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_IEEI", "special_items"), 1.0F);

        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_SSoul_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_SSoul_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_SSoul_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoul_0", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoul_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoul_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoul_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoul_4", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoulCM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_SSoulEI", "special_items"), 1.0F);

        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Elf_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Elf_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Elf_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Elf_0", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Elf_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Elf_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Elf_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Elf_4", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_ElfCM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_ElfEI", "special_items"), 1.0F);

        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Dust_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Dust_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Data_Dust_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Dust_0", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Dust_1", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Dust_2", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Dust_3", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_Dust_4", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_DustCM", "special_items"), 1.0F);
        Crimson_citadel.getMarket().getSubmarket("Witch_Data").getCargo().addSpecial(new SpecialItemData("Archives_DustEI", "special_items"), 1.0F);

        MarketAPI Starship_dust_market = addMarket(Starship_dust, "Crimson_citadel", 0.15f, // 星球和势力和税率
                // 在 data/campaign/conditions里找各类星球特质。其中，population_x是必要的
                new ArrayList(Arrays.asList("population_1","beyondthenoisyworld")),
                new ArrayList(Arrays.asList("population", "stardustnode","serdgenerator")),
                new ArrayList(Arrays.asList("warehouse_dust","database_dust","storage")));
        Starship_dust.getMarket().getMemoryWithoutUpdate().set("$noBar",true);
        Starship_dust.getMarket().getSubmarket("warehouse_dust").getCargo().addCommodity("supplies", 20000f);
        Starship_dust.getMarket().getSubmarket("warehouse_dust").getCargo().addCommodity("fuel", 50000f);
        Starship_dust.getMarket().getSubmarket("warehouse_dust").getCargo().addCommodity("food", 50000f);
        Starship_dust.getMarket().getSubmarket("database_dust").getCargo().addSpecial(new SpecialItemData("Archives_CM2", "special_items"), 1.0F);

        MarketAPI market_2 = addMarket(Planet8, "Pale_Phantom", 0.35f, // 星球和势力和税率
                // 在 data/campaign/conditions里找各类星球特质。其中，population_x是必要的
                new ArrayList(Arrays.asList("population_4", "WthC_DarkWorld", "beyondthenoisyworld", "ore_abundant","habitable")),
                new ArrayList(Arrays.asList("kingdom")),
                new ArrayList(Arrays.asList("Between_Visits", "storage")));
        Planet8.getMarket().getMemoryWithoutUpdate().set("$noBar",true);
        Planet8.getMarket().getSubmarket("Between_Visits").getCargo().addSpecial(new SpecialItemData("Crad_B1", "special_items"), 1.0F);
        Planet8.getMarket().getSubmarket("Between_Visits").getCargo().addSpecial(new SpecialItemData("Data_Be_1", "special_items"), 1.0F);
        Planet8.getMarket().getSubmarket("Between_Visits").getCargo().addSpecial(new SpecialItemData("Archives_DH", "special_items"), 1.0F);
        Planet8.getMarket().getSubmarket("Between_Visits").getCargo().addSpecial(new SpecialItemData("Archives_ET", "special_items"), 1.0F);
        Planet8.getMarket().getSubmarket("Between_Visits").getCargo().addSpecial(new SpecialItemData("Archives_ZS", "special_items"), 1.0F);


        PersonAPI Hillya = Crimson_citadel_market.getFaction().createRandomPerson();
        Hillya.getName().setFirst("Hillya");
        Hillya.getName().setLast("Crimson");
        Hillya.setRankId(Ranks.FACTION_LEADER);
        Hillya.setPostId(Ranks.POST_FACTION_LEADER);
        Hillya.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        Hillya.setPortraitSprite("graphics/portraits/W01.png");
        Hillya.setId("Hillya");


        system.generateAnchorIfNeeded();
        system.autogenerateHyperspaceJumpPoints();


        JumpPointAPI jumppoint = Global.getFactory().createJumpPoint("Crimson_citadel_Jump", "Gate of Passing Darkness");
        OrbitAPI the_orbit = Global.getFactory().createCircularOrbit(Crimson_citadel, 40.0F, 500.0F, 250.0F);
        jumppoint.setOrbit(the_orbit);
        jumppoint.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumppoint);

        JumpPointAPI jumpPointH = Global.getFactory().createJumpPoint("Crimson_citadel_JumpH", "Gate of Passing Darkness");
        jumpPointH.getLocation().set(-22200f, -16200f);
        jumpPointH.setStandardWormholeToStarfieldVisual();
        jumpPointH.setAutogenJumpPointNameInHyper("Gate of Passing Darkness");
        hyper.addEntity(jumpPointH);

        JumpDestination point = new JumpDestination(jumppoint, "Gate of Passing Darkness");
        JumpDestination pointH = new JumpDestination(jumpPointH, "Gate of Passing Darkness");

        jumppoint.addDestination(pointH);
        jumpPointH.addDestination(point);

        Misc.makeStoryCritical(Crimson_citadel.getMarket(),"station_Crimson_citadel");
        Misc.makeStoryCritical(Starship_dust.getMarket(),"Starship_dust");
        Misc.makeStoryCritical(Planet8.getMarket(),"dark_world_w");

        MagicCampaign.hyperspaceCleanup(system);
    }


    private MarketAPI addMarket(SectorEntityToken entity, String faction, float tarrif, List<String> conditions, List<String> industries, List<String> submarkets) {
        int size = 0;
        for (String condition : conditions) {
            if (condition.startsWith("population_")) {
                String sub = condition.replace("population_", "");
                size = Integer.parseInt(sub);
            }
        }

        MarketAPI market = MagicCampaign.addSimpleMarket(entity, entity.getId(), entity.getName(),
                size, faction, false, false,
                conditions, industries, false, false, false, false, false, false);

        if (conditions.contains("free_market")) market.setFreePort(true);
        for (String submarket : submarkets) {
            market.addSubmarket(submarket);
        }

        market.getTariff().modifyFlat("generator", tarrif);
        Global.getSector().getEconomy().addMarket(market, true);

        entity.setMarket(market);
        entity.setFaction(faction);
        return market;
    }
    public static final String HILLYA_DATA_KEY = "$HILLYA_DATA_KEY";
    public static final String RIKA_DATA_KEY = "$RIKA_DATA_KEY";
    public static final String YOUYU_DATA_KEY = "$YOUYU_DATA_KEY";
    public static final String NEIZER_DATA_KEY = "$NEIZER_DATA_KEY";
    public static final String EDEN_DATA_KEY = "$EDEN_DATA_KEY";
    public static final String ROSALIA_DATA_KEY = "$ROSALIA_DATA_KEY";
    public static final String SU_DATA_KEY = "$SU_DATA_KEY";
    public static final String ROMENA_DATA_KEY = "$ROMENA_DATA_KEY";

    public static PersonAPI getHILLYA() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(HILLYA_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Crimson_citadel").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(HILLYA_DATA_KEY);
        person.getName().setFirst("Hillya");
        person.getName().setLast("Crimson");
        person.setRankId(Ranks.FACTION_LEADER);
        person.setPostId(Ranks.POST_FACTION_LEADER);
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W01.png");
        person.getMemoryWithoutUpdate().set("$Hillya_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }

    public static PersonAPI getROMENA() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(ROMENA_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Pale_Phantom").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(ROMENA_DATA_KEY);
        person.getName().setFirst("Freesia");
        person.getName().setLast("Blast");
        person.setRankId("WthC_RM1");
        person.setPostId("WthC_RM2");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/RM.png");
        person.getMemoryWithoutUpdate().set("$Romena_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
    public static PersonAPI getRIKA() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(RIKA_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Crimson_citadel").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(RIKA_DATA_KEY);
        person.getName().setFirst("Rika");
        person.getName().setLast("Primordial ");
        person.setRankId("WthC_Witch");
        person.setPostId("WthC_IE");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W02.png");
        person.getMemoryWithoutUpdate().set("$Rika_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
    public static PersonAPI getYOUYU() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(YOUYU_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Crimson_citadel").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(YOUYU_DATA_KEY);
        person.getName().setFirst("Youyu");
        person.getName().setLast("Wuxin");
        person.setRankId("WthC_Witch");
        person.setPostId("WthC_RRA");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W03.png");
        person.getMemoryWithoutUpdate().set("$YouYu_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
    public static PersonAPI getNEIZER() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(NEIZER_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Crimson_citadel").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(NEIZER_DATA_KEY);
        person.getName().setFirst("Nizer");
        person.getName().setLast("Dust");
        person.setRankId("WthC_Witch");
        person.setPostId("WthC_Dust");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W04.png");
        person.getMemoryWithoutUpdate().set("$Neizer_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
    public static PersonAPI getEDEN() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(EDEN_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Crimson_citadel").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(EDEN_DATA_KEY);
        person.getName().setFirst("Eden");
        person.getName().setLast("Ludwig Faust");
        person.setRankId("WthC_Witch");
        person.setPostId("WthC_Elf");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W05.png");
        person.getMemoryWithoutUpdate().set("$Eden_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
    public static PersonAPI getROSALIA() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(ROSALIA_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Pale_Phantom").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(ROSALIA_DATA_KEY);
        person.getName().setFirst("Rosalia");
        person.getName().setLast("Cross");
        person.setRankId("WthC_Witch");
        person.setPostId("WthC_Cross");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W07.png");
        person.getMemoryWithoutUpdate().set("$Rosalia_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
    public static PersonAPI getSU() {
        PersonAPI preload = Global.getSector().getImportantPeople().getPerson(SU_DATA_KEY);
        if (preload != null) return preload;

        PersonAPI person = Global.getSector().getFaction("Pale_Phantom").createRandomPerson(FullName.Gender.FEMALE);
        person.setId(SU_DATA_KEY);
        person.getName().setFirst("Su");
        person.getName().setLast("Soul");
        person.setRankId("WthC_Witch");
        person.setPostId("WthC_SSoul");
        person.setImportanceAndVoice(PersonImportance.HIGH, StarSystemGenerator.random);
        person.setPortraitSprite("graphics/portraits/W06.png");
        person.getMemoryWithoutUpdate().set("$Su_Person",true);
        Global.getSector().getImportantPeople().addPerson(person);
        Global.getSector().getImportantPeople().checkOutPerson(person, "permanent_staff");
        return person;
    }
}
