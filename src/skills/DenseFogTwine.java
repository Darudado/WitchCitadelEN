package data.skills;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BattleAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberViewAPI;
import com.fs.starfarer.api.impl.campaign.abilities.BaseToggleAbility;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.Iterator;

import data.util.WthC_ColorData;
import org.lwjgl.util.vector.Vector2f;

// 原版持续加速修改
public class DenseFogTwine extends BaseToggleAbility {
    public static String SB_NO_STOP = "$sb_active";
    public static String SB_NO_SLOW = "$sb_no_slow";
    public static float DETECTABILITY_PERCENT = -50.0F;
    public static float MAX_BURN_PERCENT = 100.0F;
    public static float ACCELERATION_MULT = 0.8F;
    public static String COMMODITY_ID = "cube_densefog";
    public static float COMMODITY_PER_DAY = 1.0F;

    public DenseFogTwine() {
    }


    protected String getActivationText() {
        return COMMODITY_ID == null || this.getFleet() == null || !(this.getFleet().getCargo().getCommodityQuantity(COMMODITY_ID) <= 0.0F) || Global.getSettings().isDevMode() && !Global.getSettings().getBoolean("playtestingMode") ? "Shrouding Mist Activated" : null;
    }

    protected void activateImpl() {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            if (!fleet.getMemoryWithoutUpdate().is(SB_NO_STOP, true)) {
                fleet.setVelocity(0.0F, 0.0F);
            }

        }
    }

    protected void applyEffect(float amount, float level) {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            if (level > 0.0F && !fleet.isAIMode() && fleet.getCargo().getFuel() <= 0.0F && fleet.getContainingLocation() != null && fleet.getContainingLocation().isHyperspace()) {
                this.deactivate();
            } else {
                fleet.getMemoryWithoutUpdate().set(SB_NO_STOP, true, 0.3F);
                float speed;
                float b;
                float accImpact;
                if (level > 0.0F && level < 1.0F && amount > 0.0F && !fleet.getMemoryWithoutUpdate().is(SB_NO_SLOW, true)) {
                    float activateSeconds = this.getActivationDays() * Global.getSector().getClock().getSecondsPerDay();
                    speed = fleet.getVelocity().length();
                    b = Math.max(speed, 500.0F) / activateSeconds + fleet.getAcceleration();
                    accImpact = b * amount;
                    if (accImpact > speed) {
                        accImpact = speed;
                    }

                    Vector2f dv = Misc.getUnitVectorAtDegreeAngle(Misc.getAngleInDegrees(fleet.getVelocity()));
                    dv.scale(accImpact);
                    fleet.setVelocity(fleet.getVelocity().x - dv.x, fleet.getVelocity().y - dv.y);
                } else {
                    fleet.getStats().getDetectedRangeMod().modifyPercent(this.getModId(), DETECTABILITY_PERCENT * level, "Mist Shroud");
                    speed = 1.0F;
                    b = fleet.getStats().getDynamic().getValue("sustained_burn_bonus", 0.0F);
                    int burnModifier = (int)(b * level);
                    fleet.getStats().getFleetwideMaxBurnMod().modifyFlat(this.getModId(), (float)burnModifier, "Mist Shroud");
                    fleet.getStats().getFleetwideMaxBurnMod().modifyMult(this.getModId(), speed, "Mist Shroud");
                    fleet.getStats().getFleetwideMaxBurnMod().modifyPercent(this.getModId(), MAX_BURN_PERCENT, "Mist Shroud");
                    accImpact = 0.0F;
                    float burn = Misc.getBurnLevelForSpeed(fleet.getVelocity().length());
                    if (burn > 1.0F) {
                        float dir = Misc.getDesiredMoveDir(fleet);
                        float velDir = Misc.getAngleInDegrees(fleet.getVelocity());
                        float diff = Misc.getAngleDiff(dir, velDir);
                        float pad = 120.0F;
                        diff -= pad;
                        if (diff < 0.0F) {
                            diff = 0.0F;
                        }

                        accImpact = 1.0F - 0.5F * Math.min(1.0F, diff / (180.0F - pad));
                    }

                    fleet.getStats().getAccelerationMult().modifyMult(this.getModId(), 1.0F - (1.0F - ACCELERATION_MULT) * accImpact);
                    Iterator var17 = fleet.getViews().iterator();

                    while(var17.hasNext()) {
                        FleetMemberViewAPI view = (FleetMemberViewAPI)var17.next();
                        view.getContrailColor().shift(this.getModId(), view.getEngineColor().getBase(), 1.0F, 1.0F, 0.5F * level);
                        view.getEngineGlowSizeMult().shift(this.getModId(), 1.5F, 1.0F, 1.0F, 1.0F * level);
                        view.getEngineHeightMult().shift(this.getModId(), 3.0F, 1.0F, 1.0F, 1.0F * level);
                        view.getEngineWidthMult().shift(this.getModId(), 2.0F, 1.0F, 1.0F, 1.0F * level);
                    }

                    if (level <= 0.0F) {
                        this.cleanupImpl();
                    }
                    float days = Global.getSector().getClock().convertToDays(amount);
                    if (COMMODITY_ID != null) {
                        float cost = days * COMMODITY_PER_DAY;
                        if (!(fleet.getCargo().getCommodityQuantity(COMMODITY_ID) > 0.0F) && (!Global.getSettings().isDevMode() || Global.getSettings().getBoolean("playtestingMode"))) {
                            CommoditySpecAPI spec = this.getCommodity();
                            fleet.addFloatingText("Missing " + spec.getName().toLowerCase(), Misc.setAlpha(this.entity.getIndicatorColor(), 255), 0.5F);
                            this.deactivate();
                        } else {
                            fleet.getCargo().removeCommodity(COMMODITY_ID, cost);
                        }
                    }
                }
            }
        }
    }
    public CommoditySpecAPI getCommodity() {
        return Global.getSettings().getCommoditySpec(COMMODITY_ID);
    }
    protected void deactivateImpl() {
    }

    protected void cleanupImpl() {
        CampaignFleetAPI fleet = this.getFleet();
        if (fleet != null) {
            fleet.getStats().getDetectedRangeMod().unmodify(this.getModId());
            fleet.getStats().getFleetwideMaxBurnMod().unmodify(this.getModId());
            fleet.getStats().getAccelerationMult().unmodify(this.getModId());
        }
    }

    public boolean showProgressIndicator() {
        return super.showProgressIndicator();
    }

    public boolean showActiveIndicator() {
        return this.isActive();
    }

    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded) {
        CampaignFleetAPI fleet = this.getFleet();
        Color bad = Misc.getNegativeHighlightColor();
        Color gray = Misc.getGrayColor();
        String status = " (OFF) ";
        if (this.turnedOn) {
            status = " (ON) ";
        }
        LabelAPI title = tooltip.addTitle("Scepter Resonance - Enshrouding Mist" + status);
        title.highlightLast(status);
        title.setHighlightColor(gray);
        float pad = 10.0F;
        if (fleet.getCargo().getCommodityQuantity(COMMODITY_ID) < 1.0F) {
            tooltip.addPara("Not enough star energy cubes.", bad, pad);
        }
        tooltip.addPara("Concentrates energy contained in star energy cubes into the scepter to create sustained resonance, providing more flexible sustained acceleration.", WthC_ColorData.IE_WHITE, pad);
        tooltip.addPara("Compared to normal sustained burn, Mist Shroud provides greater fleet maneuverability and reduces detection range by %s", pad, Misc.getPositiveHighlightColor(), "50%");
        tooltip.addPara("Has most other characteristics of sustained burn, and consumes %s unit of %s per day when active.", pad, Misc.getHighlightColor(), "1","Mist Star Energy Cube");
        this.addIncompatibleToTooltip(tooltip, expanded);
    }

    public boolean hasTooltip() {
        return true;
    }
    public boolean isUsable() {
        return super.isUsable() && this.getFleet() != null && (this.getFleet().isAIMode() || this.getFleet().getCargo().getCommodityQuantity(COMMODITY_ID) >= 1.0f);
    }
    public void fleetLeftBattle(BattleAPI battle, boolean engagedInHostilities) {
    }

    public void fleetJoinedBattle(BattleAPI battle) {
        if (!battle.isPlayerInvolved()) {
            this.deactivate();
        }

    }
}
