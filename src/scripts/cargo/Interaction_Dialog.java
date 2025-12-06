package data.scripts.cargo;


import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.campaign.rules.Option;
import com.fs.starfarer.api.combat.EngagementResultAPI;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.util.WthC_ColorData;

import java.awt.*;
import java.util.Map;


public class Interaction_Dialog implements InteractionDialogPlugin {
    private final CargoAPI playerCargo;
    private final CargoAPI crateCargo;
    private InteractionDialogAPI dialog;
    protected TextPanelAPI textPanel;
    protected OptionPanelAPI options;
    protected VisualPanelAPI visual;
    private static final String key ="$Wthc_Cargo_auto";
    private static final String tag ="Cargo_target";
    private boolean fired =false;

    public Interaction_Dialog(CargoAPI playerCargo, CargoAPI crateCargo) {
        this.playerCargo = playerCargo;
        this.crateCargo = crateCargo;
    }
    protected static enum OptionId {
        INIT,
        CARGO,
        CHANGE,
        LEAVE,
    }
    //这里切换货舱显示的旗帜，填上去自己的facitonid
    public String getfaction(){
        return Global.getSector().getFaction("Crimson_citadel").getLogo();
    }


    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        textPanel = dialog.getTextPanel();
        options = dialog.getOptionPanel();
        visual = dialog.getVisualPanel();

        //在这里切换右边显示的图片，改前面两个就行
        visual.showImagePortion("illustrations","WthC_Crimson_citadel",640, 400, 0, 0, 480, 300);

        //dialog.setOptionOnEscape("Leave", OptionId.LEAVE);

        dialog.setOptionOnEscape("Leave", OptionId.LEAVE);
        optionSelected(null, OptionId.INIT);
    }


    @Override
    public void optionSelected(String optionText, Object optionData) {
        if (optionData == null) return;
        Global.getLogger(this.getClass()).info(optionData.getClass());
        if (optionData instanceof OptionId){
            OptionId option =(OptionId)optionData;
            switch (option) {
                case INIT:
                    createInitialOptions();
                    break;
                case CARGO:
                    showCratePickerDialog();
                    break;
                case CHANGE:
                   boolean change = getmemorykey();
                    Global.getSector().getMemoryWithoutUpdate().set(key,!change);
                    textPanel.addParagraph("Auto-collection is now "+(!change ? "enabled":"disabled"));
                    options.setTooltip(OptionId.CHANGE,"Will toggle auto-collection mode, current status:"+ (!change?"enabled":"disabled"));
                    break;
                case LEAVE:
                    dialog.dismiss();
                    break;
            }
        }
    }
    protected void createInitialOptions() {
        options.clearOptions();
        options.addOption("Open Cargo Hold", OptionId.CARGO);
        options.setTooltip(OptionId.CARGO, "Open Cargo Hold");
        options.addOption("Toggle Auto-Storage", OptionId.CHANGE);
        boolean b = getmemorykey();
        options.setTooltip(OptionId.CHANGE, "Will toggle auto-storage mode, currently: " + (b ? "On" : "Off"));
        options.addOption("Leave", OptionId.LEAVE, null);


        // This is the small left text in the selection interface
        float pad = 2f;
        TooltipMakerAPI tooltip = textPanel.beginTooltip();
        tooltip.addPara("It seems that this so-called Ringwraith Network can %s some of the products from Crimson Citadel.", pad, WthC_ColorData.HIGH_BLUE, Misc.getHighlightColor(), new String[]{"store"});
        tooltip.addPara("Although those small items won't put much pressure on your cargo hold, it must be admitted that this magical %s is indeed quite novel.", pad, WthC_ColorData.HIGH_BLUE, Misc.getHighlightColor(), new String[]{"storage process"});
        tooltip.addPara("(Auto-storage interval is %s in-game time)", pad, WthC_ColorData.HIGH_BLUE, Misc.getHighlightColor(), new String[]{"1 day"});
        textPanel.addTooltip();
    }

    public boolean getmemorykey(){
        if(!Global.getSector().getMemoryWithoutUpdate().contains(key)){
            Global.getSector().getMemoryWithoutUpdate().set(key,true);
            return false;
        }else {
           return Global.getSector().getMemoryWithoutUpdate().getBoolean(key);
        }
    }

    public void showCratePickerDialog() {
        final CargoAPI availableCargo = Global.getFactory().createCargo(false);
        final CargoAPI Cargofromplayer = Global.getFactory().createCargo(false);

        for (CargoStackAPI stack : this.playerCargo.getStacksCopy()) {
            if (!stack.isSpecialStack()  ) {
                CommoditySpecAPI spec = stack.getResourceIfResource();
                if (spec != null && spec.getTags().contains(tag)) {
                    Cargofromplayer.addCommodity(stack.getCommodityId(),stack.getSize());
                    playerCargo.removeStack(stack);
                }
            }
            if(stack.isSpecialStack() && stack.getSpecialItemSpecIfSpecial()!=null ){
                if(stack.getSpecialItemSpecIfSpecial().getTags().contains(tag)){

                    Cargofromplayer.addSpecial(stack.getSpecialDataIfSpecial(),stack.getSize());
                    playerCargo.removeStack(stack);

                }

            }
            //add



        }

        availableCargo.addAll(crateCargo);


        availableCargo.sort();

        //这里是title
        this.dialog.showCargoPickerDialog("Cargo Hold", "Confirm", "Cancel", true, 240.0F, availableCargo, new CargoPickerListener() {    //listener
            public void pickedCargo(CargoAPI selectedCargo) {

                if (selectedCargo.isEmpty()) {
                    closeDialog();
                    return;
                }
                crateCargo.removeAll(selectedCargo);
                playerCargo.addAll(selectedCargo);
                availableCargo.removeAll(crateCargo);
                crateCargo.addAll(availableCargo);

                for (CargoStackAPI stack : crateCargo.getStacksCopy()) {
                    if (stack.isNull() || stack.getSize() == 0) {
                        crateCargo.removeStack(stack);
                    }
                }

              closeDialog();
            }

            public void cancelledCargoSelection() {
             //   crateCargo.addAll(Cargofromplayer);
                playerCargo.addAll(Cargofromplayer);
                closeDialog();
            }

            public void recreateTextPanel(TooltipMakerAPI panel, CargoAPI cargo, CargoStackAPI pickedUp, boolean pickedUpFromSource, CargoAPI combined) {
                float opad = 10.0F;
                if(!fired){
                    fired =true;
                   cargo.addAll(Cargofromplayer);
                }


                //在这里写仓库的小作文可以根据每次选择的pickercargo 来变化
                panel.setParaFontOrbitron();
                panel.setParaOrbitronLarge();
                 panel.setParaFontDefault();
                panel.addImage(getfaction(), 240f, 3f);

                panel.addPara("Even though it feels somewhat unreal, you can still sense that these items are currently stored in some %s.", 2f, WthC_ColorData.HIGH_BLUE, Misc.getHighlightColor(), new String[]{"hidden corner"});
                panel.addPara("(Please retrieve an item before using it)", WthC_ColorData.B_WHITE_L, 2f);
                int i = 0;
                if (availableCargo.getStacksCopy() != null && !availableCargo.getStacksCopy().isEmpty()) {
                    for (CargoStackAPI stack : availableCargo.getStacksCopy()) {
                        if (stack.getDisplayName() == null) continue;
                        if (stack.getSize() == 0) continue;
                        if (i >= 14) {
                            panel.addPara("     ......and other items", Misc.getHighlightColor(), 5f);
                            break;
                        }
                        panel.addPara(i == 0 ? "Currently has" : " " + " %s units of %s ", 2f, WthC_ColorData.HIGH_BLUE, Misc.getHighlightColor(), new
                                String[]{String.valueOf((int) stack.getSize()), stack.getDisplayName()});
                        i++;
                    }
                }
                // Write the player's selected items section here. pickedfromsource determines whether it's from top to bottom or bottom to top.
                if (pickedUp != null && pickedUp.getDisplayName() != null) {
                    panel.addPara("Selected " + pickedUp.getDisplayName() + "*" + (int) pickedUp.getSize(), 4f);
                }

            }

            private void closeDialog() {

               dialog.dismiss();
            }
        });
    }


    @Override
    public void optionMousedOver(String optionText, Object optionData) {

    }

    @Override
    public void advance(float amount) {

    }

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {

    }


    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }
}

