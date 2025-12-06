package data.scripts.cargo;


import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;

import java.awt.*;

public class opencargo implements EveryFrameScript {
    private final CargoAPI cargo;
    private boolean success = false;

    public opencargo(CargoAPI cargo) throws AWTException {
        this.cargo = cargo;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    public void advance(float amount) {
        if (success) {
            Global.getSector().removeTransientScript(this);
        } else {
            success = Global.getSector().getCampaignUI().showInteractionDialog(
                    new Interaction_Dialog(cargo, WTHC_cargodata.getInstance().getCargo()), null);
        }
    }
}
