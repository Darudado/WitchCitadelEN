package data.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoTransferHandlerAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.impl.items.BaseSpecialItemPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;
import data.util.WthC_Util;
import org.lazywizard.lazylib.MathUtils;


// Stitched together from Janus and other items
public class Cube_DenseFog10 extends BaseSpecialItemPlugin{

    public static final WthC_Util.I18nSection strings = WthC_Util.I18nSection.getInstance("items", "WthC_");

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


        tooltip.addPara("A description engraved on the surface:", WthC_ColorData.MYSTERIOUS_PURPLE, pad);

        tooltip.addPara("This cube has been directly authorized by the Crimson Lord...", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Simply hold it in your palm and recite the identification code noted here to initiate a magnificent light show~", WthC_ColorData.B_WHITE, pad);
        tooltip.addPara("Who knows... something good might happen~?", WthC_ColorData.B_WHITE, padS);
        tooltip.addPara("By the way, this batch is sold in packs of 10... what could be more joyful than a 10-pull?", WthC_ColorData.B_WHITE, padS);

        tooltip.addPara("Where there is gain, there must be loss...", WthC_ColorData.DUST_PURPLE, pad);
        tooltip.addPara("Where there is loss, there must be gain...", WthC_ColorData.DUST_PURPLE, padS);

        tooltip.addPara("(The final interpretation right of this product belongs to Crimson City...)", WthC_ColorData.CROSS_GARY_D, pad);
        tooltip.addPara("(A small percentage of people may experience adverse reactions during the light show, including dizziness, blurred vision, facial twitching, limb distortion, loss of direction, mental confusion, or temporary loss of consciousness. If such phenomena occur, please contact the nearest gene clinic...)", WthC_ColorData.CROSS_GARY_D, padS);


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
        for (int i = 0; i < 10; i++){
            float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);
            if (RandomNumber1 <= 1F) {
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Story", "special_items"), 1);
            } else if(RandomNumber1 <= 3F){
                float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
                if(RandomNumber2 <= 25F){
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_DestinyTrack",1);
                } else if (RandomNumber2 <= 50F){
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_TwinMovement",1);
                } else if (RandomNumber2 <= 75F){
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_JokeAg",1);
                } else {
                    Global.getSector().getPlayerFleet().getCargo().addFighters("WthC_TTR_wing",1);
                }
        } else if(RandomNumber1 <= 10F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if(RandomNumber2 <= 66F){
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_StarryEmbellishment",1);
            } else {
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_CHB",1);
            }
        } else if(RandomNumber1 <= 20F){
                if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A1-ON")){
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A1-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Ring", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A2-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A2-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Elis", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A3-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A3-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Hillya", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A4-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A4-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Shinki", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A5-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A5-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Romena", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A6-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A6-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Reincarnation", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A7-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A7-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Data_Ba_1", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A8-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A8-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Data_Bb_1", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A9-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A9-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_OldGod", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A10-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A10-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Witch", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A11-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A11-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Yihui", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A12-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A12-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Rules", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A13-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A13-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CMELF", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A14-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A14-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Angel", "special_items"), 1);
                } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A15-ON")) {
                    Global.getSector().getMemoryWithoutUpdate().set("$WthC_A15-ON",true);
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Demon", "special_items"), 1);
                } else {
                    float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
                    if (RandomNumber2 <= 25){
                        Global.getSector().getPlayerFleet().getCargo().addCommodity("survey_data_5", 1);
                    } else if (RandomNumber2 <= 50) {
                        Global.getSector().getPlayerFleet().getCargo().addCommodity("survey_data_4", 3);
                    } else if (RandomNumber2 <= 75) {
                        Global.getSector().getPlayerFleet().getCargo().addCommodity("survey_data_3", 5);
                    } else {
                        Global.getSector().getPlayerFleet().getCargo().addCommodity("survey_data_2", 5);
                        Global.getSector().getPlayerFleet().getCargo().addCommodity("survey_data_1", 5);
                    }
                }
        } else if(RandomNumber1  <= 35F){
                float RandomNumber3 = MathUtils.getRandomNumberInRange(0F, 100F);
                if (RandomNumber3 <= 33F){
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Crew", "special_items"), 1);
                } else if (RandomNumber3 <= 66F){
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Supplies", "special_items"), 1);
                } else {
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Fuel", "special_items"), 1);
                }
        } else if(RandomNumber1  <= 60F){
            Global.getSector().getPlayerFleet().getCargo().getCredits().add(1);
        } else {
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_LittleNodeBeam-A",1);
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_MicroNodeBeam",2);
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_MicroNodeBeam-A",2);
                Global.getSector().getPlayerFleet().getCargo().addFighters("WthC_LIB_wing",1);
        }
        }
        Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                "You mechanically activated a pile of Dense Fog Cubes... The overwhelmingly brilliant light show forced you to turn away... After all, it's just for the rewards, right?");
        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);

    }
}
