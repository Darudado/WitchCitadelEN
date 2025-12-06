package data.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.JumpPointAPI.JumpDestination;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.MusicPlayerPluginImpl;
import com.fs.starfarer.api.impl.campaign.ids.Tags;

import data.scripts.util.MagicCampaign;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//主要来源于虚无主义教程
// 修改文件名和下方为 你的星系名
// 在教程范例中为 Nipher
public class Other_Void {

    public void generate(SectorAPI sector) {

        LocationAPI hyper = Global.getSector().getHyperspace();

        StarSystemAPI system = sector.createStarSystem("Other_Void");
        system.getLocation().set(-30000f, -20000f);
        system.setLightColor(new Color(200, 200, 210));
        PlanetAPI star = system.initStar("Other_Void", "black_hole", 900f, 850f);
        system.getMemoryWithoutUpdate().set(MusicPlayerPluginImpl.MUSIC_SET_MEM_KEY, "Crimson_citadel_system");
        star.setCustomDescriptionId("Other_Void");
        system.addTag("theme_unsafe");
        system.addTag("theme_hidden");
        system.addTag("WthC_Other_Void");

        SectorEntityToken Starship_lose = system.addCustomEntity("Starship_lose", "Lost Ship", "Starship_lose", "Crimson_citadel");
        Starship_lose.setCircularOrbitPointingDown(star, 180, 3500, 60);
        Starship_lose.setCustomDescriptionId("Starship_lose");
        Starship_lose.setInteractionImage("illustrations", "WthC_Crimson_citadel");

        SectorEntityToken gate2 = system.addCustomEntity("WthC_Other_gate", "Light-Dark Node", "inactive_gate", "Crimson_citadel");
        gate2.setCircularOrbit(star, 0, 3500, 60);
        gate2.setCustomDescriptionId("WthC_Other_gate");

        SectorEntityToken relay = system.addCustomEntity("Other_relay", "Gaze Pivot", "comm_relay", "Crimson_citadel");
        relay.setCircularOrbitPointingDown(star, 0, 10000, 120);

        MarketAPI Starship_lose_market = addMarket(Starship_lose, "Crimson_citadel", 0.15f, // Planet, faction, and tax rate
                // Look for planet traits in data/campaign/conditions. population_x is required.
                new ArrayList(Arrays.asList("population_1", "beyondthenoisyworld")),
                new ArrayList(Arrays.asList("population", "stardustnode", "serdgenerator")),
                new ArrayList(Arrays.asList("warehouse_dust", "storage")));
        Starship_lose.getMarket().getMemoryWithoutUpdate().set("$noBar", true);
        Starship_lose.getMarket().getSubmarket("warehouse_dust").getCargo().addCommodity("supplies", 20000f);
        Starship_lose.getMarket().getSubmarket("warehouse_dust").getCargo().addCommodity("fuel", 50000f);

        // Planets
        PlanetAPI Planet1 = system.addPlanet("Other_Void_fire", star, "Fire Prison", "lava", 72.0F, 125.0F, 5500.0F, 60.0F);
        Planet1.setCustomDescriptionId("Other_Void_fire");

        PlanetAPI Planet2 = system.addPlanet("Other_Void_water", star, "Vast Ocean", "water", 288.0F, 125.0F, 5500.0F, 60.0F);
        Planet2.setCustomDescriptionId("Other_Void_water");

        PlanetAPI Planet3 = system.addPlanet("Other_Void_soil", star, "Desolation", "barren", 144.0F, 125.0F, 5500.0F, 60.0F);
        Planet3.setCustomDescriptionId("Other_Void_soil");

        PlanetAPI Planet4 = system.addPlanet("Other_Void_wood", star, "Overgrowth", "jungle", 0.0F, 125.0F, 5500.0F, 60.0F);
        Planet4.setCustomDescriptionId("Other_Void_wood");

        PlanetAPI Planet5 = system.addPlanet("Other_Void_gold", star, "Malignant", "toxic", 216.0F, 125.0F, 5500.0F, 60.0F);
        Planet5.setCustomDescriptionId("Other_Void_gold");

        PlanetAPI Planet6 = system.addPlanet("Other_Void_initial", star, "Suffocation", "gas_giant", 90.0F, 250.0F, 10000.0F, 120.0F);
        Planet6.setCustomDescriptionId("Other_Void_initial");

        PlanetAPI Planet7 = system.addPlanet("Other_Void_end", star, "Frost", "ice_giant", 270.0F, 250.0F, 10000.0F, 120.0F);
        Planet7.setCustomDescriptionId("Other_Void_end");
        



        //修正和势力
                Planet1.getMarket().addCondition("WthC_Xijie");
                
                Planet2.getMarket().addCondition("WthC_Xijie");
                
                Planet3.getMarket().addCondition("WthC_Xijie");
                
                Planet4.getMarket().addCondition("WthC_Xijie");
                
                Planet5.getMarket().addCondition("WthC_Xijie");
                
                Planet6.getMarket().addCondition("WthC_Xijie");

                Planet7.getMarket().addCondition("WthC_Xijie");

                

                

        system.generateAnchorIfNeeded();


        JumpPointAPI jumppoint = Global.getFactory().createJumpPoint("Other_Void_Jump", "Gate of Light and Shadow");
        OrbitAPI the_orbit = Global.getFactory().createCircularOrbit(Planet7, 40.0F, 500.0F, 250.0F);
        jumppoint.setOrbit(the_orbit);
        jumppoint.addTag(Tags.NO_ENTITY_TOOLTIP);
        jumppoint.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumppoint);

        JumpPointAPI jumpPointH2 = Global.getFactory().createJumpPoint("Other_Void_JumpH", "Gate of Light and Shadow");
        jumpPointH2.getLocation().set(-30000f, -20000f);
        jumpPointH2.addTag(Tags.NO_ENTITY_TOOLTIP);
        jumpPointH2.setStandardWormholeToStarfieldVisual();
        jumpPointH2.setAutogenJumpPointNameInHyper("Gate of Light and Shadow");
        hyper.addEntity(jumpPointH2);

        jumpPointH2.setAutogenJumpPointNameInHyper("Gate of Light and Shadow");
        JumpDestination point = new JumpDestination(jumppoint, "Gate of Light and Shadow");
        JumpDestination pointH2 = new JumpDestination(jumpPointH2, "Gate of Light and Shadow");
        jumppoint.addDestination(pointH2);
        jumpPointH2.addDestination(point);
        
        MagicCampaign.hyperspaceCleanup(system);
    }
    private MarketAPI addMarket(SectorEntityToken entity, String faction, float tarrif, java.util.List<String> conditions, java.util.List<String> industries, List<String> submarkets) {
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
}
