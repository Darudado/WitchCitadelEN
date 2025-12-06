package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BarEventManager;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.plugin.WthC_CampaignListener;
import data.plugin.WthC_CampaignListener2;
import data.scripts.cargo.Cargo_CampaignPlugin;

import java.util.ArrayList;
import java.util.List;


//来源于虚无主义教程
// 修改文件名和下方为 前缀名_ModPlugin
// 在教程范例中为 GearIn_ModPlugin

public class WthC_ModPlugin extends BaseModPlugin {
    protected CampaignFleetAPI player_fleet;

    public static final String CLASS_MARK = "WthC_Plugin";

    @Override
    public void onGameLoad(boolean newGame) {

        boolean classLoad = false;
        if (Global.getSector().getPersistentData().containsKey(CLASS_MARK)) {
            classLoad = (Boolean)Global.getSector().getPersistentData().get(CLASS_MARK);
        }

        if (!classLoad) {
            Global.getSector().getPersistentData().put(CLASS_MARK, true);
            Global.getSector().addScript(new WthC_CampaignListener());
            Global.getSector().addScript(new WthC_CampaignListener2());
            Global.getSector().getPlayerStats().setSkillLevel("WitchSup", 1.0F);
            SharedData.getData().getPersonBountyEventData().addParticipatingFaction("Crimson_citadel");

            if (NEX()) {
                new WthC_NEXGenerate().generate(Global.getSector());
            } else {
                new WthC_NormalGenerate().generate(Global.getSector());
            }
        }

        if (!Global.getSector().hasScript(Cargo_CampaignPlugin.class)) {
            Global.getSector().addScript(new Cargo_CampaignPlugin());
        }


        onDevModeF8Reload();
        this.addBarEvents();
    }

    @Override
    public void beforeGameSave() {
    }

    @Override
    public void onApplicationLoad() {

    }

    @Override
    public void onNewGame() {
        afterNewGameLoad = false;
        onGameLoad(true);
        afterNewGameLoad = true;



    }
    boolean afterNewGameLoad = true;
    public static boolean NEX() {
        return Global.getSettings().getModManager().isModEnabled("nexerelin");
    }
    protected void addBarEvents() {
        BarEventManager bar = BarEventManager.getInstance();
    }
}
