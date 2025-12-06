package data.campaign.archives.WitchData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;


// 从双面神等道具缝合而来
public class Archives_Dust_0 extends BaseSpecialItemPlugin{

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


        tooltip.addPara("Dust Witch's Decorative Style", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("Naizel always wears a black dress with few discernible details, and like her wild hair, its hem mysteriously moves even in completely still air. The simple dress combined with more elaborate physical accessories clearly shows that the Dust Witch doesn't dislike maintaining her appearance, but rather her mastery of energy weaving and the total amount of energy she can control are quite poor in comparison.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("It's precisely because of this that Naizel is often teased by Lika, 'You can destroy a city with a wave of your hand, yet you're so clumsy in this area.' But perhaps suppressing that unimaginable energy itself is already difficult enough.", WthC_ColorData.MID_WHITE, padS);

        tooltip.addPara("In contrast to the completely energy-woven and pitch-black dress, Naizel's stockings and boots beneath are actual physical creations. They consist of a pair of black thigh-high stockings secured by garters that completely cover up to the thigh roots, and a pair of equally pitch-black high-heeled boots. On the stockings' surface, one can clearly feel blue or red pulses flowing through the zigzag decorative lines to the boot-covered bottom, while the boots completely encase from the ankles to the base of the calves, with their slim high heels and pointed design making ordinary people wonder about the skill required for the Dust Witch to walk.", WthC_ColorData.HIGH_BLUE, pad);
        tooltip.addPara("However, as one might expect... wearing such long stockings under a dress that barely shows any leg isn't Naizel's special fetish. These stockings and the boots covering them are necessary preventive measures to protect the surrounding environment from the Dust Witch. After all, for any humanoid creature, legs are usually their only point of contact with the ground. Naizel's current body is extremely dangerous to the human world - she passively conducts stellar energy throughout her body, naturally emitting massive amounts of heat and gamma rays, which would be lethal to all nearby organisms if left unchecked.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("The stockings are a collection of fibrous energy receivers and transmission conduits, made of pan-stellar atomic materials wrapped in Primordial Steel plating, used to collect the large amount of fusion energy from her body and skirt hem and transmit it to the connected boots; the natural slight swaying of Naizel's skirt hem is also due to the circulation pulse effect produced in this process.", WthC_ColorData.MID_WHITE, padS);
        tooltip.addPara("The boots consist of energy capacitors and quantum transmission devices, and of course... parts that function as shoes. Their active function is to cache energy collected from the stockings for Naizel's use, enabling functions like potential energy kicks or inertial dashes. Any energy about to exceed the storage limit is directly sent to the quantum transmission device and teleported to the 'Dust' in outer space. However, even though the Primordial Steel plating ensures over 99.7% of the energy is completely absorbed, the remaining 0.3% still naturally dissipates to the ground, so the boots are made with pointed high heels to minimize contact area - the resulting mature and alluring charm is merely a side effect.", WthC_ColorData.MID_WHITE, padS); }

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
