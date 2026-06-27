package data.plugin;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.OfficerDataAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import data.world.systems.WthC_Citadel;

import java.util.Objects;


public class WthC_CampaignListener extends BaseCampaignEventListener implements EveryFrameScript {

    public WthC_CampaignListener() {
        super(true);
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {
        if (market.hasIndustry("citadel") || market.hasIndustry("kingdom")) {
            if (isQuestChainFinished()) {
                if (!market.hasSubmarket("witch_ship_market")) {
                    market.addSubmarket("witch_ship_market");
                }
            }
        }
        FactionAPI CC = Global.getSector().getFaction("Crimson_citadel");
        FactionAPI PP = Global.getSector().getFaction("Pale_Phantom");
        if (market.getFaction() == CC) {
            market.getCommDirectory().clear();
            market.setAdmin(WthC_Citadel.getHILLYA());
            if (Objects.equals(market.getId(), "Crimson_citadel")) {
                market.getCommDirectory().addPerson(WthC_Citadel.getHILLYA());
                market.getCommDirectory().addPerson(WthC_Citadel.getRIKA());
                market.getCommDirectory().addPerson(WthC_Citadel.getYOUYU());
            }
            if (Objects.equals(market.getId(), "Starship_dust")) {
                market.getCommDirectory().addPerson(WthC_Citadel.getHILLYA());
                market.getCommDirectory().addPerson(WthC_Citadel.getNEIZER());
                market.getCommDirectory().addPerson(WthC_Citadel.getEDEN());

            }
        }
        if (market.getFaction() == PP) {
            market.getCommDirectory().clear();
            market.setAdmin(WthC_Citadel.getSU());
            if (Objects.equals(market.getId(), "dark_world_w")) {
                market.getCommDirectory().addPerson(WthC_Citadel.getROSALIA());
                market.getCommDirectory().addPerson(WthC_Citadel.getSU());
                if (Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_AL-3")){
                    market.getCommDirectory().addPerson(WthC_Citadel.getROMENA());
                }
            }
        }

        if (!(market.getSubmarket("black_market") == null)) {
            FleetDataAPI data = market.getSubmarket("black_market").getCargo().getMothballedShips();
            for (FleetMemberAPI member : data.getMembersListCopy()) {
                if (member.getHullId().contentEquals("WthC_Dreamer_IE_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_paragon_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Onslaught_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_irises_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_legion_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_conquest_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_pegasus_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Minerva_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Mithril_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Murderers_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Eagle_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_champion_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_fury_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_dominator_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Falcon_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Mora_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Onslaught_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_pegasus_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_conquest_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Mithril_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Onslaught_IE_B_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_pegasus_IE_B_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_conquest_IE_B_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Mithril_IE_B_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_paragon_IE_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_hyperion_SS_S_N")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Dreamer_IE")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_legion_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Holysee_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_dominator_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_champion_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_fury_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Eagle_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Falcon_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Mora_IE_B")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Brawler_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_lumen_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_glimmer_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_hyperion_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_scarab_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_monitor_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Centurion_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Medusa_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_whitebird_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_Alcedo_SS")) {
                    data.removeFleetMember(member);
                }
                if (member.getHullId().contentEquals("WthC_hyperion_SS_S")) {
                    data.removeFleetMember(member);
                }
            }
        }


        if (!(market.getSubmarket("exerelin_prismMarket") == null)) {

            FleetDataAPI data2 = market.getSubmarket("exerelin_prismMarket").getCargo().getMothballedShips();
            for (FleetMemberAPI member2 : data2.getMembersListCopy()) {
                if (member2.getHullId().contentEquals("WthC_Dreamer_IE_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_paragon_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Onslaught_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_irises_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_legion_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_conquest_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_pegasus_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Minerva_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Mithril_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Murderers_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Eagle_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_champion_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_fury_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_dominator_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Falcon_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Mora_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Onslaught_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_pegasus_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_conquest_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Mithril_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Onslaught_IE_B_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_pegasus_IE_B_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_conquest_IE_B_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Mithril_IE_B_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_paragon_IE_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_hyperion_SS_S_N")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Dreamer_IE")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_legion_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Holysee_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_dominator_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_champion_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_fury_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Eagle_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Falcon_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Mora_IE_B")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Brawler_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_lumen_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_glimmer_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_hyperion_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_scarab_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_monitor_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Centurion_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Medusa_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_whitebird_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_Alcedo_SS")) {
                    data2.removeFleetMember(member2);
                }
                if (member2.getHullId().contentEquals("WthC_hyperion_SS_S")) {
                    data2.removeFleetMember(member2);
                }
            }
        }
    }


        
    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
    }

    @Override
    public void reportEncounterLootGenerated(FleetEncounterContextPlugin plugin, CargoAPI loot) {
    }

    @Override
    public void reportBattleFinished(CampaignFleetAPI primaryWinner, BattleAPI battle) {
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    public void advance(float amount) {

    }

    private boolean isQuestChainFinished() {
        String[] keys = {
            "$WthC_CM-RRA",
            "$WthC_CM-Cross",
            "$WthC_CM-IE",
            "$WthC_CM-SSoul",
            "$WthC_CM-Elf",
            "$WthC_CM-Dust",
            "$WthC_BA-RRA",
            "$WthC_BA-Cross",
            "$WthC_BA-IE",
            "$WthC_BA-SSoul",
            "$WthC_BA-Elf",
            "$WthC_BA-Dust",
            "$WthC_AL-1",
            "$WthC_AL-2",
            "$WthC_AL-3"
        };
        for (String key : keys) {
            if (!Global.getSector().getMemoryWithoutUpdate().getBoolean(key)) {
                return false;
            }
        }
        return true;
    }
}