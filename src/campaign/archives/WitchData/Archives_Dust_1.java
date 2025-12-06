package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Dust_1 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Primordial Spirit Ring: Extinction", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("The Dust Witch's Primordial Spirit Ring is named 'Extinction', also a gift from the Primordial Witch, this ghostly ring is the most mysterious pulsing star among all spirit rings. Extinction's appearance is like a black hole that absorbs all light sources, naturally emitting energy expressed entirely in black, like dark energy in most people's imagination; beneath the black energy wrapping lies an exquisite black diamond sleeping on a transparent crystal base. Perhaps decided from its initial design that it would always hide under the mist formed by energy, Extinction's appearance is as minimalist as its prototype glimpse.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Extinction is often considered a religious term, meaning 'absolute silence beyond all life and death', and to some extent, this is exactly the liberation that the immortal Dust Witch seeks; based on this, Naizel has always believed that only after all stars resonating with herself die completely can this so-called extinction arrive. One could say this ring reflects the Dust Witch's determination to shatter stars.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("Unlike other Primordial Spirit Rings that continue personalizing basic functions, Extinction not only hasn't gained more functions but has fewer than the original, retaining only communication and information database preview capabilities. However, this trade-off resulted in a more massive energy storage system and reliability. After all, no matter how unimaginable the construction of Primordial Spirit Rings is technically, from the most basic perspective, they are still just highly precise quantum machines, still subject to other fundamental forces in the natural environment.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Therefore, even ordinary Primordial Spirit Rings would cease to function near the Dust Witch, who naturally emits massive gamma ray radiation. Thus, the only two scholars of the Crimson City jointly devised a 'Dark Veil Stabilization System'. Based on this, Extinction builds a relatively stable region around itself with the large amount of energy brought by the quantum teleportation device in its core, thereby maintaining the machine's operation.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("The black energy around Extinction isn't actually the dark energy many scholars imagine; in fact, it's just an illusion. Thanks to the success of the Dark Veil Stabilization System, Extinction's core can produce an event horizon effect similar to a black hole, appearing deep black through its ability to absorb light. Although this sounds incredible, Extinction isn't actually a real black hole, just a very limited event horizon barrier.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("Naizel can also manually detach the ring's core, let it float in mid-air, and stretch the dark veil field into a sphere with a maximum diameter of one meter, which is the Dust Witch's only means of carrying natural objects for extended periods. So if you see a dark black sphere floating around her, no doubt, it's filled with things Naizel wants to carry.", WthC_ColorData.MID_WHITE, padS);}

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
