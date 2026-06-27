package data.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI.CoreUITradeMode;
import com.fs.starfarer.api.campaign.CoreUIAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin.OnClickAction;
import com.fs.starfarer.api.campaign.SubmarketPlugin.PlayerEconomyImpactMode;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.submarkets.BaseSubmarketPlugin;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.util.Highlights;
import com.fs.starfarer.api.util.Misc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WitchShipMarket extends BaseSubmarketPlugin {

    public WitchShipMarket() {
    }

    @Override
    public void init(SubmarketAPI submarket) {
        super.init(submarket);
        if (this.getCargo().getMothballedShips() == null) {
            this.getCargo().initMothballedShips(submarket.getFaction().getId());
        }
    }

    @Override
    public float getTariff() {
        return 2.0f;
    }

    @Override
    public boolean isIllegalOnSubmarket(CargoStackAPI stack, TransferAction action) {
        if (action == TransferAction.PLAYER_SELL) return true;
        return super.isIllegalOnSubmarket(stack, action);
    }

    @Override
    public boolean isIllegalOnSubmarket(FleetMemberAPI member, TransferAction action) {
        if (action == TransferAction.PLAYER_SELL) return true;
        return super.isIllegalOnSubmarket(member, action);
    }

    @Override
    public String getName() {
        if (this.submarket != null && this.submarket.getFaction() != null && "Pale_Phantom".equals(this.submarket.getFaction().getId())) {
            return "Pale Shipyard";
        }
        return "Crimson Shipyard";
    }

    @Override
    public PlayerEconomyImpactMode getPlayerEconomyImpactMode() {
        return PlayerEconomyImpactMode.NONE;
    }

    @Override
    public OnClickAction getOnClickAction(CoreUIAPI ui) {
        return OnClickAction.OPEN_SUBMARKET;
    }

    @Override
    public boolean isMilitaryMarket() {
        return true;
    }

    @Override
    public boolean isEnabled(CoreUIAPI ui) {
        if (ui.getTradeMode() == CoreUITradeMode.SNEAK) {
            return false;
        }
        return true;
    }

    @Override
    public String getTooltipAppendix(CoreUIAPI ui) {
        if (ui.getTradeMode() == CoreUITradeMode.SNEAK) {
            return "Requires: Authorization for normal docking";
        }
        return null;
    }

    @Override
    public Highlights getTooltipAppendixHighlights(CoreUIAPI ui) {
        String appendix = this.getTooltipAppendix(ui);
        if (appendix == null) {
            return null;
        } else {
            Highlights h = new Highlights();
            h.setText(new String[]{appendix});
            h.setColors(new Color[]{Misc.getNegativeHighlightColor()});
            return h;
        }
    }

    protected boolean isFirst = true;

    @Override
    public void updateCargoPrePlayerInteraction() {
        float days = Global.getSector().getClock().getElapsedDaysSince((long) this.sinceLastCargoUpdate);
        if (isFirst || this.sinceLastCargoUpdate == 0L || days >= 30.0F) {
            isFirst = false;
            this.sinceLastCargoUpdate = Global.getSector().getClock().getTimestamp();

            this.getCargo().getMothballedShips().clear();

            Random random = new Random();
            int numRares = 1 + random.nextInt(2); // 1 or 2 rare ships
            int numCommons = 2 + random.nextInt(3); // 2 to 4 common ships

            List<String> rarePool = new ArrayList<String>();
            rarePool.add("WthC_Dreamer_IE_N_Dre");
            rarePool.add("WthC_paragon_IE_N_IE");
            rarePool.add("WthC_Onslaught_IE_B_N_Dust");
            rarePool.add("WthC_pegasus_IE_B_N_SSoul");
            rarePool.add("WthC_conquest_IE_B_N_RRA");
            rarePool.add("WthC_Mithril_IE_B_N_Cross");
            rarePool.add("WthC_hyperion_SS_S_N_Elf");
            rarePool.add("WthC_Dreamer_IE_Dre");
            rarePool.add("WthC_PaleCrown_始与终");

            List<String> commonPool = new ArrayList<String>();
            // Elites
            commonPool.add("WthC_Onslaught_IE_Elite");
            commonPool.add("WthC_irises_IE_Elite");
            commonPool.add("WthC_legion_IE_Elite");
            commonPool.add("WthC_conquest_IE_Elite");
            commonPool.add("WthC_pegasus_IE_Elite");
            commonPool.add("WthC_Minerva_IE_Elite");
            commonPool.add("WthC_Mithril_IE_Elite");
            commonPool.add("WthC_Murderers_IE_Elite");
            commonPool.add("WthC_Eagle_IE_Elite");
            commonPool.add("WthC_champion_IE_Elite");
            commonPool.add("WthC_fury_IE_Elite");
            commonPool.add("WthC_dominator_IE_Elite");

            // Standards
            commonPool.add("WthC_Onslaught_IE_Standard");
            commonPool.add("WthC_irises_IE_Standard");
            commonPool.add("WthC_legion_IE_Standard");
            commonPool.add("WthC_conquest_IE_Standard");
            commonPool.add("WthC_pegasus_IE_Standard");
            commonPool.add("WthC_Minerva_IE_Standard");
            commonPool.add("WthC_Mithril_IE_Standard");
            commonPool.add("WthC_Murderers_IE_Standard");
            commonPool.add("WthC_Eagle_IE_Standard");
            commonPool.add("WthC_champion_IE_Standard");
            commonPool.add("WthC_fury_IE_Standard");
            commonPool.add("WthC_dominator_IE_Standard");
            commonPool.add("WthC_Falcon_IE_Standard");
            commonPool.add("WthC_Mora_IE_Standard");

            // Destroyers & Frigates
            commonPool.add("WthC_Alcedo_SS_Standard");
            commonPool.add("WthC_Alcedo_SS_Elite");
            commonPool.add("WthC_Brawler_SS_Standard");
            commonPool.add("WthC_Brawler_SS_Elite");
            commonPool.add("WthC_Centurion_SS_Standard");
            commonPool.add("WthC_Centurion_SS_Elite");
            commonPool.add("WthC_Medusa_SS_Standard");
            commonPool.add("WthC_Medusa_SS_Elite");
            commonPool.add("WthC_whitebird_SS_Standard");
            commonPool.add("WthC_whitebird_SS_Elite");
            commonPool.add("WthC_scarab_SS_Standard");
            commonPool.add("WthC_scarab_SS_Elite");
            commonPool.add("WthC_lumen_SS_Standard");
            commonPool.add("WthC_lumen_SS_Elite");
            commonPool.add("WthC_monitor_SS_Standard");
            commonPool.add("WthC_monitor_SS_Elite");
            commonPool.add("WthC_glimmer_SS_Standard");
            commonPool.add("WthC_glimmer_SS_Elite");
            commonPool.add("WthC_hyperion_SS_Standard");
            commonPool.add("WthC_hyperion_SS_Elite");
            commonPool.add("WthC_HundredEyes_Standard");
            commonPool.add("WthC_HundredEyes_Elite");

            // Add rare ships
            for (int i = 0; i < numRares; i++) {
                String variantId = rarePool.get(random.nextInt(rarePool.size()));
                try {
                    FleetMemberAPI member = Global.getFactory().createFleetMember(FleetMemberType.SHIP, variantId);
                    member.getRepairTracker().setMothballed(true);
                    this.getCargo().getMothballedShips().addFleetMember(member);
                } catch (Exception e) {
                    // Fail-safe in case a variant ID fails to load
                }
            }

            // Add common ships
            for (int i = 0; i < numCommons; i++) {
                String variantId = commonPool.get(random.nextInt(commonPool.size()));
                try {
                    FleetMemberAPI member = Global.getFactory().createFleetMember(FleetMemberType.SHIP, variantId);
                    member.getRepairTracker().setMothballed(true);
                    this.getCargo().getMothballedShips().addFleetMember(member);
                } catch (Exception e) {
                    // Fail-safe in case a variant ID fails to load
                }
            }
        }
    }
}
