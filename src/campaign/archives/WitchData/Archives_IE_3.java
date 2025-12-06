package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_IE_3 extends BaseSpecialItemPlugin{

    protected CampaignFleetAPI player_fleet;

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


        tooltip.addPara("Primordial Light Spear", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Primordial Light Spear is the Primordial Witch's most iconic lethal technique, manifesting as a fast and precise elemental energy beam, precisely destroying targets from an absolutely safe distance using targeted light, heat, and elemental reactions.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Since these beams often appear similar and change color based on the added element type, they are symbolically called 'Primordial Light Spears'. However, these seemingly just color-different beams can actually cause vastly different destructive effects.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("This is a rapid ranged attack method emphasizing projection volume. Each beam usually lasts only one to two seconds, but often the next shot arrives before the previous light has fully faded.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("And compared to other beams emphasizing pure heat damage, the Primordial Witch's magic focuses more on element combinations, gradually weakening opponents through mixed elemental strikes has always been her most proficient combat style.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Simple, efficient, versatile, and highly controllable - as an attack method, the Primordial Light Spear has many advantages. Perhaps its only weakness is that against opponents who can effectively counter elemental magic with shield-type defenses, battles often devolve into energy attrition warfare.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, this disadvantage is common to energy weapons, and at such times the concept of layered element combinations stripping away opponent defenses proves extremely effective, often allowing Lika to create more opportunities to defeat theoretically stronger beings...", WthC_ColorData.MID_WHITE, padS);}

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

        Global.getSector().getPlayerStats().addStoryPoints(1);

        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "Congratulations on spending 10 million for a piece of data... Keep up the good work, little genius. Here's a Story Point for your trouble~");
    }
}
