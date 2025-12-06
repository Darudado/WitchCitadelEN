package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_SSoul_0 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("The decorative style of the Spirit Witch", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Although necromorphs, as an energy-based species, are not incapable of wearing clothing woven from material, at least the garments on Su are indeed completely composed of energy, just like her body. Thus, these ornate garments not only appear somewhat unstable, with decorative patterns constantly shifting like swirling clouds, but they also share the same burning and fleeting sensation as Su's form.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, it is precisely these unnatural characteristics that make any onlooker doubt neither the solemnity nor the beauty of this magnificent attire. Descriptions such as 'so beautiful it takes your breath away' and 'so elaborate it makes you hesitate' are both exceedingly apt. One might also say it's fortunate that the Spirit Witch is not a living being and thus does not need to engage in daily activities, for wearing such cumbersome clothing would likely make even the simplest movements a true challenge.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("As if to complement the grandeur of her attire, countless small flame spirits and butterfly spirits always accompany Su. Of course... these colorful, floating entities are more often than not mere decorations and energy reserves rather than true undead. After all, flames and butterflies generally lack the corresponding memories or consciousness, and without any subjective agency, they can hardly be considered necromorphs.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However... due to the overwhelming aura of mystery surrounding Su, many legends about the Lord of the Myriad Rifts prefer to liken these flames and butterflies to malevolent spirits burdened with grave sins and beyond redemption, or to tranquil spirits that have transcended life and death. While these interpretations sound cool and logical, in the end... they are baseless, as those energy entities are simply part of Su's body.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("A multi-layered crown known as the 'Otherworldly Laurel' serves as the Spirit Witch's hat... or so it seems. After all, if it were explicitly recorded as such, one can imagine her fervent followers would consider it a grave sacrilege.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Unlike the seemingly symbolic hats worn by other witches, the Otherworldly Laurel is extraordinarily ornate and profoundly significant, containing vast amounts of energy and always operating in an absolutely precise manner... calling it a hat is somewhat inappropriate.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("The Otherworldly Laurel presents an image of multiple layered crowns combined, symbolizing her unparalleled dominion across several dimensions, from the celestial realms to the depths of hell. This layered crown consists of five tiers, each representing a significant rift: Heaven, the Underworld, the Mortal Realm, the Long River, and Hell. The parts representing Heaven and the Underworld are not directly connected to the others but instead float mysteriously above Su's head, creating a uniquely exquisite aesthetic.", WthC_ColorData.HIGH_BLUE, padS);
        tooltip.addPara("However, like most crown-like headpieces, although the Otherworldly Laurel is surrounded by numerous differing legends across various realms, it is, in reality, merely a decorative item with no special effects. Yet, for such a meticulously designed, self-rotating, and faintly glowing treasure, it might not be unreasonable to assume there are secrets yet unknown to the world?", WthC_ColorData.MID_WHITE, padS); }

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
