package data.campaign.archives.Elis;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.ReputationActionResponsePlugin;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.impl.campaign.CoreReputationPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import data.world.systems.WthC_Citadel;


// Stitched together from items like the Two-Faced God
public class Archives_CM extends BaseSpecialItemPlugin{

    protected CampaignFleetAPI player_fleet;
    private static final String hascargo_key ="$Wthc_Cargo_harcargo";

    @Override
    public String getDesignType() {
        return null;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, CargoTransferHandlerAPI transferHandler, Object stackSource) {
        float pad = 10f;
        float padS = 2f;

        tooltip.addTitle(getName());

        String design = getDesignType();

        if (design != null) {
            Misc.addDesignTypePara(tooltip, design, 10f);
        }

        if (!spec.getDesc().isEmpty()) {
            tooltip.addPara(spec.getDesc(), Misc.getTextColor(), pad);
        }


        tooltip.addPara("Hello... traveler from the stars.", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("I know... you possess something unique that other Perseus inhabitants have never shown...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("So... I have prepared some games for you...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("If you are truly powerful enough...", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("If you thirst for wealth beyond the human world...", WthC_ColorData.LIGHT_CRIMSON, padS);
        tooltip.addPara("If you wish to understand the truth of this starry sky...", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("Then perhaps... join this death poem of dancing with phantoms and witches...", WthC_ColorData.LIGHT_CRIMSON, pad);

        tooltip.addPara("Obviously, these mystifying messages come from the owner of this castle...", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("After all, they appeared slowly after you set foot here, giving you ample reason to suspect you're being watched.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("However... you can't deny having some interest in this bizarre castle...", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("(Purchase and right-click this item to unlock the main HVB mission chain)", WthC_ColorData.MID_WHITE, pad);
        tooltip.addPara("(You can find them in the Bounty Board section of the Intel menu (E))", WthC_ColorData.MID_WHITE, padS);

    }

    @Override
    public float getTooltipWidth() {
        return super.getTooltipWidth();
    }

    @Override
    public boolean isTooltipExpandable() {
        return false;
    }

    @Override
    public boolean hasRightClickAction() {
        return true;
    }

    @Override
    public boolean shouldRemoveOnRightClickAction() {
        return true;
    }

    @Override
    public void performRightClickAction() {
        this.player_fleet = Global.getSector().getPlayerFleet();

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Perhaps... this is a point of no return?");
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-ON",true);
        Global.getSector().getMemoryWithoutUpdate().set(hascargo_key,true);

        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-RRA2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Cross2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-IE2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-SSoul2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Elf2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_CM-Dust2",true);

        Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-1EX",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-2EX",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_AL-3EX",true);

        Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-RRA2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Cross2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-IE2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-SSoul2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Elf2",true);
        Global.getSector().getMemoryWithoutUpdate().set("$WthC_BA-Dust2",true);

        Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Skip", "special_items"), 1.0F);
        CoreReputationPlugin.CustomRepImpact impact = new CoreReputationPlugin.CustomRepImpact();
        impact.delta = 0.1f;
        ReputationActionResponsePlugin.ReputationAdjustmentResult result = Global.getSector().adjustPlayerReputation(new CoreReputationPlugin.RepActionEnvelope(CoreReputationPlugin.RepActions.CUSTOM, impact, null, null, true), WthC_Citadel.getHILLYA());

    }
}
