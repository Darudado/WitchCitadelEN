//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package data.skills;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BattleAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.abilities.BaseDurationAbility;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;

import java.awt.*;
import java.util.Iterator;

// 原版紧急加速修改
public class ResonanceRepair extends BaseDurationAbility {

    public static float MAX_BURN_MOD = -20.0F;
    public static float ACCELERATION_MULT = 10.0F;
    public static String COMMODITY_ID = "cube_deepbule";
    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("Skills", "WthC_");

    public ResonanceRepair() {
    }

    protected void activateImpl() {
        CampaignFleetAPI fleet = this.getFleet();
        if (COMMODITY_ID != null) {
            float cost = 1.0f;
            if (!(fleet.getCargo().getCommodityQuantity(COMMODITY_ID) > 0.0F) && !Global.getSettings().isDevMode()) {
                CommoditySpecAPI spec = this.getCommodity();
                fleet.addFloatingText("Missing " + spec.getName().toLowerCase(), Misc.setAlpha(this.entity.getIndicatorColor(), 255), 0.5F);
                this.deactivate();
            } else {
                fleet.getCargo().removeCommodity(COMMODITY_ID, cost);
            }
        }
    }

    protected void applyEffect(float amount, float level) {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            fleet.getStats().getFleetwideMaxBurnMod().modifyFlat(this.getModId(), (float) ((int) (MAX_BURN_MOD * level)), "Scepter Resonance-Revival");
            fleet.getStats().getAccelerationMult().modifyMult(this.getModId(), 1.0F + (ACCELERATION_MULT - 1.0F) * level);

            Iterator var8 = fleet.getFleetData().getMembersListCopy().iterator();

            while (var8.hasNext()) {
                FleetMemberAPI member = (FleetMemberAPI) var8.next();
                if (fleet != null) {
                    member.getStats().getBaseCRRecoveryRatePercentPerDay().modifyPercent(this.getId(), 1000.0F);
                    member.getStats().getRepairRatePercentPerDay().modifyPercent(this.getId(), 1000.0F);
                }


            }
        }
    }
    protected void deactivateImpl() {
        this.cleanupImpl();
    }

    protected void cleanupImpl() {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            fleet.getStats().getFleetwideMaxBurnMod().unmodify(this.getModId());
            fleet.getStats().getAccelerationMult().unmodify(this.getModId());
        }
        Iterator var8 = fleet.getFleetData().getMembersListCopy().iterator();
            FleetMemberAPI member = (FleetMemberAPI) var8.next();
            if (fleet != null) {
                member.getStats().getBaseCRRecoveryRatePercentPerDay().unmodify(this.getModId());
                member.getStats().getRepairRatePercentPerDay().unmodify(this.getModId());
            }
        }


    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded) {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            Color bad = Misc.getNegativeHighlightColor();
            LabelAPI title = tooltip.addTitle("Scepter Resonance-Revival");
            float pad = 10.0F;
            if (fleet.getCargo().getCommodityQuantity(COMMODITY_ID) < 1.0F) {
                tooltip.addPara("Not enough Star Energy Cubes.", bad, pad);
            }
            tooltip.addPara("Focus the energy of a Star Energy Cube into the scepter to resonate, quickly repairing ships and restoring combat readiness in a short time.", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("The massive star energy used for resonance adaptively utilizes surrounding parts and metals to restore ships to ideal condition in extremely short time, appearing like some inexplicable space magic.", WthC_ColorData.IE_WHITE, pad);

            tooltip.addPara(strings.get("ResonanceRepair_TEXT1"), pad, Misc.getPositiveHighlightColor(), "10");
            tooltip.addPara(strings.get("ResonanceRepair_TEXT2"), pad, Misc.getNegativeHighlightColor(), "cannot move");
            tooltip.addPara(strings.get("ResonanceRepair_TEXT3"), pad, Misc.getPositiveHighlightColor(), "1");
            tooltip.addPara(strings.get("EternalConquer_TEXT0"), pad, Misc.getHighlightColor(), "1","Azure Star Energy Cube","30");

            this.addIncompatibleToTooltip(tooltip, expanded);
        }
    }

    public boolean hasTooltip() {
        return true;
    }

    public void fleetLeftBattle(BattleAPI battle, boolean engagedInHostilities) {
        if (engagedInHostilities) {
            this.deactivate();
        }

    }

    public void fleetOpenedMarket(MarketAPI market) {
        this.deactivate();
    }


    public boolean isUsable() {
        return super.isUsable() && this.getFleet() != null && (this.getFleet().isAIMode() || this.getFleet().getCargo().getCommodityQuantity(COMMODITY_ID) >= 1.0f);
    }

    public CommoditySpecAPI getCommodity() {
        return Global.getSettings().getCommoditySpec(COMMODITY_ID);
    }


    public boolean showCooldownIndicator() {
        return super.showCooldownIndicator();
    }

    public boolean isOnCooldown() {
        return super.getCooldownFraction() < 1.0F;
    }

}