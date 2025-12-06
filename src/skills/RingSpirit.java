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
import data.world.systems.WthC_Citadel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 原版拒止脉冲修改
public class RingSpirit extends BaseDurationAbility {


    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("Skills", "WthC_");

    public RingSpirit() {
    }

    protected void activateImpl() {
    }

    protected void applyEffect(float amount, float level) {
        Global.getSector().getEconomy().getMarket("Crimson_citadel").getPrimaryEntity();
    }
    protected void deactivateImpl() {
        this.cleanupImpl();
    }

    protected void cleanupImpl() {
    }


    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded) {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            Color bad = Misc.getNegativeHighlightColor();
            LabelAPI title = tooltip.addTitle("Scepter Resonance-Delve");
            float pad = 10.0F;
            tooltip.addPara("Focus your mind on the scepter to establish a short-term connection to the Ring Spirit network.", WthC_ColorData.IE_WHITE, pad);
            tooltip.addPara("Can store some abstract products within the Ring Spirit network...", WthC_ColorData.IE_WHITE, pad);

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

    public boolean showCooldownIndicator() {
        return super.showCooldownIndicator();
    }

    public boolean isOnCooldown() {
        return super.getCooldownFraction() < 1.0F;
    }

}
