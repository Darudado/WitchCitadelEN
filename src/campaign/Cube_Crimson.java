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
public class Cube_Crimson extends BaseSpecialItemPlugin{

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

        tooltip.addPara("At least... compared to randomly crashing around in this chaotic sector, this risk might be worth the reward?", WthC_ColorData.LIGHT_CRIMSON, pad);
        tooltip.addPara("So... why not stake everything on this, you adventure-loving Persean~?", WthC_ColorData.LIGHT_CRIMSON, padS);

        tooltip.addPara("(The final right of this product belongs to Crimson Citadel...)", WthC_ColorData.CROSS_GARY_D, pad);
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
        float RandomNumber1 = MathUtils.getRandomNumberInRange(0F, 100F);
        if (RandomNumber1 <= 1F) {
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if(RandomNumber2 <= 10F){
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, an ultimate master has arrived! (Easter egg!)");
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Crad_Dali", "special_items"), 1);
            } else {
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 5 of each of the three Transfer Protocol Cards! (1% chance)");
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Crew", "special_items"), 5);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Supplies", "special_items"), 5);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Fuel", "special_items"), 5);
            }

        } else if(RandomNumber1 <= 3F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if (RandomNumber2 <= 25F){
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_LiLancedeepblue2",1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 1 mysterious large hybrid weapon~ (2% chance)");
            } else if (RandomNumber2 <= 50F){
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_RuneBreathe",1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 1 mysterious large hybrid weapon~ (2% chance)");
            } else if (RandomNumber2 <= 75F){
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_RecitationCeremony",1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 1 mysterious large hybrid weapon~ (2% chance)");
            } else {
                Global.getSector().getPlayerFleet().getCargo().addFighters("WthC_RCH_wing",1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 1 super fighter LPC (2% chance)");
            }
        } else if(RandomNumber1 <= 10F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if(RandomNumber2 <= 66F){
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 1 mysterious medium energy weapon~ (7% chance)");
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_StarryEmbellishment",1);
            } else {
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... 1 mysterious medium energy weapon~ (7% chance)");
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_CHB",1);
            }
        } else if(RandomNumber1 <= 20F){
            if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A1-ON")){
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A1-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Ring", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A2-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A2-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Elis", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A3-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A3-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Hillya", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A4-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A4-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Shinki", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A5-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A5-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Romena", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A6-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A6-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Reincarnation", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A7-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A7-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Data_Ba_1", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A8-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A8-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Data_Bb_1", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A9-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A9-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_OldGod", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A10-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A10-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Witch", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A11-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A11-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Yihui", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A12-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A12-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Rules", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A13-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A13-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_CMELF", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A14-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A14-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Angel", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else if (!Global.getSector().getMemoryWithoutUpdate().getBoolean("$WthC_A15-ON")) {
                Global.getSector().getMemoryWithoutUpdate().set("$WthC_A15-ON",true);
                Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Archives_Demon", "special_items"), 1);
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, some strange information appeared on the cube~ (10% chance)");
            } else {
                float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
                if(RandomNumber2 <= 33F) {
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("dragonpod", 1);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("hydra", 1);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("dragon", 2);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("gorgon", 4);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("gazer", 4);
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received some missile weapons that might make your gaze go blank~ (10% chance)");
                } else if(RandomNumber2  <= 66F){
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("heavyneedler",2);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("tachyonlance",1);
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a historically significant best weapon combination~ (10% chance)");
                } else {
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("cyclone",2);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("sabotpod",4);
                    Global.getSector().getPlayerFleet().getCargo().addWeapons("phasecl",4);
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received some reliable missile weapons~ (10% chance)");
                }
            }
        } else if(RandomNumber1  <= 35F){
                float RandomNumber3 = MathUtils.getRandomNumberInRange(0F, 100F);
                if (RandomNumber3 <= 33F){
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... a Transfer Protocol Card (Crew)~! (15% chance)");
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Crew", "special_items"), 1);
                } else if (RandomNumber3 <= 66F){
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... a Transfer Protocol Card (Supplies)~! (15% chance)");
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Supplies", "special_items"), 1);
                } else {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... a Transfer Protocol Card (Fuel)~! (15% chance)");
                    Global.getSector().getPlayerFleet().getCargo().addSpecial(new SpecialItemData("Agreement_Fuel", "special_items"), 1);
                }
        } else if(RandomNumber1  <= 60F){
            float RandomNumber2 = MathUtils.getRandomNumberInRange(0F, 100F);
            if(RandomNumber2 <= 50F) {
                float RandomNumber3 = MathUtils.getRandomNumberInRange(0F, 100F);
                if (RandomNumber3 <= 33F){
                    Global.getSector().getPlayerFleet().getCargo().addCommodity("organics",666f);
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, a large pile of unspeakable things appeared in your cargo hold... at least they can be sold as organics? (25% chance)");
                } else if (RandomNumber3 <= 66F){
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, your cargo hold is filled with things that will soon become space junk... (25% chance)");
                    Global.getSector().getPlayerFleet().getCargo().addCommodity("ore",2333f);
                    Global.getSector().getPlayerFleet().getCargo().addCommodity("metals",1000f);
                } else {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, your cargo hold is filled with nearly expired packaged food... (25% chance)");
                    Global.getSector().getPlayerFleet().getCargo().addCommodity("food",1551f);
                }
            } else {
                float RandomNumber4 = MathUtils.getRandomNumberInRange(0F, 100F);
                if (RandomNumber4 <= 10F){
                    Global.getSector().getPlayerFleet().getCargo().getCredits().add(-648);
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you became the O-God, the initiator of OmegaShin Impact... (-648 credits 25% chance)");
                } else if (RandomNumber4 <= 20F){
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Perhaps even you could change your fate in your dreams? 25% chance)");
                } else if(RandomNumber4 <= 30F){
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Does failure always permeate your entire life? 25% chance)");
                } else if(RandomNumber4 <= 40F) {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Perhaps it's not too late to restart your life? 25% chance)");
                } else if(RandomNumber4 <= 50F) {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Will you load a save like a baby? 25% chance)");
                } else if(RandomNumber4 <= 60F) {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Want me to adjust these probabilities for you? 25% chance)");
                } else if(RandomNumber4 <= 70F) {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Seems you're not invincible when it comes to luck? 25% chance)");
                } else if(RandomNumber4 <= 80F) {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Why not reflect on your usual behavior? 25% chance)");
                } else if(RandomNumber4 <= 90F) {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (So this is the extent of your power? 25% chance)");
                } else {
                    Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                            "You activated a Crimson Cube... Accompanied by a magnificent light show, you received a taunt from the Crimson Lord... (Does this mean your intelligence is below average? 25% chance)");
                }
            }
        } else {
                Global.getSector().getCampaignUI().getMessageDisplay().addMessage(
                        "You activated a Crimson Cube... Accompanied by a magnificent light show, you received... a bunch of decorative weapons that are better than nothing (40% chance - guaranteed reward)");
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_LittleNodeBeam-A",1);
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_MicroNodeBeam",2);
                Global.getSector().getPlayerFleet().getCargo().addWeapons("WthC_MicroNodeBeam-A",2);
                Global.getSector().getPlayerFleet().getCargo().addFighters("WthC_LIB_wing",1);
        }


        Global.getSoundPlayer().playUISound(getSpec().getSoundId(), 1f, 1f);

    }
}
